<template>
    <div>
        <v-dialog
            v-model="thisShow"
            :width="width"
            persistent
        >

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
                                :value="selected.messageId"
                                label="Message ID"
                                :disabled="!textEdit"
                            ></v-text-field>
                        </v-row>
                        <v-row>
                            <v-text-field
                                :value="selected.koMessage"
                                label="Korean"
                                :disabled="!textEdit || selected.countryCode === 'ENG'"
                            ></v-text-field>
                        </v-row>
                        <v-row>
                            <v-text-field
                                :value="selected.engMessage"
                                label="English"
                                :disabled="!textEdit || selected.countryCode === 'KO'"
                            ></v-text-field>
                        </v-row>
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
                            color="primary"
                            text
                            @click="addCheck"
                            :disabled="blockAdd"
                    >
                        Add New
                    </v-btn>
                    <v-btn
                            color="green darken-1"
                            text
                            @click="selectAction"
                    >
                        Select
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    export default {
        name: "SystemPopup",
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
                searchKeys: {
                    messageId: '', message: ''
                },
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
                blockAdd: true,
                textEdit: true
            }
        },
        mounted() {
            if (!this.selected.messageId) {
                this.setSelected();
                return;
            }
            this.listSelected = [];
            this.listSelected.push(this.selected);
        },
        watch: {
            messageId() {
                this.setSelected();
            },
            listSelected: {
                handler() {
                    if (this.listSelected.length === 0) return;

                    this.selected = this.listSelected[0];
                    let locale = this.selected.countryCode === 'ENG' ? 'engMessage' : 'koMessage';
                    this.selected[locale] = this.selected.message;

                    this.editable(false);
                },
                deep: true
            }
        },
        computed: {
            thisShow() {
                return this.show;
            },
            messageList() {
                return MESSAGE?.getMessageList();
            },
            searchMessageList() {
                return MESSAGE?.getMessageList(true);
            }
        },
        methods: {
            setSelected() {
                if (this.messageId) {
                    this.selected = MESSAGE.getMessageData(this.messageId);
                    let locale = this.selected.countryCode === 'ENG' ? 'engMessage' : 'koMessage';
                    this.selected[locale] = this.selected.message;
                } else {
                    this.setDefaultVal();
                }
                this.editable(false);
            },
            setDefaultVal() {
                this.selected = {messageId: '', message: '', countryCode: '', koMessage: '', engMessage: ''};
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

            },
            addAction() {
                this.$emit('addAction', true);
            },
            searchAction() {

            },
            selectAction() {
                if (this.listSelected.length === 0) return;

                this.$emit('selectAction', true);
            },
            cancelAction() {
                this.$emit('cancelAction', false);
            }
        }
    }
</script>