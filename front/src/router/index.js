import Vue from 'vue'
import VueRouter from 'vue-router'

import Join from '@/views/menu/membership/Join';
import MyPage from '@/views/menu/membership/MyPage.vue'
import Login from '@/views/menu/membership/Login.vue'
import IdPwFinder from '@/views/menu/membership/IdPwFinder.vue'
import VuetifyHelloWorld from '@/views/components/VuetifyHelloWorld'

import About from '@/views/menu/about/About'

import Photo from '@/views/menu/forum/Photo';
import Review from '@/views/menu/forum/Review';
import Etc from '@/views/menu/forum/Etc';

import Perfume from '@/views/menu/perfume/Perfume';

import BrandMgmt from '@/views/menu/system/BrandMgmt';
import CodeMgmt from '@/views/menu/system/CodeMgmt';
import CountryMgmt from '@/views/menu/system/CountryMgmt';
import CreatorMgmt from '@/views/menu/system/CreatorMgmt';
import ForumMgmt from '@/views/menu/system/ForumMgmt';
import IngredientMgmt from '@/views/menu/system/IngredientMgmt';
import MembershipMgmt from '@/views/menu/system/MembershipMgmt';
import ProductMgmt from '@/views/menu/system/ProductMgmt';
import ProductTypeMgmt from '@/views/menu/system/ProductTypeMgmt';
import RatingItemMgmt from '@/views/menu/system/RatingItemMgmt';
import UserMgmt from '@/views/menu/system/UserMgmt';

import SessionCheck from '@/views/menu/SessionCheck';


Vue.use(VueRouter);

// routes == controller
const routes = [
  {
    path: '/Home',
    name: 'Home',
    component: VuetifyHelloWorld
  },
  {
    path: '/Join',
    name: 'Join',
    component: Join
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
  },
  {
    path: '/Photo',
    name: 'Photo',
    component: Photo
  },
  {
    path: '/Review',
    name: 'Review',
    component: Review
  },
  {
    path: '/Etc',
    name: 'Etc',
    component: Etc
  },
  {
    path: '/Perfume',
    name: 'Perfume',
    component: Perfume
  },
  {
    path: '/BrandMgmt',
    name: 'BrandMgmt',
    component: BrandMgmt
  },
  {
    path: '/CodeMgmt',
    name: 'CodeMgmt',
    component: CodeMgmt
  },
  {
    path: '/CountryMgmt',
    name: 'CountryMgmt',
    component: CountryMgmt
  },
  {
    path: '/CreatorMgmt',
    name: 'CreatorMgmt',
    component: CreatorMgmt
  },
  {
    path: '/ForumMgmt',
    name: 'ForumMgmt',
    component: ForumMgmt
  },
  {
    path: '/IngredientMgmt',
    name: 'IngredientMgmt',
    component: IngredientMgmt
  },
  {
    path: '/MembershipMgmt',
    name: 'MembershipMgmt',
    component: MembershipMgmt
  },
  {
    path: '/ProductMgmt',
    name: 'ProductMgmt',
    component: ProductMgmt
  },
  {
    path: '/ProductTypeMgmt',
    name: 'ProductTypeMgmt',
    component: ProductTypeMgmt
  },
  {
    path: '/RatingItemMgmt',
    name: 'RatingItemMgmt',
    component: RatingItemMgmt
  },
  {
    path: '/UserMgmt',
    name: 'UserMgmt',
    component: UserMgmt
  },
  {
    path: '/SessionCheck',
    name: 'SessionCheck',
    component: SessionCheck
  }
];

const router = new VueRouter({
  routes
});

export default router
