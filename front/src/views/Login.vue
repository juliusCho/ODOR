<template>
    <div> <!--최상위 태그 히나로만 감싸야함-->
        <div>Login</div>
        <div>
            <div>
                <label>ID</label>
                <label>
                    <input type="text" v-model="loginInput.userId"/>
                </label>
            </div>
            <div>
                <label>PW</label>
                <label>
                    <input type="text" v-model="loginInput.password"/>
                </label>
            </div>
            <div>
                <button @click="loginRequest">Login</button>
            </div>
        </div>
    </div>
</template>

<script>
    import { API } from '@/api'
    import { v } from './utils/script-validator'
    import axios from 'axios'

    export default {
        name: "Login",
        data() {
            return {
                loginInput: {userId: '', password: ''}
            };
        },
        methods: {
            loginRequest() {
                if (!v.nullCheck(this.loginInput.userId) || !v.nullCheck(this.loginInput.password)) {
                    return;
                }
                console.log(API.SessionController.validateLogin);
                console.log(this.loginInput);

                axios.post(API.SessionController.validateLogin, this.loginInput)
                .then((res) => {
                    console.log(res);
                    console.log(res.data);
                });
            }
        }
    }
</script>

<style scoped>

</style>