<template>
    <table>
        <thead>
            <tr>
                <th v-for="col in columns" :key="col.label"
                    :style="`width: ${col.width === '*' ? getAstaWidth : (col.width + 'px')};`"
                    @click="sortBy(col.label)"
                    :class="{ active: sortKey == col.label }">
                    {{ col.label | capitalize }}
                    <span class="arrow" :class="sortOrders[col.label] > 0 ? 'asc' : 'dsc'">
                  </span>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(entry, idx) in filteredData" :key="idx">
                <td v-for="col in columns" :key="col.label">
                    {{entry[col.label]}}
                </td>
            </tr>
        </tbody>
    </table>
</template>

<script>
    export default {
        name: "DataGrid",
        props: {
            data: {
                type: Array,
                default: () => [],
                required: false
            },
            columns: {
                type: Array,
                default: () => [],
                required: true
            },
            filterKey: {
                type: String,
                default: "",
                required: false
            }
        },
        data() {
            let sortOrders = {};
            this.columns.forEach(col => {
                sortOrders[col.label] = 1;
            });

            return {
                sortKey: "",
                sortOrders: sortOrders
            };
        },
        computed: {
            filteredData() {
                let sortKey = this.sortKey;
                let filterKey = this.filterKey && this.filterKey.toLowerCase();
                let order = this.sortOrders[sortKey] || 1;
                let data = this.data;

                if (filterKey) {
                    data = data.filter(row =>
                         Object.keys(row).some(key =>
                                String(row[key])
                                    .toLowerCase()
                                    .indexOf(filterKey) > -1
                        )
                    );
                }
                if (sortKey) {
                    data = data.slice().sort((a, b) => {
                        a = a[sortKey];
                        b = b[sortKey];
                        return (a === b ? 0 : a > b ? 1 : -1) * order;
                    });
                }
                return data;
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
                this.sortOrders[key] = this.sortOrders[key] * -1;
            },
            getAstaWidth() {
                let width = 0;
                for (let col in this.columns) {
                    if (col.width !== '*') {
                        width += Number(col.width);
                    }
                }
                return 'calc(100% - ' + width + 'px)';
            }
        }
    }
</script>

<style scoped>
    body {
        font-family: Helvetica Neue, Arial, sans-serif;
        font-size: 14px;
        color: #444;
    }

    table {
        background-color: #fff;
        border-spacing: 0;
        width: calc(100% - 20px);
    }

    th {
        background-color: #3c80ff;
        color: #cfe3ff;
        cursor: pointer;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    td {
        background-color: #f9f9f9;
    }

    th,
    td {
        min-width: 20px;
        padding: 10px 20px;
    }

    th.active {
        color: #ffffff;
    }

    th.active .arrow {
        opacity: 1;
    }

    .arrow {
        display: inline-block;
        vertical-align: middle;
        width: 0;
        height: 0;
        margin-left: 5px;
        opacity: 0.66;
    }

    .arrow.asc {
        border-left: 4px solid transparent;
        border-right: 4px solid transparent;
        border-bottom: 4px solid #fff;
    }

    .arrow.dsc {
        border-left: 4px solid transparent;
        border-right: 4px solid transparent;
        border-top: 4px solid #fff;
    }
</style>