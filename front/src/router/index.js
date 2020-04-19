import Vue from 'vue'
import VueRouter from 'vue-router'

import MyPage from '@/views/menu/MyPage.vue'
import IdPwFinder from '@/views/menu/IdPwFinder.vue'
import VuetifyHelloWorld from "@/views/components/VuetifyHelloWorld"

Vue.use(VueRouter);

// routes == controller
const routes = [
  {
    path: '/Home',
    name: 'Home',
    component: VuetifyHelloWorld
  },
  {
    path: '/MyPage',
    name: 'MyPage',
    component: MyPage
  },
  {
    path: '/IdPwFinder',
    name: 'IdPwFinder',
    component: IdPwFinder
  }
];

const router = new VueRouter({
  routes
});

export default router
