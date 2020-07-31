<template>
    <v-content>
        <template v-if="routing === 'Home'">
            <Main :key="routing"/>
        </template>
        <template v-else-if="routing === 'Join'">
            <Join @goTo="goTo" :key="routing"/>
        </template>
        <template v-else-if="routing === 'MyPage'">
            <MyPage @goTo="goTo" @loggedIn="loggedIn" :key="routing"/>
        </template>
        <template v-else-if="routing === 'Login'">
            <Login @goTo="goTo" @loggedIn="loggedIn" :key="routing"/>
        </template>
        <template v-else-if="routing === 'IdPwFinder'">
            <IdPwFinder @goTo="goTo" :key="routing"/>
        </template>
        <template v-else-if="routing === 'About'">
            <About :key="routing"/>
        </template>
        <template v-else-if="routing === 'Photo'">
            <Photo :forumKey="forumKey" :categoryId="categoryId" :key="routing"/>
        </template>
        <template v-else-if="routing === 'Review'">
            <Review :forumKey="forumKey" :categoryId="categoryId" :key="routing"/>
        </template>
        <template v-else-if="routing === 'Etc'">
            <Etc :forumKey="forumKey" :categoryId="categoryId" :key="routing"/>
        </template>
        <template v-else>
            <SessionCheck
                :page="routing"
                @checkSession="checkSession"
                @goTo="goTo"
                :key="routing"
            />
        </template>
    </v-content>
</template>

<script>
    import Main from '@/views/menu/Main';
    import Join from '@/views/menu/membership/Join';
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
            Main,
            Join,
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
                forumKey: 0,
                categoryId: ''
            }
        },
        methods: {
            getParent(name){
                let p = this.$parent;
                while(typeof p !== 'undefined') {
                    if (p.$options?.name === name) {
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

                this.$emit('goTo', page);
                let parent = this.getParent('App');
                this.forumKey = parent?.forumKey || 0;
                this.categoryId = parent?.categoryId || '';

                this.checkSession(page);
            },
            async checkSession(page) {
                await axios.post(
                    API.SessionController.sessionCheck,
                    {sessionToken: TMP_SESSION.getLoginUser()}
                )
                .then((res) => {
                    this.$emit('goTo', res.data ? page : this.incl);
                });
            },
            loggedIn(boo) {
                this.$emit('loggedIn', boo);
            }
        }
    }
</script>