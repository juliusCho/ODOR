<template>
    <v-container fluid text-center>
        <v-row justify="center">
            <v-col>
                <v-btn
                        class="pl-12 pr-12"
                        @click="save"
                        color="primary"
                        rounded
                >
                    Save
                </v-btn>
            </v-col>
            <v-spacer/><v-spacer/><v-spacer/>
            <v-spacer/><v-spacer/><v-spacer/>
            <v-col>
                <v-btn
                        class="pl-12 pr-12"
                        @click="add"
                        rounded
                >
                    Add
                </v-btn>
            </v-col>
            <v-col>
                <v-btn
                        class="pl-12 pr-12"
                        @click="search"
                        rounded
                >
                    Reload
                </v-btn>
            </v-col>
        </v-row>
        <v-row no-gutters>
            <template v-for="(col, idx) in columns">
                <v-col :key="idx" v-if="col.display">
                    <v-card
                        @click="sortBy(col.key)"
                        class="pa-1"
                        outlined
                        title
                        style="background-color: lightgrey;"
                    >
                        {{ col.label | capitalize }}
                        <v-icon v-if="sortOrders[col.key] > 0">mdi-chevron-down</v-icon>
                        <v-icon v-else>mdi-chevron-up</v-icon>
                    </v-card>
                </v-col>
            </template>
        </v-row>
        <v-layout row v-for="(data, idx) in filteredData" :key="idx">
            <template v-for="col in columns">
                <v-flex xs1.5 :key="col.key" v-if="col.display">
                    <v-card
                            class="pa-1"
                            outlined
                    >
                        <v-card-text>{{ data[col.key] }}</v-card-text>
                    </v-card>
                </v-flex>
            </template>
        </v-layout>
<!--        <v-row no-gutters v-for="(data, idx) in filteredData" :key="idx">-->
<!--            <template v-for="col in columns">-->
<!--                <v-col :key="col.key" v-if="col.display">-->
<!--                    <v-card-->
<!--                        class="pa-1"-->
<!--                        outlined-->
<!--                    >-->
<!--                        {{ data[col.key] }}-->
<!--                    </v-card>-->
<!--                </v-col>-->
<!--            </template>-->
<!--        </v-row>-->
    </v-container>
</template>

<script>
    export default {
        name: 'Grid',
        props: {
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
                sortOrders[key.key] = 1;
            });
            //---------------------------------------------------

            return {
                sortKey: '',
                sortOrders: sortOrders
            };
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
            },
            save() {
                this.$emit('save');
            },
            add() {
                this.$emit('add');
            },
            search() {
                this.$emit('search');
            }
        }
    }
</script>