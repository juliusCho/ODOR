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
        <v-container style="width: 300px;">
            <v-alert
                    v-if="loginSucess === 'inval'"
                    type="info"
                    mode="in-out"
            >
                제대로 입력해!
            </v-alert>
            <v-alert
                    v-if="loginSucess === 'ok'"
                    type="success"
                    mode="in-out"
            >
                Login 성공!
            </v-alert>
            <v-alert
                    v-else-if="loginSucess === 'no'"
                    type="warning"
                    mode="out-in"
            >
                Login 실패ㅠ
            </v-alert>
            <v-alert
                    v-else-if="loginSucess === 'err'"
                    type="error"
                    mode="out-in"
            >
                오류발견
            </v-alert>
        </v-container>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "Login",
        data() {
            return {
                loginInput: {userId: '', password: ''},
                loginSucess: ''
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
                    this.loginSucess = 'inval';
                    return;
                }

                axios.post(
                    API.SessionController.validateLogin,
                    this.loginInput
                )
                .then((res) => {
                    this.loginSucess = res.data;
                })
                .catch((e) => {
                    console.log(e);
                    this.loginSucess = 'err';
                });
            }
        }
    }
</script>

<style scoped>

</style>