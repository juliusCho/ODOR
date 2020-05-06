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
            <SystemBtn
                @search="getCodeGroupList"
                @add="addItem"
                @update="updateConfirm"
                @delete="deleteConfirm"
            />
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

        <DeleteDialog
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
    import SystemBtn from "@/views/components/SystemBtn";
    import DeleteDialog from "@/views/components/Dialog";

    export default {
        name: 'CodeMgmt',
        components: {
            SystemBtn,
            DeleteDialog
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
                        width: '180px',
                        type: 'disabled'
                        // fixed: true
                        // sortable: false
                    },
                    {
                        text: 'Code Group Message',
                        value: 'codeGroupMessage',
                        width: '180px',
                        type: 'message'
                    },
                    {
                        text: 'Locale Message',
                        value: 'localeMessage',
                        width: '*',
                        type: 'none'
                    },
                    {
                        text: 'Code Group Name',
                        value: 'codeGroupName',
                        width: '190px',
                        type: 'text'
                    },
                    {
                        text: 'Use YN',
                        value: 'useYn',
                        width: '100px',
                        type: 'radio'
                    },
                    {
                        text: 'Updater',
                        value: 'updaterName',
                        width: '100px',
                        type: 'none'
                    },
                    {
                        text: 'Update Date',
                        value: 'updateDate',
                        width: '150px',
                        type: 'none'
                    }
                ],
                codeGroupList: [],
                selectedCodeGroup: [],


                confirmShow: false
            }
        },
        methods: {
            getCodeGroupList() {
                this.selectedCodeGroup = [];

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
            updateConfirm() {
                if (this.selectedCodeGroup.length === 0) {
                    return;
                }
            },
            updateItem() {
            },
            deleteConfirm() {
                if (this.selectedCodeGroup.length === 0) {
                    return;
                }
                this.confirmShow = true;
            },
            deleteItem() {
                this.confirmShow = false;
            },
            addItem() {
            },
            goTo(page) {
                this.$emit('goTo', page);
            }
        }
    }
</script>
