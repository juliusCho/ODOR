<template>
    <div>
    </div>
</template>

<script>
    export default {
        name: "MyPage",
        data() {
            return {
            };
        },
        mounted() {
            this.checkSession();
        },
        methods: {
            checkSession() {
                if (!SCRIPT_VALIDATOR.nullCheck(TMP_SESSION.getId())) {
                    this.goTo('Login');
                }
            },
            goTo(page) {
                this.$emit('goTo', page);
            }
        }
    }
</script>

<style scoped>

</style>