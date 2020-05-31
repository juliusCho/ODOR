<template>
    <div>
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
                    {{ title }}
                </v-card-title>
                <v-card-text>
                    <v-form v-model="valid">
                        <v-col>
                            <v-row v-for="field in fields" :key="field.value">

                                <template v-if="update">
                                    <template v-if="field.updateType === 'disabled'">
                                        <v-text-field
                                            v-model="newValue[field.value]"
                                            :label="field.text"
                                            readonly
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.updateType === 'id'">
                                        <v-text-field
                                            v-model="newValue[field.value]"
                                            :label="field.text"
                                            :counter="20"
                                            :rules="field.noRequire ? [true] : idRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.updateType === 'text'">
                                        <v-text-field
                                            v-model="newValue[field.value]"
                                            :label="field.text"
                                            :counter="200"
                                            :rules="field.noRequire ? [true] : textRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.updateType === 'number'">
                                        <v-text-field
                                            v-model="newValue[field.value]"
                                            :label="field.text"
                                            type="number"
                                            :counter="4"
                                            :rules="field.noRequire ? [true] : numRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.updateType === 'year'">
                                        <YearPicker
                                                :date="newValue[field.value]"
                                                @bind="date => {newValue[field.value] = date}"
                                                :label="field.text"
                                                :rules="field.noRequire ? [true] : numRules"
                                        >
                                        </YearPicker>
                                    </template>
                                    <template v-else-if="field.updateType === 'name'">
                                        <v-text-field
                                            v-model="newValue[field.value]"
                                            :label="field.text"
                                            :counter="500"
                                            :rules="field.noRequire ? [true] : nameRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.updateType === 'email'">
                                        <v-text-field
                                            v-model="newValue[field.value]"
                                            :label="field.text"
                                            :counter="50"
                                            :rules="field.noRequire ? [true] : emailRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.updateType === 'url'">
                                        <v-text-field
                                            v-model="newValue[field.value]"
                                            :label="field.text"
                                            :counter="1000"
                                            :rules="field.noRequire ? [true] : urlRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.updateType === 'select'">
                                        <v-autocomplete
                                            :items="field.selectItems"
                                            color="white"
                                            item-value="value"
                                            item-text="text"
                                            :label="field.text"
                                            v-model="newValue[field.selectKey]"
                                            :rules="field.noRequire ? [true] : selectRules"
                                        ></v-autocomplete>
                                    </template>
                                    <template v-else-if="field.updateType === 'switch'">
                                        <v-switch
                                            v-model="newValue[field.value]"
                                            class="ma-2"
                                            :label="field.text"
                                        ></v-switch>
                                    </template>
                                    <template v-else-if="field.updateType === 'textarea'">
                                        <v-textarea
                                            outlined
                                            name="input-7-4"
                                            :label="field.text"
                                            v-model="newValue[field.value]"
                                            :counter="3000"
                                            :rules="field.noRequire ? [true] : descRules"
                                        ></v-textarea>
                                    </template>
                                    <template v-else-if="field.updateType === 'content'">
                                        <v-textarea
                                            outlined
                                            name="input-7-4"
                                            :label="field.text"
                                            v-model="newValue[field.value]"
                                            :counter="9999"
                                            :rules="field.noRequire ? [true] : contentRules"
                                        ></v-textarea>
                                    </template>
                                    <template v-else-if="field.updateType === 'message'">
                                        <v-chip class="ma-2">
                                            [MSG ID]: {{newValue[field.value]}} / [MSG]: {{newValue.localeMessage}}
                                        </v-chip>
                                        <v-btn
                                            color="primary"
                                            @click="selectMessage(newValue[field.value], field.value)"
                                        >
                                            {{'메세지선택한당'}}
                                        </v-btn>
                                        <v-input
                                            v-model="newValue[field.value]"
                                            :rules="field.noRequire ? [true] : selectRules"
                                            style="display: none;"
                                        />
                                    </template>
                                </template>

                                <template v-else>
                                    <template v-if="field.insertType === 'id'">
                                        <v-text-field
                                            v-model="newValue[field.value]"
                                            :label="field.text"
                                            :counter="20"
                                            :rules="field.noRequire ? [true] : idRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-if="field.insertType === 'text'">
                                        <v-text-field
                                            v-model="newValue[field.value]"
                                            :label="field.text"
                                            :counter="200"
                                            :rules="field.noRequire ? [true] : textRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.insertType === 'number'">
                                        <v-text-field
                                                v-model="newValue[field.value]"
                                                :label="field.text"
                                                type="number"
                                                :counter="4"
                                                :rules="field.noRequire ? [true] : numRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.insertType === 'year'">
                                        <YearPicker
                                                :date="newValue[field.value]"
                                                @bind="date => {newValue[field.value] = date}"
                                                :label="field.text"
                                                :rules="field.noRequire ? [true] : numRules"
                                        >
                                        </YearPicker>
                                    </template>
                                    <template v-if="field.insertType === 'name'">
                                        <v-text-field
                                            v-model="newValue[field.value]"
                                            :label="field.text"
                                            :counter="500"
                                            :rules="field.noRequire ? [true] : nameRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.insertType === 'email'">
                                        <v-text-field
                                                v-model="newValue[field.value]"
                                                :label="field.text"
                                                :counter="50"
                                                :rules="field.noRequire ? [true] : emailRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.insertType === 'url'">
                                        <v-text-field
                                                v-model="newValue[field.value]"
                                                :label="field.text"
                                                :counter="1000"
                                                :rules="field.noRequire ? [true] : urlRules"
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.insertType === 'select'">
                                        <v-autocomplete
                                            :items="field.selectItems"
                                            color="white"
                                            item-value="value"
                                            item-text="text"
                                            :label="field.text"
                                            v-model="newValue[field.selectKey]"
                                            :rules="field.noRequire ? [true] : selectRules"
                                        ></v-autocomplete>
                                    </template>
                                    <template v-else-if="field.insertType === 'switch'">
                                        <v-switch
                                            v-model="newValue[field.value]"
                                            class="ma-2"
                                            :label="field.text"
                                        ></v-switch>
                                    </template>
                                    <template v-else-if="field.insertType === 'textarea'">
                                        <v-textarea
                                            outlined
                                            name="input-7-4"
                                            :label="field.text"
                                            v-model="newValue[field.value]"
                                            :counter="3000"
                                            :rules="field.noRequire ? [true] : descRules"
                                        ></v-textarea>
                                    </template>
                                    <template v-else-if="field.insertType === 'content'">
                                        <v-textarea
                                            outlined
                                            name="input-7-4"
                                            :label="field.text"
                                            v-model="newValue[field.value]"
                                            :counter="9999"
                                            :rules="field.noRequire ? [true] : contentRules"
                                        ></v-textarea>
                                    </template>
                                    <template v-else-if="field.insertType === 'message'">
                                        <v-chip class="ma-2">
                                            [MSG ID]: {{newValue[field.value] || 'Needed'}} / [MSG]: {{newValue.localeMessage || 'Needed'}}
                                        </v-chip>
                                        <v-btn
                                                color="primary"
                                                @click="selectMessage(newValue[field.value], field.value)"
                                        >
                                            {{'메세지선택한당'}}
                                        </v-btn>
                                        <v-input
                                            v-model="newValue[field.value]"
                                            :rules="field.noRequire ? [true] : selectRules"
                                            style="display: none;"
                                        />
                                    </template>
                                </template>

                            </v-row>
                        </v-col>
                    </v-form>
                </v-card-text>

                <v-divider/>

                <v-card-actions>
                    <v-spacer/>
                    <v-btn
                        color="primary"
                        text
                        @click="cancelAction"
                    >
                        {{ cancelBtnText }}
                    </v-btn>
                    <v-btn
                        color="green darken-1"
                        text
                        @click="okConfirm"
                        :disabled="!valid"
                    >
                        {{ okBtnText }}
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>

        <MessagePopup
            :show="messagePopShow"
            :width="900"
            :messageId="messageId"
            @cancelAction="hideMessagePop"
            @selectAction="assignMessage"
        />

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
    import MessagePopup from "@/views/components/Popup/MessagePopup";
    import ConfirmDialog from "@/views/components/Dialog";
    import RightTopAlert from "@/views/components/RightTopAlert";
    import YearPicker from "@/views/components/YearPicker";

    export default {
        name: "SystemPopup",
        components: {
            MessagePopup,
            ConfirmDialog,
            RightTopAlert,
            YearPicker
        },
        props: {
            show: {
                type: Boolean,
                default: false,
                required: false
            },
            update: {
                type: Boolean,
                default: false,
                required: false
            },
            title: {
                type: String,
                default: '제목임당',
                required: false
            },
            fields: {
                type: Array,
                default: () => [],
                required: false
            },
            values: {
                type: Object,
                default: () => {},
                required: false
            },
            itemKey: {
                type: String,
                default: '',
                required: false
            },
            okBtnText: {
                type: String,
                default: '확인버튼',
                required: false
            },
            cancelBtnText: {
                type: String,
                default: '취소버튼',
                required: false
            },
            width: {
                type: Number,
                default: 500,
                required: false
            },
            validation: {
                type: Function,
                default: () => { return true; },
                required: false
            },
            invalidMsg: {
                type: String,
                default: '',
                required: false
            }
        },
        mounted() {
            this.setNewValue();
        },
        data() {
            return {
                thisShow: false,
                newValue: {},

                valid: false,
                checkRules: SCRIPT_VALIDATOR.checkRules(),
                selectRules: SCRIPT_VALIDATOR.selectRules(),
                ageRules: SCRIPT_VALIDATOR.textRules(3),
                sortRules: SCRIPT_VALIDATOR.textRules(10),
                idRules: SCRIPT_VALIDATOR.idRules(),
                textRules: SCRIPT_VALIDATOR.textRules(200),
                numRules: SCRIPT_VALIDATOR.numRules(4),
                nameRules: SCRIPT_VALIDATOR.textRules(500),
                descRules: SCRIPT_VALIDATOR.textRules(3000),
                contentRules: SCRIPT_VALIDATOR.textRules(9999),
                emailRules: SCRIPT_VALIDATOR.emailRules(),
                urlRules: SCRIPT_VALIDATOR.urlRules(),

                messagePopShow: false,
                messageId: '',
                messageFieldName: '',

                alertShow: false,
                alertMsg: '',
                alertStatus: '',

                confirmShow: false
            };
        },
        watch: {
            values: {
                handler() {
                    this.setNewValue();
                },
                deep: true
            },
            show() {
                this.thisShow = this.show;
            }
        },
        methods: {
            hideMessagePop() {
                this.messagePopShow = false;
            },
            assignMessage(msg) {
                this.newValue[this.messageFieldName] = msg.messageId;
                this.newValue.localeMessage = msg.message;
            },
            selectMessage(messageId = '', fieldName = '') {
                this.messagePopShow = true;
                this.messageId = messageId;
                this.messageFieldName = fieldName;
            },


            initializeNewValue() {
                if (this.fields.length === 0) return;

                this.fields.filter(v => v?.type).forEach(v => {
                    this.newValue[v.value] = this.returnDefaultVal(v.type);

                    if (v.selectKey) {
                        this.newValue[v.selectKey] = v.type === 'number' ? 0 : '';
                    }
                });
                this.newValue.localeMessage = '';

                if (this.values) {
                    Object.assign(this.newValue, this.values);
                }
            },
            returnDefaultVal(type = null) {
                switch (type) {
                    case 'string':
                        return '';
                    case 'number':
                        return 0;
                    case 'boolean':
                        return true;
                    default:
                        return null;
                }
            },
            setNewValue() {
                if (this.update && this.values) {
                    Object.assign(this.newValue, this.values);
                } else {
                    this.initializeNewValue();
                }
            },
            async okConfirm() {
                let validResult = await this.validation(this.newValue);
                if (!validResult) {
                    this.alertStatus = 'warning';
                    this.alertMsg = this.invalidMsg;
                    this.alertShow = true;
                    return;
                }
                this.confirmShow = true;
            },
            async okAction() {
                this.confirmShow = false;
                await this.$emit('okAction', this.newValue);
                this.cancelAction();
            },
            cancelAction() {
                this.$emit('cancelAction', false);
                this.setNewValue();
            }
        }
    }
</script>