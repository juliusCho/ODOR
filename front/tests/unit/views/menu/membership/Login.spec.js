// import Vue from "vue";
import {shallowMount} from "@vue/test-utils";
import Login from '@/views/menu/membership/Login'

const wrapper = shallowMount(Login, {
    mocks: {
        $t: () => {}
    }
});

// function getMountedComponent(Component, propsData) {
//
// }

describe('Login.vue', () => {

    // it('userId must be binded before all', () => {
    //     expect(wrapper.data().loginInput).toBeDefined();
    // });

    it('has a created hook', () => {
        expect(typeof Login.created).toBe('function');
    });

    it('sets the correct default data', () => {
        expect(typeof Login.data).toBe('function');
        const defaultData = Login.data();
        expect(defaultData.loginInput).toBe({userId: '', password: ''});
    });

    //
    // it('should render correct contents', () => {
    //     const Constructor = Vue.extend(Login);
    //     const vm = new Constructor().$mount();
    //     // console.log(vm);
    //     // expect(vm.$el.getElementById('testId').style.margin)
    //     //     .toEqual('5px');
    // });
});