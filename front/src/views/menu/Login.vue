<template>
    <div style="display: flex;">
        <v-container style="margin-left: 20px;">
            <span class="display-4">Login</span>
            <v-row align="center">
                <v-form>
                    <v-text-field v-model="loginInput.userId" label="ID" required/>
                    <v-text-field v-model="loginInput.password" label="PW" required/>
                    <v-btn color="success" class="mr-4" @click="loginRequest">Login</v-btn>
                </v-form>
            </v-row>
        </v-container>
        <RightTopAlert
                :status="loginStatus"
                :msg="alertMsg"
                :show="alertShow"
                :showTime="2500"
                @hideDisplay="hideAlert"/>
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
                    API.SessionController.validateLogin,
                    this.loginInput
                )
                .then((res) => {
                    this.loginStatus = res.data;
                    this.alertMsg = res.data === 'sucess' ? 'Login성공!' : 'Login실패ㅠ';
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
            hideAlert() {
                this.alertShow = false;
            }
        }
    }
</script>

<style scoped>

</style>