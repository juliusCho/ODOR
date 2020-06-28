import Vue from "vue";
// import {API} from '../../../../../public/scripts/api';
// import {COMMON_UTIL} from '../../../../../public/scripts/common-util';
// import {ODOR, MEMBERSHIP_PAGES, TMP_SESSION, MESSAGE, CODE} from '../../../../../public/scripts/consts';
// import SCRIPT_VALIDATOR from '../../../../../public/scripts/script-validator';
// import {ENTER_ACTION} from '../../../../../public/scripts/key-events';
import Login from '@/views/menu/membership/Login'

describe('Login.vue', () => {
    it('should render correct contents', () => {
        const Constructor = Vue.extend(Login);
        const vm = new Constructor().$mount();
        expect(vm.$el.querySelector('#testId').style.margin)
            .toEqual('5px');
    });
});