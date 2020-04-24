<template>
    <v-app>
        <v-content>
            <RightTopAlert
                    :status="loginStatus"
                    :msg="alertMsg"
                    :show="alertShow"
                    :showTime="2500"
                    @hideDisplay="hideAlert"
            />
            <v-container class="fill-height" fluid>
                <v-row align="center" justify="center" style="padding-bottom: 12%;">
                    <v-col cols="12" sm="8" md="4">
                        <v-card class="elevation-12">
                            <v-toolbar color="primary" dark flat>
                                <v-toolbar-title>Login</v-toolbar-title>
                                <v-spacer/>
                                <v-tooltip bottom>
                                    <template v-slot:activator="{ on }">
                                        <v-btn
                                            @click="goTo('IdPwFinder')"
                                            icon
                                            large
                                            v-on="on"
                                        >
                                            <v-icon>mdi-account-alert-outline</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>I Forgot T_T</span>
                                </v-tooltip>
                                <v-tooltip right>
                                    <template v-slot:activator="{ on }">
                                        <v-btn
                                            icon
                                            large
                                            v-on="on"
                                            @click="goTo('Join')"
                                        >
                                            <v-icon>mdi-account-box-multiple-outline</v-icon>
                                        </v-btn>
                                    </template>
                                    <span>First Time?</span>
                                </v-tooltip>
                            </v-toolbar>
                            <v-card-text>
                                <v-form>
                                    <v-text-field
                                            v-model="loginInput.userId"
                                            label="ID"
                                            required
                                            type="text"
                                            name="id"
                                    />
                                    <v-text-field
                                            v-model="loginInput.password"
                                            label="PW"
                                            required
                                            type="password"
                                            name="password"
                                            id="password"
                                    />
                                </v-form>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer/>
                                <v-btn
                                        color="primary"
                                        class="mr-4"
                                        @click="loginRequest"
                                >
                                    Login
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-content>
    </v-app>
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
                alertShow: false
            };
        },
        created() {
            // TEST
            this.$vuetify.theme.themes.dark.primary = '#4caf50';

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