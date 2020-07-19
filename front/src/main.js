import Vue from 'vue'
import App from '@/App.vue'
import store from '@/store'
import router from '@/router'
import '@/plugins/axios'
import vuetify from '@/plugins/vuetify/index'
import i18n from '@/plugins/vueI18n'
import VideoBg from 'vue-videobg/src/VideoBackground'
import axios from 'axios'
// import lifecycleLogger from '@/mixins/lifcycle-logger.mixin'
// import pluginTest from '@/plugins/test/lifecycle-logger.plugin'




Vue.config.productionTip = false;





export const eventBus = new Vue({
  data: {
    passData: {}
  },


  methods: {
    // Get Login User
    async loginRequest(data = {}) {
      if (data?.userId && data?.password) {
        await axios.post(
            API.SessionController.validateLogin, //url
            data
        ).then(res => {
          ODOR.odorLoginUser = res.data;
        });
      }
    },
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
  VideoBg,
  render: h => h(App),
}).$mount('#app');

Vue.component('video-bg', VideoBg);

//Vue.component('video-bg', VideoBg)
// window.onload = () => {
//   new Vue({
//     el:'body',
//     components: { VideoBg }
//   })
// }






// index.js 는 따로 import 명시 안해도 됨
