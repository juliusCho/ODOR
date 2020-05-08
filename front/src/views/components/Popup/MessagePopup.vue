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
                            <v-row>
                                <v-chip class="ma-2">선택의시간</v-chip>
                            </v-row>
                            <v-row>

                            </v-row>
                        </v-col>
                        <v-divider
                            vertical
                        ></v-divider>
                        <v-col>
                            <v-row>
                                <v-chip class="ma-2">Add New</v-chip>
                                <v-text-field
                                    :value="selected.messageId"
                                    label="Message ID"
                                ></v-text-field>
                            </v-row>
                                <v-text-field
                                    :value="selected.koMessage"
                                    label="Korean"
                                ></v-text-field>
                            <v-row>
                                <v-text-field
                                    :value="selected.engMessage"
                                    label="English"
                                ></v-text-field>
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
                        Cancel
                    </v-btn>
                    <v-btn
                        color="primary"
                        text
                        @click="addCheck"
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
            orgMessage: {
                type: Object,
                default: () => {},
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
                title: '공통메세지선택',
                selected: {}
            }
        },
        watch: {
            orgMessage: {
                handler() {
                    this.setSelected();
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
            setSelected() {
                if (this.orgMessage.localeMessage) {
                    this.selected = this.orgMessage;
                } else {
                    this.selected = {messageId: '', message: ''};
                }
                console.log(this.selected);
            },
            addCheck() {

            },
            addAction() {
                this.$emit('addAction', true);
            },
            selectAction() {
                this.$emit('selectAction', true);
            },
            cancelAction() {
                this.$emit('cancelAction', false);
            }
        }
    }
</script>