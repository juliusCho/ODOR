import Vue from 'vue'
import App from '@/App.vue'
import store from '@/store'
import router from '@/router'
import '@/plugins/axios'
import vuetify from '@/plugins/vuetify/index'
import i18n from '@/plugins/vueI18n'

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  vuetify,
  i18n,
  render: h => h(App),
}).$mount('#app');

// index.js 는 따로 import 명시 안해도 됨
