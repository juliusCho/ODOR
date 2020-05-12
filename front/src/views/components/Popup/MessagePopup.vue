<template>
    <v-dialog
        v-model="thisShow"
        :width="width"
        persistent
    >

        <RightTopAlert
                :status="alertStatus"
                :msg="alertMsg"
                :show="alertShow"
                @hideDisplay="alertShow = false"
        />

        <v-card>
            <v-card-title
                class="headline grey lighten-2"
                primary-title
            >
                {{ '공통메세지선택' }}
            </v-card-title>
        </v-card>
        <v-card class="d-flex">
            <v-card>
                <v-card-text>
                    <v-row>
                        <v-col>
                            <v-autocomplete
                                :items="searchMessageList"
                                color="white"
                                item-value="messageId"
                                item-text="messageId"
                                :label="'Message ID'"
                                v-model="searchKeys.messageId"
                            ></v-autocomplete>
                        </v-col>
                        <v-col>
                            <v-autocomplete
                                :items="searchMessageList"
                                color="white"
                                item-value="message"
                                item-text="message"
                                :label="'Message'"
                                v-model="searchKeys.message"
                            ></v-autocomplete>
                        </v-col>
                        <v-col>
                            <v-btn @click="searchAction" color="teal lighten-5">
                                Search
                            </v-btn>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-data-table
                            :headers="headers"
                            :items="messageList"
                            item-key="messageId"
                            v-model="listSelected"
                            show-select
                            single-select
                            height="250px"
                            @click:row="listSelected = [$event]"
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
                                    <td>{{ props.item.message }}</td>
                                </tr>
                            </template>
                        </v-data-table>
                    </v-row>
                </v-card-text>
            </v-card>
            <v-card width="350px">
                <v-card-text>
                    <v-form v-model="valid">
                        <v-row>
                            <v-btn
                                    @click="addNew"
                                    color="blue lighten-5"
                            >
                                Add New
                            </v-btn>
                        </v-row>
                        <v-row>
                            <v-spacer/>
                            <v-divider/>
                            <v-spacer/>
                        </v-row>
                        <v-row>
                            <v-text-field
                                v-model="selected.messageId"
                                label="Message ID"
                                :disabled="!textEdit"
                                required
                                :counter="20"
                                :rules="idRules"
                            ></v-text-field>
                        </v-row>
                        <v-row>
                            <v-text-field
                                v-model="selected.koMessage"
                                label="Korean"
                                :disabled="!textEdit || selected.locale === 'ENG'"
                                required
                                :rules="msgRules"
                                :counter="200"
                            ></v-text-field>
                        </v-row>
                        <v-row>
                            <v-text-field
                                v-model="selected.engMessage"
                                label="English"
                                :disabled="!textEdit || selected.locale === 'KO'"
                                required
                                :rules="msgRules"
                                :counter="200"
                            ></v-text-field>
                        </v-row>
                    </v-form>
                </v-card-text>
            </v-card>
        </v-card>
        <v-divider/>
        <v-card>
            <v-card-actions>
                <v-spacer/>
                <v-btn
                        color="primary"
                        text
                        @click="cancelAction"
                >
                    Cancel
                </v-btn>
                <v-btn
                        color="green darken-1"
                        text
                        @click="addCheck"
                        :disabled="blockAdd || !valid"
                >
                    Add New
                </v-btn>
                <v-btn
                        color="green darken-1"
                        text
                        @click="selectAction"
                        :disabled="!blockAdd"
                >
                    Select
                </v-btn>
            </v-card-actions>
        </v-card>

        <ConfirmDialog
            :show="confirmShow"
            :title="'확인'"
            :content="'저장 ㄱ?'"
            type="C"
            :yesBtnText="'ㅇㅇ'"
            :noBtnText="'ㄴㄴ'"
            :width="500"
            @yesAction="createMessage"
            @noAction="confirmShow = false"
        />
    </v-dialog>
</template>

