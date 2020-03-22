import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import './plugins/axios'

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app');

// index.js 는 따로 import 명시 안해도 됨
