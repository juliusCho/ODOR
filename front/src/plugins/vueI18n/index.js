import Vue from 'vue';
import VueI18n from "vue-i18n";
import ENG from '@/assets/locale/english';
import KOR from '@/assets/locale/korean';

Vue.use(VueI18n);

const messages = {
    ENG: ENG,
    KO: KOR
};

const i18n = new VueI18n({
    locale: window.navigator.language.indexOf('ko') > -1 ? 'KO' : 'ENG',
    messages
});

export default i18n