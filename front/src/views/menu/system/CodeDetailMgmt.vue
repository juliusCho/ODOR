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
                <v-btn @click="goTo('CodeMgmt')" color="primary">
                    Back
                </v-btn>
            </v-row>
            <v-row>
                <v-col>
                    <v-text-field
                        v-model="searchKeys.codeGroupId"
                        :label="'Code Group ID'"
                        readonly
                    ></v-text-field>
                </v-col>
                <v-col>
                    <v-text-field
                        v-model="searchKeys.codeGroupName"
                        :label="'Code Group Name'"
                        readonly
                    ></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <v-autocomplete
                        :items="searchCombos.codeId"
                        color="white"
                        item-value="codeId"
                        item-text="codeName"
                        label="Code Name"
                        v-model="searchKeys.codeId"
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
                @search="getCodeList"
                @add="addConfirm"
                @update="updateConfirm"
                @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
            :headers="headers"
            :items="codeList"
            item-key="codeId"
            v-model="selectedCode"
            show-select
            single-select
            @click:row="selectedCode = [$event]"
            dense
        >
            <template slot="items" slot-scope="props">
                <tr >
                    <td>
                        <v-checkbox
                            v-model="props.selected"
                            hide-details
                        ></v-checkbox>
                    </td>
                    <td>{{ props.item.codeId }}</td>
                    <td>{{ props.item.codeMessage }}</td>
                    <td>{{ props.item.codeName }}</td>
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
            :width="300"
            @yesAction="deleteItem"
            @noAction="confirmShow = false"
        />

        <UpdatePopup
            ref="updatePopup"
            :show="updatePopShow"
            :title="'코드수정'"
            :update="true"
            :okBtnText="'ㄱㄱ'"
            :cancelBtnText="'ㄴㄴ'"
            :width="800"
            :fields="headers"
            :values="selectedCode[0]"
            @okAction="updateItem"
            @cancelAction="updatePopShow = false"
        />

        <InsertPopup
            ref="insertPopup"
            :show="insertPopShow"
            :title="'코드생성'"
            :okBtnText="'ㄱㄱ'"
            :cancelBtnText="'ㄴㄴ'"
            :width="800"
            :fields="headers"
            :values="{codeGroupId: searchKeys.codeGroupId}"
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
    import {eventBus} from "@/store/event-bus";

    export default {
        name: 'CodeDetailMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert
        },
        mounted() {
            ENTER_ACTION(this.getCodeList);
            this.getCodeList();
        },
        data() {
            return {
                searchKeys: {
                    codeGroupId: eventBus.passData?.codeGroupId,
                    codeGroupName: eventBus.passData?.codeGroupName,
                    codeId: '',
                    useYn: true
                },
                searchCombos: {
                    codeId: CODE.getCodeList(eventBus.passData?.codeGroupId, true),
                    useYn: CODE.getCodeList('USE_YN')
                },


                headers: [
                    {
                        text: 'Code ID',
                        value: 'codeId',
                        width: '180px',
                        type: 'string',
                        updateType: 'disabled',
                        insertType: 'id'
                    },
                    {
                        text: 'Code Message',
                        value: 'codeMessage',
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
                        text: 'Code Name',
                        value: 'codeName',
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
                codeList: [],
                selectedCode: [],


                confirmShow: false,
                updatePopShow: false,
                insertPopShow: false,

                alertShow: false,
                alertMsg: '',
                alertStatus: ''
            };
        },
        methods: {
            async getCodeList() {
                this.selectedCode = [];

                await axios.post(
                    API.CodeMgmtController.cruCode,
                    this.searchKeys
                ).then(res => {
                    this.codeList = res.data;
                });
            },
            async resetComCodes() {
                await eventBus.setCodeList();
            },
            updateConfirm() {
                this.updatePopShow = this.selectedCode.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedCode.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.CodeMgmtController.deleteCode,
                    this.selectedCode[0]
                )
                .then(res => {
                    this.confirmShow = false;
                    this.doneAlert(res.data);
                    this.reset();

                });
            },
            reset() {
                this.searchKeys.codeId = '';
                this.searchKeys.useYn = true;
                this.getCodeList();
                this.resetComCodes();
                this.searchCombos = {
                    codeId: CODE.getCodeList(eventBus.passData?.codeGroupId, true),
                    useYn: CODE.getCodeList('USE_YN')
                };
            },
            addConfirm() {
                this.insertPopShow = true;
            },
            async duplicateValidation(data) {
                let result = true;

                await axios.post(
                    API.CodeMgmtController.checkDetailDuplication,
                    data
                )
                .then(res => {
                    result = res.data === 0;
                });
                return result;
            },
            addItem(data) {
                axios.put(API.CodeMgmtController.cruCode, data)
                .then(res => {
                    this.doneAlert(res.data);
                    this.reset();
                });
            },
            updateItem(data) {
                axios.patch(API.CodeMgmtController.cruCode, data)
                .then(res => {
                    this.doneAlert(res.data);
                    this.reset();
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
            goTo(page) {
                this.$emit('goTo', page);
            }
        }
    }
</script>
