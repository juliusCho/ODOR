import Vue from 'vue'
import App from '@/App.vue'
import store from '@/store'
import router from '@/router'
import '@/plugins/axios'
import vuetify from '@/plugins/vuetify/index'
import i18n from '@/plugins/vueI18n'
import axios from 'axios'
// import lifecycleLogger from '@/mixins/lifcycle-logger.mixin'
// import pluginTest from '@/plugins/test/lifecycle-logger.plugin'




Vue.config.productionTip = false;





export const eventBus = new Vue({
  data: {
    passData: {}
  },


  methods: {

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


// Vue.use(pluginTest, {activate: false});

new Vue({
  name: 'Vue',
  // mixins: [lifecycleLogger],
  store,
  router,
  vuetify,
  i18n,
  render: h => h(App),
}).$mount('#app');

// index.js 는 따로 import 명시 안해도 됨
