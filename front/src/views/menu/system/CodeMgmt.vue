<template>
    <v-container>
        <v-form id="search">
            <v-row>
                <v-col>
                    <v-text-field
                            v-model="searchKeys.codeGroupId"
                            label="Code Group Id"
                    />
                </v-col>
                <v-col>
                    <v-text-field
                            v-model="searchKeys.codeGroupName"
                            label="Code Group Name"
                    />
                </v-col>
                <v-col>
                    <v-select
                        :items="searchCombos.useYn"
                        item-text="codeName"
                        item-value="codeId"
                        v-model="searchKeys.useYn"
                        label="Use"
                    />
                </v-col>
                <v-col>
                    <v-btn @click="getCodeGroupList">
                        Search
                    </v-btn>
                </v-col>
            </v-row>
        </v-form>
        <v-data-table
            :headers="headers"
            :items="codeGroupList"
            item-key="codeGroupId"
            v-model="selectedCodeGroup"
            show-select
        >
            <template slot="items" slot-scope="props">
                <tr>
                    <td>
                        <v-checkbox
                            v-model="props.selected"
                            hide-details
                        ></v-checkbox>
                    </td>
                    <td>{{ props.item.codeGroupId }}</td>
                    <td>{{ props.item.codeGroupMessage }}</td>
                    <td>{{ props.item.codeGroupName }}</td>
                    <td>{{ props.item.useYn }}</td>
                    <td>{{ props.item.updaterName }}</td>
                    <td>{{ props.item.updateDate }}</td>
                </tr>
            </template>
        </v-data-table>
    </v-container>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'CodeMgmt',
        mounted() {
            this.getCodeGroupList();
        },
        data() {
            return {
                searchKeys: {
                    codeGroupId: '',
                    codeGroupName: '',
                    useYn: true
                },
                searchCombos: {
                    useYn: CODE.getCodeList('USE_YN')
                },
                headers: [
                    {
                        text: 'Code Group ID',
                        value: 'codeGroupId',
                        width: '200px'
                        // fixed: true
                        // sortable: false
                    },
                    {
                        text: 'Code Group Message',
                        value: 'codeGroupMessage',
                        width: '200px'
                    },
                    {
                        text: 'Code Group Name',
                        value: 'codeGroupName',
                        width: '200px'
                    },
                    {
                        text: 'Use YN',
                        value: 'useYn',
                        width: '100px'
                    },
                    {
                        text: 'Updater',
                        value: 'updaterName',
                        width: '150px'
                    },
                    {
                        text: 'Update Date',
                        value: 'updateDate',
                        width: '150px'
                    }
                ],
                codeGroupList: [],
                selectedCodeGroup: []
            }
        },
        methods: {
            getCodeGroupList() {
                axios.post(
                    API.CodeMgmtController.getCodeGroupList,
                    this.searchKeys
                ).then(res => {
                    this.codeGroupList = res.data;
                });
            },
            updateItem(item) {

            }
        }
    }
</script>
