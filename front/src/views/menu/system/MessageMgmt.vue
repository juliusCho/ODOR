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
                        :items="searchCombos.messageId"
                        color="white"
                        label="MessageId"
                        v-model="searchKeys.messageId"
                    ></v-autocomplete>
                </v-col>
                <v-col>
                    <v-autocomplete
                        :items="searchCombos.message"
                        color="white"
                        label="Message"
                        v-model="searchKeys.message"
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
                @search="getMessageList"
                @add="addConfirm"
                @update="updateConfirm"
                @delete="deleteConfirm"
            />
        </v-form>
        <v-data-table
            :headers="headers"
            :items="messageList"
            item-key="messageId"
            v-model="selectedMessage"
            show-select
            single-select
            @click:row="selectedMessage = [$event]"
        >
            <template slot="items" slot-scope="props">
                <tr >
                    <td>
                        <v-checkbox
                                v-model="props.selected"
                                hide-details
                        ></v-checkbox>
                    </td>
                    <td>{{ props.item.messageId }}</td>
                    <td>{{ props.item.koMessage }}</td>
                    <td>{{ props.item.engMessage }}</td>
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
            :title="'메세지수정'"
            :update="true"
            :okBtnText="'ㄱㄱ'"
            :cancelBtnText="'ㄴㄴ'"
            :width="1000"
            :fields="headers"
            :values="selectedMessage[0]"
            @okAction="updateItem"
            @cancelAction="updatePopShow = false"
        />

        <InsertPopup
            ref="insertPopup"
            :show="insertPopShow"
            :title="'메세지생성'"
            :okBtnText="'ㄱㄱ'"
            :cancelBtnText="'ㄴㄴ'"
            :width="1000"
            :fields="headers"
            :validation="duplicateValidation"
            :invalidMsg="'메세지ID중복!!'"
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
    import {eventBus} from "@/main";

    export default {
        name: 'MessageMgmt',
        components: {
            SystemBtn,
            DeleteDialog,
            UpdatePopup,
            InsertPopup,
            RightTopAlert
        },
        mounted() {
            this.initializeSearchCombos();
            this.getMessageList();
        },
        data() {
            return {
                searchKeys: {
                    messageId: '',
                    message: '',
                    useYn: true
                },
                searchCombos: {
                    messageId: [''],
                    message: [''],
                    useYn: CODE.getCodeList('USE_YN')
                },


                headers: [
                    {
                        text: 'Message ID',
                        value: 'messageId',
                        width: '180px',
                        type: 'string',
                        updateType: 'disabled',
                        insertType: 'id'
                    },
                    {
                        text: '한글',
                        value: 'koMessage',
                        width: '180px',
                        type: 'string',
                        updateType: 'text',
                        insertType: 'text'
                    },
                    {
                        text: 'English',
                        value: 'engMessage',
                        width: '180px',
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
                messageList: [],
                selectedMessage: [],


                confirmShow: false,
                updatePopShow: false,
                insertPopShow: false,

                alertShow: false,
                alertMsg: '',
                alertStatus: ''
            };
        },
        methods: {
            initializeSearchCombos() {
                let msgList = MESSAGE.getMessageList(true);
                let messageId = msgList.map(v => v.messageId);
                let message = msgList.map(v => v.message);
                message[0] = '';

                this.searchCombos.messageId = messageId;
                this.searchCombos.message = message;

                this.searchKeys = {
                    messageId: '',
                    message: '',
                    useYn: true
                };
            },
            getMessageList() {
                this.selectedMessage = [];

                axios.post(
                    API.MessageMgmtController.getMessageListSystem,
                    this.searchKeys
                ).then(res => {
                    this.messageList = res.data;
                });
            },
            async resetComMessages() {
                await eventBus.setMessageList();
                this.initializeSearchCombos();
                this.getMessageList();
            },
            updateConfirm() {
                this.updatePopShow = this.selectedMessage.length !== 0;
            },
            deleteConfirm() {
                this.confirmShow = this.selectedMessage.length !== 0;
            },
            deleteItem() {
                axios.patch(
                    API.MessageMgmtController.deleteMessageGroup,
                    this.selectedMessage[0]
                )
                    .then(res => {
                        this.confirmShow = false;
                        this.doneAlert(res.data);
                        this.getMessageList();
                        this.resetComMessages();
                    });
            },
            addConfirm() {
                this.insertPopShow = true;
            },
            async duplicateValidation(data) {
                let result = true;

                await axios.post(
                    API.MessageMgmtController.checkDuplication,
                    data
                )
                    .then(res => {
                        result = res.data === 0;
                    });
                return result;
            },
            addItem(data) {
                let list = this.convertToList(data);
                axios.put(API.MessageMgmtController.insertMessage, list)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.getMessageList();
                        this.resetComMessages();
                    });
            },
            convertToList(data) {
                let {messageId, koMessage, engMessage, useYn} = data;
                return [
                    {messageId, countryCode: 'KO', message: koMessage, useYn: useYn === undefined ? true : useYn},
                    {messageId, countryCode: 'ENG', message: engMessage, useYn: useYn === undefined ? true : useYn}
                ];
            },
            updateItem(data) {
                let list = this.convertToList(data);
                axios.patch(API.MessageMgmtController.updateMessage, list)
                    .then(res => {
                        this.doneAlert(res.data);
                        this.getMessageList();
                        this.resetComMessages();
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
