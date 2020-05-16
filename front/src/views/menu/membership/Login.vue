<template>
    <v-container class="fill-height" fluid>
        <RightTopAlert
                :msg="alertMsg"
                :show="alertShow"
                :showTime="2500"
                :status="loginStatus"
                @hideDisplay="hideAlert"
        />
        <v-layout column>
            <v-flex xs3 />
            <v-flex xs9>
                <v-row justify="center" align="center">
                    <p class="display-1 font-weight-thin">{{$t('membership.loginWelcome')}}</p>
                </v-row>
                <v-layout row>
                <v-flex xs4></v-flex>
                <v-flex xs4>
                    <v-row>
                        <v-col cols="12" sm="6">
                            <v-text-field
                                v-model="loginInput.userId"
                                :rules="idRules"
                                v-bind:label="$t('membership.id')"
                                name="id"
                                solo
                                required
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field
                                v-model="loginInput.password"
                                v-bind:label="$t('membership.password')"
                                name="password"
                                solo
                                required
                            ></v-text-field>
                        </v-col>
                    </v-row>
                    <div class="text-center">
                        <v-btn
                            class="pl-12 pr-12"
                            @click="loginRequest"
                            align="center"
                            color="secondary"
                            rounded
                            block
                            large
                        >
                            {{$t('membership.loginButton')}}
                        </v-btn>
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
                </v-flex>
                <v-flex xs4></v-flex>
                </v-layout>
            </v-flex>
        </v-layout>
                    <!--        <v-row justify="center">-->
<!--            <v-col md="4">-->
<!--                <v-card class="elevation-10">-->
<!--                    <v-toolbar color="primary" dark flat>-->
<!--                        <v-toolbar-title>Login</v-toolbar-title>-->
<!--                        <v-spacer/>-->
<!--                        <v-tooltip bottom>-->
<!--                            <template v-slot:activator="{ on }">-->
<!--                                <v-btn-->
<!--                                    @click="goTo('IdPwFinder')"-->
<!--                                    icon-->
<!--                                    large-->
<!--                                    v-on="on"-->
<!--                                >-->
<!--                                    <v-icon>mdi-account-alert-outline</v-icon>-->
<!--                                </v-btn>-->
<!--                            </template>-->
<!--                            <span>I Forgot T_T</span>-->
<!--                        </v-tooltip>-->
<!--                        <v-tooltip right>-->
<!--                            <template v-slot:activator="{ on }">-->
<!--                                <v-btn-->
<!--                                    @click="goTo('Join')"-->
<!--                                    icon-->
<!--                                    large-->
<!--                                    v-on="on"-->
<!--                                >-->
<!--                                    <v-icon>mdi-account-box-multiple-outline</v-icon>-->
<!--                                </v-btn>-->
<!--                            </template>-->
<!--                            <span>First Time?</span>-->
<!--                        </v-tooltip>-->
<!--                    </v-toolbar>-->
<!--                    <v-card-text>-->
<!--                        <v-form>-->
<!--                            <v-text-field-->
<!--                                    label="ID"-->
<!--                                    name="id"-->
<!--                                    required-->
<!--                                    type="text"-->
<!--                                    v-model="loginInput.userId"-->
<!--                                    autofocus-->
<!--                            />-->
<!--                            <v-text-field-->
<!--                                    id="password"-->
<!--                                    label="PW"-->
<!--                                    name="password"-->
<!--                                    required-->
<!--                                    type="password"-->
<!--                                    v-model="loginInput.password"-->
<!--                            />-->
<!--                        </v-form>-->
<!--                    </v-card-text>-->
<!--                    <v-card-actions>-->
<!--                        <v-spacer/>-->
<!--                        <v-btn-->
<!--                                @click="loginRequest"-->
<!--                                class="mr-4"-->
<!--                                color="primary"-->
<!--                        >-->
<!--                            Login-->
<!--                        </v-btn>-->
<!--                    </v-card-actions>-->
<!--                </v-card>-->
<!--            </v-col>-->
<!--        </v-row>-->
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
                    this.alertMsg = '제대로 입력해!';
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
                    this.alertMsg = 'Login실패ㅠ';

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