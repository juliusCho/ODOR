<template>
    <v-container>
        <RightTopAlert
                :status="alertStatus"
                :msg="alertMsg"
                :show="alertShow"
                @hideDisplay="alertShow = false"
        />

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
            @click:row="goTo('CodeDetailMgmt', $event)"
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
                    <td>{{ props.item.updateDtTime }}</td>
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
            ref="updatePopup"
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
            ref="insertPopup"
            :show="insertPopShow"
            :title="'코드그룹생성'"
            :okBtnText="'ㄱㄱ'"
            :cancelBtnText="'ㄴㄴ'"
            :width="1000"
            :fields="headers"
            :validation="duplicateValidation"
            :invalidMsg="'코드ID중복!!'"
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
    import RightTopAlert from "@/views/components/RightTopAlert";
    import {eventBus} from '@/main';

    export default {
        name: 'CodeMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert
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
                        insertType: 'id'
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
                        updateType: 'switch'
                    },
                    {
                        text: 'Updater',
                        value: 'updaterName',
                        width: '100px'
                    },
                    {
                        text: 'Update Date',
                        value: 'updateDtTime',
                        width: '150px'
                    }
                ],
                codeGroupList: [],
                selectedCodeGroup: [],


                confirmShow: false,
                updatePopShow: false,
                insertPopShow: false,

                alertShow: false,
                alertMsg: '',
                alertStatus: ''
            };
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
            deleteConfirm() {
                this.confirmShow = this.selectedCodeGroup.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.CodeMgmtController.deleteCodeGroup,
                    this.selectedCodeGroup[0]
                )
                .then(res => {
                    this.confirmShow = false;
                    this.doneAlert(res.data);
                    this.getCodeGroupList();
                });
            },
            addConfirm() {
                this.insertPopShow = true;
            },
            async duplicateValidation(data) {
                let result = true;

                await axios.post(
                    API.CodeMgmtController.checkDuplication,
                    null,
                    {
                        params: {
                            codeGroupId: data.codeGroupId
                        }
                    }
                )
                .then(res => {
                    result = res.data === 0;
                });
                return result;
            },
            addItem(data) {
                axios.put(API.CodeMgmtController.insertCodeGroup, data)
                .then(res => {
                    this.doneAlert(res.data);
                    this.getCodeGroupList();
                });
            },
            updateItem(data) {
                axios.patch(API.CodeMgmtController.updateCodeGroup, data)
                .then(res => {
                    this.doneAlert(res.data);
                    this.getCodeGroupList();
                });
            },
            doneAlert(type) {
                if (type === 'success') {
                    this.alertStatus = 'info';
                    this.alertMsg = '저장완료';
                } else {
                    this.alertStatus = 'error';
                    this.alertMsg = '저장실패';
                }
                this.alertShow = true;
            },
            goTo(page, data) {
                eventBus.dataCarrier(data);
                // this.$emit('dataCarry', data);
                this.$emit('goTo', page);
            }
        }
    }
</script>
