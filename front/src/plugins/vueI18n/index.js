import Vue from 'vue';
import VueI18n from "vue-i18n";

Vue.use(VueI18n);

const messages = {
    ENG: {
        lbl: {
            welcome: 'Welcome to ODOR'
        }
    },
    KO: {
        lbl: {
            welcome: 'ODOR의 세계로 오신걸 환영합니다'
        }
    }
};

const i18n = new VueI18n({
    locale: window.navigator.language.indexOf('ko') > -1 ? 'KO' : 'ENG',
    messages
});

export default i18n