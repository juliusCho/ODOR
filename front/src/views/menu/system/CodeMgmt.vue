<template>
    <v-container>
        <v-form id="search">
            <v-row>
                <v-col>
                    <v-autocomplete
                            :items="searchCombos.codeGroupId"
                            color="white"
                            label="Code Group ID"
                            v-model="searchKeys.codeGroupId"
                    ></v-autocomplete>
                </v-col>
                <v-col>
                    <v-autocomplete
                            :items="searchCombos.codeGroupName"
                            color="white"
                            item-value="codeGroupId"
                            item-text="codeGroupName"
                            label="Code Group Name"
                            v-model="searchKeys.codeGroupName"
                    ></v-autocomplete>
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
            </v-row>
            <v-row justify="center">
                <v-col>
                    <v-btn @click="updateItem" color="deep-purple lighten-5">
                        Update
                    </v-btn>
                </v-col>
                <v-col>
                    <v-btn @click="deleteConfirm" color="red lighten-5">
                        Delete
                    </v-btn>
                </v-col>
                <v-spacer/><v-spacer/><v-spacer/>
                <v-spacer/><v-spacer/><v-spacer/>
                <v-col>
                    <v-btn @click="addItem" color="blue lighten-5">
                        Add
                    </v-btn>
                </v-col>
                <v-col>
                    <v-btn @click="getCodeGroupList" color="teal lighten-5">
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
            single-select
            @click:row="goTo('CodeDetailMgmt')"
        >
            <template slot="items" slot-scope="props">
                <tr >
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

        <Dialog
            :show="confirmShow"
            :title="'확인'"
            :content="'진짜 삭제 ㄱ?'"
            type="C"
            :yesBtnText="'ㅇㅇ'"
            :noBtnText="'ㄴㄴ'"
            :width="500"
            @yesAction="deleteItem"
            @noAction="confirmShow = false"
        />
    </v-container>
</template>

<script>
    import axios from 'axios';
    import Dialog from "@/views/components/Dialog";

    export default {
        name: 'CodeMgmt',
        components: {
            Dialog
        },
        mounted() {
            this.getCodeGroupList();
        },
        data() {
            let codeGroupIdList = CODE.getCodeListAll().map(v => v.codeGroupId);

            return {
                searchKeys: {
                    codeGroupId: '',
                    codeGroupName: '',
                    useYn: true
                },
                searchCombos: {
                    codeGroupId: [''].concat(COMMON_UTIL.removeArrDuplicate(codeGroupIdList)),
                    codeGroupName: [],
                    useYn: CODE.getCodeList('USE_YN')
                },


                headers: [
                    {
                        text: 'Code Group ID',
                        value: 'codeGroupId',
                        width: '180px'
                        // fixed: true
                        // sortable: false
                    },
                    {
                        text: 'Code Group Message',
                        value: 'codeGroupMessage',
                        width: '180px'
                    },
                    {
                        text: 'Locale Message',
                        value: 'localeMessage',
                        width: '*'
                    },
                    {
                        text: 'Code Group Name',
                        value: 'codeGroupName',
                        width: '190px'
                    },
                    {
                        text: 'Use YN',
                        value: 'useYn',
                        width: '100px'
                    },
                    {
                        text: 'Updater',
                        value: 'updaterName',
                        width: '100px'
                    },
                    {
                        text: 'Update Date',
                        value: 'updateDate',
                        width: '150px'
                    }
                ],
                codeGroupList: [],
                selectedCodeGroup: [],


                confirmShow: false
            }
        },
        methods: {
            getCodeGroupList() {
                axios.post(
                    API.CodeMgmtController.getCodeGroupList,
                    this.searchKeys
                ).then(res => {
                    this.codeGroupList = res.data;
                    this.searchCombos.codeGroupName = [{
                        codeGroupId: '',
                        codeGroupName: 'All'
                    }].concat(
                            this.codeGroupList.map(v => ({
                                codeGroupId: v.codeGroupId,
                                codeGroupName: v.codeGroupName
                            })
                        ));
                });
            },
            updateItem() {
                console.log(this.selectedCodeGroup);
            },
            deleteConfirm() {
                if (this.selectedCodeGroup.length === 0) {
                    return;
                }
                this.confirmShow = true;
            },
            deleteItem() {
                this.confirmShow = false;
                console.log(this.selectedCodeGroup);
            },
            addItem() {
                console.log(this.selectedCodeGroup);

                console.log(this.searchCombos.codeGroupName);
            },
            goTo(page) {
                this.$emit('goTo', page);
            }
        }
    }
</script>
