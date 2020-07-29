import Vue from 'vue'
import App from '@/App.vue'
import store from '@/store'
import router from '@/router'
import '@/plugins/axios'
import vuetify from '@/plugins/vuetify/index'
import i18n from '@/plugins/vueI18n'

// import lifecycleLogger from '@/mixins/lifcycle-logger.mixin'
// import pluginTest from '@/plugins/test/lifecycle-logger.plugin'
import {library} from "@fortawesome/fontawesome-svg-core";
import {faUserSecret} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
library.add(faUserSecret);



// ************************ Global Components
Vue.component('font-awesome-icon', FontAwesomeIcon);
// ************************ Global Components




Vue.config.productionTip = false;

new Vue({
  name: 'Vue',
  // mixins: [lifecycleLogger],
  store,
  router,
  vuetify,
  i18n,
  render: h => h(App),
}).$mount('#app');