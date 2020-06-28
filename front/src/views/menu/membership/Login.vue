<template>
    <v-container class="fill-height" fluid>
        <v-layout column>
        <v-row align="center" justify="center" style="margin-bottom: -50px">
            <p class="display-1 font-weight-thin">{{$t('membership.loginWelcome')}}</p>
        </v-row>
        <v-row justify="center">
            <v-col cols="12" sm="8" md="4">
            <v-layout column>
            <RightTopAlert
                    :msg="alertMsg"
                    :show="alertShow"
                    :showTime="2500"
                    :status="loginStatus"
                    @hideDisplay="hideAlert"
            />
                <v-layout row>
                    <v-text-field
                        id="testId"
                        style="margin-right: 5px"
                        v-model="loginInput.userId"
                        :rules="idRules"
                        v-bind:label="$t('membership.id')"
                        name="id"
                        solo
                        required
                    ></v-text-field>
                    <v-text-field
                        style="margin-left: 5px"
                        v-model="loginInput.password"
                        v-bind:label="$t('membership.password')"
                        name="password"
                        solo
                        required
                    ></v-text-field>
                </v-layout>
            <div class="text-center">
            <v-btn
                    class="pl-12 pr-12"
                    @click="loginRequest"
                    align="center"
                    color="secondary"
                    block
                    rounded
                    large
                >
                    {{$t('membership.loginButton')}}
                </v-btn>
            </div>
            <div class="text-right">
                <p type="button"
                   id="findId"
                   class="subtitle-1 font-weight-thin"
                   style="margin-top:5px; margin-bottom:20px"
                   @click="goTo('IdPwFinder')"
                >
                    {{$t('membership.findIdPw')}}</p>
            </div>
            <v-row justify="center" align="center">
                <p class="subtitle-1 font-weight-thin" style="margin-top:25px; margin-bottom:20px">
                    {{$t('membership.joinMessage')}}</p>
            </v-row>
            <div class="text-center">
                <v-btn
                        class="pl-12 pr-12"
                        @click="goTo('Join')"
                        align="center"
                        color="accent"
                        rounded
                        large
                >
                    {{$t('membership.joinButton')}}
                </v-btn>
            </div>
        </v-layout>
            </v-col>
        </v-row>
        </v-layout>
    </v-container>
</template>

<script>
    import axios from 'axios'
    import RightTopAlert from "@/views/components/RightTopAlert"

    export default {
        name: "Login",
        components: {
            RightTopAlert
        },
        data() {
            return {
                loginInput: {userId: '', password: ''},
                loginStatus: 'info',
                alertMsg: '기냥',
                alertShow: false,
                idRules: SCRIPT_VALIDATOR.idRules()
            };
        },
        created() {
            // TEST
            //this.$vuetify.theme.themes.dark.primary = '#4caf50';

            ENTER_ACTION(this.loginRequest);
        },
        methods: {
            loginRequest() {
                if (!SCRIPT_VALIDATOR.nullCheck(
                    this.loginInput.userId,
                    this.loginInput.password
                )) {
                    this.loginStatus = 'info';
                    this.alertMsg = '아이디 또는 패스워드를 입력하세요.';
                    this.alertShow = true;
                    return;
                }

                axios.post(
                    API.SessionController.validateLogin, //url
                    this.loginInput
                )
                .then(res => {
                    this.loginResult(res.data);
                })
                .catch(() => {
                    this.loginStatus = 'error';
                    this.alertMsg = '헐ㅠ오류남';
                })
                .finally(() => {
                    this.alertShow = true;
                });
            },
            loginResult(user) {
                if (!SCRIPT_VALIDATOR.nullCheck(user)) {
                    this.loginStatus = 'warning';
                    this.alertMsg = '아이디나 비밀번호가 맞지 않습니다.';

                } else if ('reasonCode' in user) {
                    this.loginStatus = 'error';
                    this.alertMsg = user.reasonCode;
                    this.goTo('Home');

                } else {
                    this.loginStatus = 'success';
                    this.alertMsg = 'Login성공!';
                    TMP_SESSION.setLognUser(user);
                    this.$emit('loggedIn', true);
                    this.goTo('Home');
                }
            },
            hideAlert() {
                this.alertShow = false;
            },
            goTo(page) {
                this.$emit('goTo', page);
            }
        }
    }
</script>

<style scoped>

</style>