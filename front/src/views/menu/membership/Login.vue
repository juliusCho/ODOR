<template>
    <div style="align-self: center; margin-bottom: 10%;">
        <div style="display: flex;">
            <v-spacer style="margin-left: 40%;"></v-spacer>
            <v-container style="width: 60%;">
                <span class="display-4">Login</span>
                <v-row align="center" style="margin-left: 20px;">
                    <v-form>
                        <v-text-field v-model="loginInput.userId" label="ID" required/>
                        <v-text-field v-model="loginInput.password" label="PW" required/>
                        <v-btn
                                color="success"
                                class="mr-4"
                                style="margin-left:55px;"
                                @click="loginRequest"
                        >
                            Login
                        </v-btn>
                    </v-form>
                </v-row>
            </v-container>
            <RightTopAlert
                    :status="loginStatus"
                    :msg="alertMsg"
                    :show="alertShow"
                    :showTime="2500"
                    @hideDisplay="hideAlert"
            />
        </div>
        <div style="margin-left: calc(40% + 92px);">
            <v-btn
                    color="secondary"
                    class="mr-4"
                    x-small
                    @click="goTo('IdPwFinder')"
            >
                I Forgot
            </v-btn>
        </div>
        <div style="margin-left: calc(40% + 75px); margin-top: 30px;">
            <v-btn
                    class="mr-4"
                    small
                    @click="goTo('Join')"
            >
                First Time?
            </v-btn>
        </div>
    </div>
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
                .then((res) => {
                    this.loginResult(res.data);
                })
                .catch((e) => {
                    console.log(e);
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
                    TMP_SESSION.setId(this.loginInput.userId);
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