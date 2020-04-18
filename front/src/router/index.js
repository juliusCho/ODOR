import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from '@/views/menu/Login.vue'
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
    path: '/Login',
    name: 'Login',
    component: Login
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
