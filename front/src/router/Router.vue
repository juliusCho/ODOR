<template>
    <v-content>
        <template v-if="routing === 'Home'">
            <VuetifyHelloWorld/>
        </template>
        <template v-else-if="routing === 'MyPage'">
            <MyPage @goTo="goTo" @loggedIn="loggedIn"/>
        </template>
        <template v-else-if="routing === 'Login'">
            <Login @goTo="goTo" @loggedIn="loggedIn"/>
        </template>
        <template v-else-if="routing === 'IdPwFinder'">
            <IdPwFinder @goTo="goTo"/>
        </template>
    </v-content>
</template>

<script>
    import VuetifyHelloWorld from '@/views/components/VuetifyHelloWorld';
    import MyPage from '@/views/menu/membership/MyPage';
    import Login from '@/views/menu/membership/Login';
    import IdPwFinder from "@/views/menu/membership/IdPwFinder";
    import axios from "axios";

    export default {
        name: "Router",
        components: {
            VuetifyHelloWorld,
            MyPage,
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
        methods: {
            async goTo(page) {
                if (page === '') {
                    this.$emit('goTo', 'Home');
                    return;
                }

                await axios.post(
                    API.SessionController.sessionCheck,
                    {sessionToken: page}
                )
                .then((res) => {
                    console.log(res);
                    this.$router.push(page).catch(() => {});
                    this.$emit('goTo', page);
                });
            },
            loggedIn(boo) {
                this.$emit('loggedIn', boo);
            }
        }
    }
</script>

<style scoped>

</style>