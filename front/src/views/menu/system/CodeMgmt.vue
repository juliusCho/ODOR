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
                @add="addConfirm"
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

        <UpdatePopup
            :show="updatePopShow"
            :title="'코드그룹수정'"
            :update="true"
            :okBtnText="'ㄱㄱ'"
            :cancelBtnText="'ㄴㄴ'"
            :width="1000"
            :fields="headers"
            :values="selectedCodeGroup[0]"
            @okAction="updateItem"
            @cancelAction="updatePopShow = false"
        />

        <InsertPopup
            :show="insertPopShow"
            :title="'코드그룹생성'"
            :okBtnText="'ㄱㄱ'"
            :cancelBtnText="'ㄴㄴ'"
            :width="1000"
            :fields="headers"
            @okAction="addItem"
            @cancelAction="insertPopShow = false"
        />
    </v-container>
</template>

<script>
    import axios from 'axios';
    import SystemBtn from "@/views/components/SystemBtn";
    import DeleteDialog from "@/views/components/Dialog";
    import UpdatePopup from "@/views/components/Popup/SystemPopup";
    import InsertPopup from "@/views/components/Popup/SystemPopup";

    export default {
        name: 'CodeMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup
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
                        type: 'string',
                        updateType: 'disabled',
                        insertType: 'text'
                        // fixed: true
                        // sortable: false
                    },
                    {
                        text: 'Code Group Message',
                        value: 'codeGroupMessage',
                        width: '180px',
                        type: 'string',
                        updateType: 'message',
                        insertType: 'message'
                    },
                    {
                        text: 'Locale Message',
                        value: 'localeMessage',
                        width: '*'
                    },
                    {
                        text: 'Code Group Name',
                        value: 'codeGroupName',
                        width: '190px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text'
                    },
                    {
                        text: 'Use YN',
                        value: 'useYn',
                        width: '100px',
                        type: 'boolean',
                        updateType: 'switch',
                        insertType: 'switch'
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


                confirmShow: false,
                updatePopShow: false,
                insertPopShow: false
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
                this.updatePopShow = this.selectedCodeGroup.length !== 0;
            },
            updateItem() {
            },
            deleteConfirm() {
                this.confirmShow = this.selectedCodeGroup.length !== 0;
            },
            deleteItem() {
                this.confirmShow = false;
            },
            addConfirm() {
                this.insertPopShow = true;
            },
            addItem() {
            },
            goTo(page) {
                this.$emit('goTo', page);
            }
        }
    }
</script>
