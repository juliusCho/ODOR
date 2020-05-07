<template>
    <div class="text-center">
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
                    {{ content }}
                </v-card-text>

                <v-divider/>

                <v-card-actions v-if="type === 'C'">
                    <v-spacer/>
                    <v-btn
                        color="primary"
                        text
                        @click="noAction"
                    >
                        {{ noBtnText }}
                    </v-btn>
                    <v-btn
                        color="green darken-1"
                        text
                        @click="yesAction"
                    >
                        {{ yesBtnText }}
                    </v-btn>
                </v-card-actions>

                <v-card-actions v-else>
                    <v-spacer/>
                    <v-btn
                        color="primary"
                        fab
                        @click="okAction"
                    >
                        {{ btnText }}
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    export default {
        name: "Dialog",
        props: {
            show: {
                type: Boolean,
                default: false,
                required: false
            },
            type: {
                type: String,
                default: 'A',   // A: Alert / C: Confirm
                required: false
            },
            title: {
                type: String,
                default: '제목임당',
                required: false
            },
            content: {
                type: String,
                default: '내용임당',
                required: false
            },
            btnText: {
                type: String,
                default: '확인버튼',
                required: false
            },
            yesBtnText: {
                type: String,
                default: '예버튼',
                required: false
            },
            noBtnText: {
                type: String,
                default: '아니오버튼',
                required: false
            },
            width: {
                type: Number,
                default: 500,
                required: false
            }
        },
        computed: {
            thisShow() {
                return this.show;
            }
        },
        methods: {
            okAction() {
                this.$emit('okAction');
            },
            yesAction() {
                this.$emit('yesAction', true);
            },
            noAction() {
                this.$emit('noAction', false);
            }
        }
    }
</script>