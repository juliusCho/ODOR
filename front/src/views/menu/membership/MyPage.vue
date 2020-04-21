<template>
    <div>
        <v-btn @click="logout">Logout</v-btn>
    </div>
</template>

<script>
    import axios from 'axios';

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
                    this.$emit('loggedIn', false);
                    this.goTo('Login');
                }
            },
            goTo(page) {
                this.$emit('goTo', page);
            },
            logout() {
                axios.get(API.SessionController.logout, {})
                .then(() => {
                    TMP_SESSION.setId('');
                    this.$emit('loggedIn', false);
                    this.goTo('Home');
                });
            }
        }
    }
</script>

<style scoped>

</style>