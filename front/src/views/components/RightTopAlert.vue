<template>
    <div v-if="show"
         class="float-right"
         style="width: 300px;">
        <v-alert :type="status" :mode="mode">
            {{msg}}
        </v-alert>
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