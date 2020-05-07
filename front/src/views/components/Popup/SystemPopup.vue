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
                    {{ title }}
                </v-card-title>
                <v-card-text>
                    <v-form>
                        <v-col>
                            <v-row v-for="field in fields" :key="field.value">

                                <template v-if="update">
                                    <template v-if="field.updateType === 'disabled'">
                                        <v-text-field
                                            :value="newValue[field.value]"
                                            :label="field.text"
                                            disabled
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.updateType === 'text'">
                                        <v-text-field
                                            :value="newValue[field.value]"
                                            :label="field.text"
                                            single-line
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.updateType === 'select'">
                                        <v-autocomplete
                                            :items="field.selectItems"
                                            color="white"
                                            item-value="value"
                                            item-text="text"
                                            :label="field.text"
                                            v-model="newValue[field.value]"
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
                                            :value="newValue[field.value]"
                                        ></v-textarea>
                                    </template>
                                </template>

                                <template v-else>
                                    <template v-if="field.insertType === 'text'">
                                        <v-text-field
                                            :value="newValue[field.value]"
                                            :label="field.text"
                                            single-line
                                        ></v-text-field>
                                    </template>
                                    <template v-else-if="field.insertType === 'select'">
                                        <v-autocomplete
                                            :items="field.selectItems"
                                            color="white"
                                            item-value="value"
                                            item-text="text"
                                            :label="field.text"
                                            v-model="newValue[field.value]"
                                        ></v-autocomplete>
                                    </template>
                                    <template v-else-if="field.insertType === 'switch'">
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
                                                :value="newValue[field.value]"
                                        ></v-textarea>
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
                        @click="okAction"
                    >
                        {{ okBtnText }}
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
            }
        },
        mounted() {
            this.initializeNewValue();
            this.setNewValue();
        },
        data() {
            return {
                newValue: {}
            }
        },
        watch: {
            values: {
                handler() {
                    this.setNewValue();
                },
                deep: true
            }
        },
        computed: {
            thisShow() {
                return this.show;
            }
        },
        methods: {
            initializeNewValue() {
                if (this.fields.length === 0) return;

                this.fields.filter(v => v?.type).forEach(v => {
                    this.newValue[v.value] = this.returnDefaultVal(v.type);
                });
            },
            returnDefaultVal(type = null) {
                switch (type) {
                    case 'string':
                        return '';
                    case 'number':
                        return 0;
                    case 'boolean':
                        return false;
                    default:
                        return null;
                }
            },
            setNewValue() {
                if (this.update && this.values) {
                    Object.assign(this.newValue, this.values);
                }
            },
            okAction() {
                this.$emit('okAction', true);
            },
            cancelAction() {
                this.$emit('cancelAction', false);
            }
        }
    }
</script>