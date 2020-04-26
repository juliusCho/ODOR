<template>
    <v-container>
        <RightTopAlert
            :msg="alertMsg"
            :show="alertShow"
            :show-time="2500"
            @hideDisplay="hideAlert"
        />
        <v-btn @click="logout">Logout</v-btn>
        <v-btn @click="mypage">mypage</v-btn>
        <div>
            {{tmpSession.nickname}}
        </div>


        <div>
            <p class="display-1"
            >회원정보 변경</p>
        </div>
        <v-row align="center">
            <v-row justify="space-around">
                    <v-switch v-model="valid" class="ma-4" label="Male"></v-switch>
                    <v-switch v-model="lazy" class="ma-4" label="Female"></v-switch>
            </v-row>
        </v-row>
        <v-form>
            <v-text-field
                    v-model="tmpSession.userId"
                    :counter="10"
                    :rules="nameRules"
                    label="ID"
                    required
            ></v-text-field>
            <v-text-field
                    v-model="tmpSession.password"
                    :counter="20"
                    :rules="nameRules"
                    label="Password"
                    required
            ></v-text-field>
            <v-text-field
                    v-model="tmpSession.nickname"
                    :counter="10"
                    :rules="nameRules"
                    label="Nickname"
                    required
            ></v-text-field>
            <v-text-field
                    v-model="tmpSession.age"
                    :counter="10"
                    :rules="nameRules"
                    label="Age"
                    required
            ></v-text-field>
            <v-row>
                <v-col cols="12" sm="6">
                    <v-text-field
                            v-model="tmpSession.email"
                            :counter="10"
                            :rules="nameRules"
                            label="Email"
                            required
                    ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                    <v-select
                            v-model="tmpSession.email"
                            :counter="10"
                            :rules="nameRules"
                            label="Email"
                            required
                    ></v-select>
                </v-col>
            </v-row>
            <div class="text-center">
                <v-btn
                        @click="updateUser"
                        align="center"
                        color="primary"
                        rounded
                >
                    변경하기
                </v-btn>
            </div>
        </v-form>
    </v-container>
</template>

<script>
    import axios from 'axios';
    import RightTopAlert from "../../components/RightTopAlert";

    export default {
        name: "MyPage",
        components: {RightTopAlert},
        data() {
            return {
                tmpSession: {},
                alertMsg: '구냥',
                alertShow: false
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
                this.tmpSession = TMP_SESSION.getLoginUser();
            },
            goTo(page) {
                this.$emit('goTo', page);
            },
            logout() {
                    axios.get(API.SessionController.logout, {})
                    .then(() => {
                        TMP_SESSION.emptyUser();
                        this.$emit('loggedIn', false);
                        this.goTo('Home');
                    });
                },
            mypage() {
                this.alertMsg = 'my Page';
                this.alertShow = true;
            },
            hideAlert() {
                this.alertShow = false;
            }
        }
    }
</script>

<style scoped>

</style>