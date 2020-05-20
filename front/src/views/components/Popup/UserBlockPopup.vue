<template>
    <div>
        <v-dialog
                v-model="thisShow"
                width="400"
                persistent
        >
            <v-card>
                <v-card-title
                        class="headline grey lighten-2"
                        primary-title
                >
                    {{ '사용자 활동 정지 처리' }}
                </v-card-title>
                <v-card-text>
                    <v-row>
                        <p class="subtitle-1" style="margin-top: 10px; margin-left: 10px;">
                            {{'ID : ' + user.userId}}
                        </p>
                    </v-row>
                    <v-row>
                        <p class="subtitle-1" style="margin-left: 10px;">
                            {{'Email : ' + user.email}}
                        </p>
                    </v-row>
                    <v-row style="justify-content: center;">
                        <v-form v-model="valid" style="width: 90%;">
                            <v-row>
                                <v-autocomplete
                                        :items="reasonCodeList"
                                        color="white"
                                        item-value="codeId"
                                        item-text="codeName"
                                        :label="'Reason'"
                                        v-model="user.reasonCode"
                                        :rules="selectRules"
                                        :readonly="blockYn === 'N'"
                                ></v-autocomplete>
                            </v-row>
                            <v-row style="margin-top: 10px;">
                                <v-textarea
                                        outlined
                                        name="input-7-4"
                                        label="Detail Reason"
                                        v-model="user.reason"
                                        :counter="9999"
                                        :readonly="blockYn === 'N'"
                                ></v-textarea>
                            </v-row>
                        </v-form>
                    </v-row>
                </v-card-text>

                <v-card-actions>
                    <v-spacer/>
                    <v-btn
                            color="primary"
                            text
                            @click="cancelAction"
                    >
                        Cancel
                    </v-btn>
                    <v-btn  v-if="blockYn === 'Y'"
                            color="green darken-1"
                            text
                            @click="okConfirm"
                            :disabled="!valid"
                    >
                        Ok
                    </v-btn>
                    <v-btn  v-else
                            color="green darken-1"
                            text
                            @click="unblock"
                    >
                        Unblock
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>

        <ConfirmDialog
                :show="confirmShow"
                :title="'확인'"
                :content="'저장ㄱ?'"
                type="C"
                :yesBtnText="'ㅇㅇ'"
                :noBtnText="'ㄴㄴ'"
                :width="500"
                @yesAction="okAction"
                @noAction="confirmShow = false"
        />
    </div>
</template>

<script>
    import ConfirmDialog from "@/views/components/Dialog";
    import axios from 'axios';

    export default {
        name: "UserBlockPopup",
        components: {
            ConfirmDialog
        },
        props: {
            show: {
                type: Boolean,
                default: false,
                required: false
            },
            userId: {
                type: String,
                default: '',
                required: true
            },
            email: {
                type: String,
                default: '',
                required: true
            },
            blockYn: {
                type: String,
                default: 'Y',
                required: false
            }
        },
        data() {
            return {
                thisShow: false,

                reasonCodeList: CODE.getCodeList('BLOCK_REASON'),
                selectRules: SCRIPT_VALIDATOR.selectRules(),

                user: {
                    userId: '',
                    email: '',
                    reasonCode: '',
                    reason: ''
                },

                valid: false,
                confirmShow: false
            }
        },
        watch: {
            show() {
                this.thisShow = this.show;

                if (!this.show) {
                    this.initialize();
                    return;
                }

                this.setBlockInfo();
            }
        },
        methods: {
            initialize() {
                this.user = {
                    userId: '',
                    email: '',
                    reasonCode: '',
                    reason: ''
                };
            },
            setBlockInfo() {
                if (SCRIPT_VALIDATOR.nullCheck(
                    this.userId,
                    this.email
                )) {
                    this.user.userId = this.userId;
                    this.user.email = this.email;
                }
                if (this.blockYn === 'Y') return;

                axios.post(API.UserMgmtController.getBlockInfo, this.user)
                .then(res => {
                    Object.assign(this.user, res.data);
                });
            },
            cancelAction() {
                this.initialize();
                this.$emit('cancelAction');
            },
            okConfirm() {
                this.confirmShow = true;
            },
            okAction() {
                this.confirmShow = false;

                axios.put(API.UserMgmtController.blockUser, this.user)
                .then(() => {
                    this.cancelAction();
                    this.$emit('okAction');
                });
            },
            unblock() {
                this.$emit('unblock');
            }
        }
    }
</script>