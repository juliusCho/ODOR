import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '../views/Home.vue'
import Login from '../views/menu/Login.vue'
import IdPwFinder from '../views/menu/IdPwFinder.vue'

Vue.use(VueRouter);

// routes == controller
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
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
