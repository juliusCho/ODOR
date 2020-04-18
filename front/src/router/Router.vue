<template>
    <v-content>
        <template v-if="routing === 'Home'">
            <VuetifyHelloWorld/>
        </template>
        <template v-else-if="routing === 'Login'">
            <Login @goTo="goTo"/>
        </template>
        <template v-else-if="routing === 'IdPwFinder'">
            <IdPwFinder @goTo="goTo"/>
        </template>
    </v-content>
</template>

<script>
    import VuetifyHelloWorld from '@/views/components/VuetifyHelloWorld';
    import Login from '@/views/menu/Login';
    import IdPwFinder from "@/views/menu/IdPwFinder";
    import axios from "axios";

    export default {
        name: "Router",
        components: {
            VuetifyHelloWorld,
            Login,
            IdPwFinder
        },
        props: {
            routing: {
                type: String,
                default: '',
                required: false
            }
        },
        data() {
            return {
                tmpSession: {tokenKey: 'XX'}
            }
        },
        methods: {
            goTo(page) {
                if (page === '') {
                    this.$emit('goTo', 'Home');
                    return;
                }

                axios.post(
                    API.SessionController.sessionCheck,
                    {sessionToken: page}
                )
                .then((res) => {
                    if (this.tmpSession.tokenKey === res.data) {
                        this.$router.push(page).catch(() => {});
                        this.$emit('goTo', page);
                    } else {
                        console.log('권한이 없다 아이가');
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>