<script>
    import axios from 'axios';
    import RightTopAlert from "@/views/components/RightTopAlert";
    import ConfirmDialog from "@/views/components/Dialog";

    export default {
        name: "SystemPopup",
        components: {
            RightTopAlert,
            ConfirmDialog
        },
        props: {
            show: {
                type: Boolean,
                default: false,
                required: false
            },
            messageId: {
                type: String,
                default: '',
                required: false
            },
            width: {
                type: Number,
                default: 500,
                required: false
            }
        },
        data() {
            return {
                thisShow: false,

                searchKeys: {
                    messageId: '', message: ''
                },
                messageList: [],
                selected: {},
                headers: [
                    {
                        text: 'ID',
                        value: 'messageId',
                        width: '100px'
                    },
                    {
                        text: 'Message',
                        value: 'message',
                        width: '100px'
                    }
                ],
                listSelected: [],
                valid: false,
                idRules: SCRIPT_VALIDATOR.idRules(),
                msgRules: SCRIPT_VALIDATOR.textRules(200),

                blockAdd: true,
                textEdit: false,

                alertStatus: 'warning',
                alertMsg: '경고',
                alertShow: false,

                confirmShow: false
            }
        },
        mounted() {
            this.messageList = MESSAGE?.getMessageList();

            if (!this.selected.messageId) {
                this.setSelected();
                return;
            }
            this.listSelected = [];
            this.listSelected.push(this.selected);
        },
        watch: {
            show() {
                this.thisShow = this.show;
            },
            messageId() {
                this.setSelected();
            },
            listSelected: {
                handler() {
                    if (this.listSelected?.length !== 1) {
                        this.setDefaultVal();
                        return;
                    }
                    this.selected = this.listSelected[0];
                    let locale = this.selected.locale === 'ENG' ? 'engMessage' : 'koMessage';
                    this.selected[locale] = this.selected.message;

                    this.editable(false);
                },
                deep: true
            }
        },
        computed: {
            searchMessageList() {
                return MESSAGE?.getMessageList(true);
            }
        },
        methods: {
            setSelected() {
                if (this.messageId) {
                    let selected = MESSAGE.getMessageData(this.messageId);
                    let locale = selected.countryCode === 'ENG' ? 'engMessage' : 'koMessage';
                    selected[locale] = selected.message;

                    this.listSelected = [];
                    this.listSelected.push(selected);
                } else {
                    this.setDefaultVal();
                }
                this.editable(false);
            },
            setDefaultVal() {
                this.selected = {messageId: '', message: '', locale: '', koMessage: '', engMessage: ''};
            },
            addNew() {
                this.listSelected = [];
                this.setDefaultVal();
                this.editable(true);
            },
            editable(boo) {
                this.textEdit = boo;
                this.blockAdd = !boo;
            },
            addCheck() {
                if (this.duplicateValidation()) {
                    this.alertStatus = 'warning';
                    this.alertMsg = 'ID가 중복되면 쓰나';
                    this.alertShow = true;
                    return;
                }
                this.confirmShow = true;
            },
            duplicateValidation() {
                return MESSAGE.getMessageList().some(v => v.messageId === this.selected.messageId);
            },
            createMessage() {
                let list = [
                    {messageId: this.selected.messageId, countryCode: 'KO', message: this.selected.koMessage},
                    {messageId: this.selected.messageId, countryCode: 'ENG', message: this.selected.engMessage}
                ];

                axios.put(API.MessageMgmtController.insertMessage, list)
                .then(res => {
                    if (res.data === 'success') {
                        this.addNewMessageToCommonList(list);

                        let message = this.$i18n.locale === 'KO' ? this.selected.koMessage : this.selected.engMessage;
                        this.$emit('selectAction', Object.assign(this.selected, {message: message}));
                        this.cancelAction();
                    }
                });
            },
            addNewMessageToCommonList(list) {
                let newMsg = list.find(v => v.countryCode === this.$i18n.locale);
                this.messageList.push({messageId: newMsg.messageId, message: newMsg.message, countryCode: newMsg.countryCode});
                MESSAGE.setMessageList(this.messageList);
            },
            addAction() {
                this.$emit('addAction', this.selected);
            },
            searchAction() {
                this.setSelected();

                if (!this.searchKeys.messageId && !this.searchKeys.message) {
                    this.messageList = MESSAGE.getMessageList();
                }

                axios.post(
                    API.MessageMgmtController.getMessageList,
                    Object.assign(this.searchKeys, {countryCode: this.$i18n.locale})
                )
                .then(res => {
                    this.messageList = res.data;
                });
            },
            selectAction() {
                if (this.listSelected.length === 0) {
                    this.alertStatus = 'warning';
                    this.alertMsg = '선택된 메시지가 없다';
                    this.alertShow = true;
                    return;
                }
                this.$emit('selectAction', this.listSelected[0]);
                this.cancelAction();
            },
            cancelAction() {
                this.$emit('cancelAction');
                this.reinitialize();
            },
            reinitialize() {
                this.selected = {};
                this.listSelected = [];
                this.searchKeys = {messageId: '', message: ''};
                this.editable(false);
            }
        }
    }
</script>