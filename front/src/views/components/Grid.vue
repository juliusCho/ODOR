<template>
    <v-container class="grey lighten-5" text-center>
        <v-row no-gutters>
            <template v-for="(col, idx) in columns">
                <v-col :key="idx">
                    <v-card
                        @click="sortBy(col)"
                        class="pa-1"
                        outlined
                        title
                        style="background-color: lightgrey;"
                    >
                        {{ col | capitalize }}
                        <v-icon v-if="sortOrders[col] > 0">mdi-chevron-down</v-icon>
                        <v-icon v-else>mdi-chevron-up</v-icon>
                    </v-card>
                </v-col>
                <v-responsive
                        v-if="idx === 2"
                        :key="`width-${idx}`"
                        width="100%"
                ></v-responsive>
            </template>
        </v-row>
        <v-row no-gutters v-for="(data, idx) in filteredData" :key="idx">
            <v-col v-for="col in columns" :key="col">
                <v-card
                    class="pa-1"
                    outlined
                >
                    {{data[col]}}
                </v-card>
            </v-col>
            <v-responsive
                    v-if="idx === 2"
                    :key="`width-${idx}`"
                    width="100%"
            ></v-responsive>
        </v-row>
    </v-container>
</template>

<script>
    export default {
        name: 'Grid',
        props: {
            searchUrl: {
                type: String,
                default: '',
                required: true
            },
            saveUrl: {
                type: String,
                default: '',
                required: false
            },
            columns: {
                type: Array,
                default: () => [],
                required: true
            },
            rowData: {
                type: Array,
                default: () => [],
                required: false
            },
            searchKeys: {
                type: Array,
                default: () => [],
                required: false
            }
        },
        data() {
            let sortOrders = {};
            this.columns.forEach(key => {
                sortOrders[key] = 1;
            });
            //---------------------------------------------------

            return {
                sortKey: '',
                sortOrders: sortOrders
            }
        },
        computed: {
            filteredData() {
                let rowData = this.rowData;
                let order = this.sortOrders[this.sortKey] || 1;
                let searchKeys = this.searchKeys;

                if (searchKeys.length > 0) {
                    searchKeys.forEach(searchKey => {
                        rowData = rowData.filter(row =>
                            Object.keys(row).some(key =>
                                String(row[key])
                                    .toLowerCase()
                                    .indexOf(searchKey.toLowerCase()) > -1
                            )
                        );
                    });
                }
                if (this.sortKey) {
                    rowData = rowData.slice().sort((a, b) => {
                        a = a[this.sortKey];
                        b = b[this.sortKey];
                        return (a === b ? 0 : a > b ? 1 : -1) * order;
                    });
                }
                return rowData;
            }
        },
        filters: {
            capitalize(str) {
                return str.charAt(0).toUpperCase() + str.slice(1);
            }
        },
        methods: {
            sortBy(key) {
                this.sortKey = key;
                this.sortOrders[key] *= -1;
            }
        }
    }
</script>

<style scoped>

</style>