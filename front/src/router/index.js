import Vue from 'vue'
import VueRouter from 'vue-router'

import MyPage from '@/views/menu/membership/MyPage.vue'
import Login from '@/views/menu/membership/Login.vue'
import IdPwFinder from '@/views/menu/membership/IdPwFinder.vue'
import VuetifyHelloWorld from '@/views/components/VuetifyHelloWorld'

import About from '@/views/menu/about/About'

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
    path: '/Login',
    name: 'Login',
    component: Login
  },
  {
    path: '/IdPwFinder',
    name: 'IdPwFinder',
    component: IdPwFinder
  },
  {
    path: '/About',
    name: 'About',
    component: About
  }
];

const router = new VueRouter({
  routes
});

export default router
