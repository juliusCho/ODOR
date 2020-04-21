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
        data() {
            return {
                incl: 'Home',
                excl: 'MyPage'
            }
        },
        methods: {
            async goTo(page) {
                if (page === '') {
                    this.$emit('goTo', 'Home');
                    return;
                }

                if (this.checkRest()) {
                    this.$router.push(page).catch(() => {});
                    this.$emit('goTo', page);
                    return;
                }

                await axios.post(
                    API.SessionController.sessionCheck,
                    {sessionToken: TMP_SESSION.getId()}
                )
                .then((res) => {
                    if (res.data) {
                        this.$router.push(page).catch(() => {});
                        this.$emit('goTo', page);
                        return;
                    }
                    this.$router.push(this.incl).catch(() => {});
                    this.$emit('goTo', this.incl);
                });
            },
            checkRest() {
                let result = false;

                if (this.routing === this.incl) {
                    return true;
                }

                for (let i = 0, ii = MEMBERSHIP_PAGES.length; i < ii; i++) {
                    if (
                        MEMBERSHIP_PAGES[i] !== this.excl &&
                        MEMBERSHIP_PAGES[i] === this.routing
                    ) {
                        result = true;
                        break;
                    }
                }
                return result;
            },
            loggedIn(boo) {
                this.$emit('loggedIn', boo);
            }
        }
    }
</script>

<style scoped>

</style>