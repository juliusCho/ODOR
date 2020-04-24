import Vue from 'vue';
import Vuetify from "vuetify";
import 'vuetify/dist/vuetify.min.css';
import Theme from './custom-theme';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: Theme,
            dark: {
            }
        }
    }
});
