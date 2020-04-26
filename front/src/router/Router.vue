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
        <template v-else-if="routing === 'About'">
            <About/>
        </template>
        <template v-else-if="routing === 'Photo'">
            <Photo :forumKey="forumKey"/>
        </template>
        <template v-else-if="routing === 'Review'">
            <Review :forumKey="forumKey"/>
        </template>
        <template v-else-if="routing === 'Etc'">
            <Etc :forumKey="forumKey"/>
        </template>
        <template v-else>
            <SessionCheck :page="routing" @checkSession="checkSession"/>
        </template>
    </v-content>
</template>

<script>
    import VuetifyHelloWorld from '@/views/components/VuetifyHelloWorld';
    import MyPage from '@/views/menu/membership/MyPage';
    import Login from '@/views/menu/membership/Login';
    import IdPwFinder from '@/views/menu/membership/IdPwFinder';
    import About from '@/views/menu/about/About';
    import Photo from '@/views/menu/forum/Photo';
    import Review from '@/views/menu/forum/Review';
    import Etc from '@/views/menu/forum/Etc';
    import SessionCheck from '@/views/menu/SessionCheck';
    import axios from 'axios';

    export default {
        name: 'Router',
        components: {
            VuetifyHelloWorld,
            MyPage,
            Login,
            IdPwFinder,
            About,
            Photo,
            Review,
            Etc,
            SessionCheck
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
                excl: 'MyPage',
                forumKey: 0
            }
        },
        methods: {
            getParent(name){
                let p = this.$parent;
                while(typeof p !== 'undefined') {
                    if (p.$options.name === name) {
                        return p;
                    } else {
                        p = p.$parent;
                    }
                }
                return p;
            },
            goTo(page) {
                if (page === '') {
                    this.$emit('goTo', 'Home');
                    return;
                }
                if (this.checkRest()) {
                    this.$emit('goTo', page);
                    this.forumKey = this.getParent('App').forumKey;
                    return;
                }
                this.checkSession(page);
            },
            async checkSession(page) {
                await axios.post(
                    API.SessionController.sessionCheck,
                    {sessionToken: TMP_SESSION.getId()}
                )
                .then((res) => {
                    if (res.data) {
                        // this.$router.push(page).catch(() => {});
                        this.$emit('goTo', page);
                        return;
                    }
                    this.$emit('goTo', this.incl);
                });
            },
            checkRest() {
                let result = true;

                if (this.routing === this.incl) {
                    return true;
                }

                for (let i = 0, ii = MEMBERSHIP_PAGES.length; i < ii; i++) {
                    if (
                        MEMBERSHIP_PAGES[i] === this.excl &&
                        MEMBERSHIP_PAGES[i] === this.routing
                    ) {
                        result = false;
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