<template>
    <v-content>
        <template v-if="routing === 'Home'">
            <VuetifyHelloWorld/>
        </template>
        <template v-else-if="routing === 'MyPage'">
            <MyPage @goTo="goTo"/>
        </template>
        <template v-else-if="routing === 'IdPwFinder'">
            <IdPwFinder @goTo="goTo"/>
        </template>
    </v-content>
</template>

<script>
    import VuetifyHelloWorld from '@/views/components/VuetifyHelloWorld';
    import MyPage from '@/views/menu/membership/MyPage';
    import IdPwFinder from "@/views/menu/membership/IdPwFinder";
    import axios from "axios";

    export default {
        name: "Router",
        components: {
            VuetifyHelloWorld,
            MyPage,
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
                    this.$router.push(page).catch(() => {});
                    this.$emit('goTo', page);
                });
            }
        }
    }
</script>

<style scoped>

</style>