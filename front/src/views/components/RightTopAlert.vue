<template>
    <v-row justify="center"
         v-if="show">
        <v-col md="3">
            <v-alert
                    :mode="mode"
                    :type="status"
                    transition="scale-transition"
            >
                {{msg}}
            </v-alert>
        </v-col>
    </v-row>
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
                default: 3000,
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

</style>