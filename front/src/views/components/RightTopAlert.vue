<template>
<!--    <div>-->
<!--    <v-snackbar-->
<!--        v-model="alertShow"-->
<!--        v-if="show"-->
<!--        :mode="mode"-->
<!--        :type="status"-->
<!--        transition="scale-transition"-->
<!--    >-->
<!--        {{msg}}-->
<!--    </v-snackbar>-->
<!--    </div>-->
    <div class="overlap">
        <v-row justify="center"
             v-if="show">
            <v-col col="12" md="3">
                <v-alert
                        :mode="mode"
                        :type="status"
                        transition="scale-transition"
                >
                    {{msg}}
                </v-alert>
            </v-col>
        </v-row>
    </div>
</template>

<script>
    export default {
        name: "RightTopAlert",
        props: {
            // info / success / warning / error
            status: {
                type: String,
                default: 'info',
                required: false
            },
            msg: {
                type: String,
                default: '우측상단Alert',
                required: true
            },
            mode: {
                type: String,
                default: 'out-in',
                required: false
            },
            show: {
                type: Boolean,
                default: false,
                required: false
            },
            showTime: {
                type: Number,
                default: 2500,
                required: false
            }
        },
        watch: {
            show() {
                setTimeout(() => {
                    this.$emit('hideDisplay');
                }, this.showTime);
            }
        }
    }
</script>

<style scoped>
    .overlap {
        position: relative;
        z-index: 99;
    }
</style>