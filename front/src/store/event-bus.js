import Vue from 'vue';
import axios from "axios";


export const eventBus = new Vue({
    data: {
        passData: {}
    },


    methods: {
        // Get Login User
        async loginRequest(data = {}) {
            if (data?.userId && data?.password) {
                await axios.post(
                    API.SessionController.validateLogin, //url
                    data
                ).then(res => {
                    ODOR.odorLoginUser = res.data;
                });
            }
        },
        // Set Message
        async setMessageList() {
            await axios.get(
                API.CommonController.getMessageList
            ).then(res => {
                MESSAGE.setMessageList(res.data);
            });
        },
        // Set System Code
        async setCodeList() {
            await axios.get(
                API.CommonController.getCodeList
            ).then(res => {
                CODE.setCodeList(res.data);
            });
        },


        dataCarrier(data) {
            this.passData = data;
        }
    }
});