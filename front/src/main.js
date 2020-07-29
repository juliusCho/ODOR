import Vue from 'vue'
import App from '@/App.vue'
import store from '@/store'
import router from '@/router'
import '@/plugins/axios'
import vuetify from '@/plugins/vuetify/index'
import i18n from '@/plugins/vueI18n'
// import lifecycleLogger from '@/mixins/lifcycle-logger.mixin'
// import pluginTest from '@/plugins/test/lifecycle-logger.plugin'

// *************************Font Awesome + Icons
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {library, dom} from "@fortawesome/fontawesome-svg-core";
import {
    faUserSecret,
    faSpinner,
    faChessQueen
} from "@fortawesome/free-solid-svg-icons";

library.add(faUserSecret);
library.add(faSpinner);
library.add(faChessQueen);
// *************************Font Awesome + Icons




// ************************ Global Components
Vue.component('font-awesome-icon', FontAwesomeIcon);
// ************************ Global Components



// ************************ Global Config
Vue.config.productionTip = false;
dom.watch();
// ************************ Global Config

new Vue({
  name: 'Vue',
  // mixins: [lifecycleLogger],
  store,
  router,
  vuetify,
  i18n,
  render: h => h(App),
}).$mount('#app');