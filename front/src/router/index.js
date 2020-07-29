import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

const lazyload = view => import(`@/views/menu/${view}`);


// routes == controller
const routes = [
  {
    path: '/Home',
    name: 'Home',
    component: lazyload('Main')
  },
  {
    path: '/Join',
    name: 'Join',
    component: lazyload('membership/Join')
  },
  {
    path: '/MyPage',
    name: 'MyPage',
    component: lazyload('membership/MyPage')
  },
  {
    path: '/Login',
    name: 'Login',
    component: lazyload('membership/Login')
  },
  {
    path: '/IdPwFinder',
    name: 'IdPwFinder',
    component: lazyload('membership/IdPwFinder')
  },
  {
    path: '/About',
    name: 'About',
    component: lazyload('about/About')
  },
  {
    path: '/Photo',
    name: 'Photo',
    component: lazyload('forum/Photo')
  },
  {
    path: '/Review',
    name: 'Review',
    component: lazyload('forum/Review')
  },
  {
    path: '/Etc',
    name: 'Etc',
    component: lazyload('forum/Etc')
  },
  {
    path: '/Perfume',
    name: 'Perfume',
    component: lazyload('perfume/Perfume')
  },
  {
    path: '/MessageMgmt',
    name: 'MessageMgmt',
    component: lazyload('system/MessageMgmt')
  },
  {
    path: '/CategoryMgmt',
    name: 'CategoryMgmt',
    component: lazyload('system/CategoryMgmt')
  },
  {
    path: '/BrandMgmt',
    name: 'BrandMgmt',
    component: lazyload('system/BrandMgmt')
  },
  {
    path: '/CodeMgmt',
    name: 'CodeMgmt',
    component: lazyload('system/CodeMgmt')
  },
  {
    path: '/CodeDetailMgmt',
    name: 'CodeDetailMgmt',
    component: lazyload('system/CodeDetailMgmt')
  },
  {
    path: '/CountryMgmt',
    name: 'CountryMgmt',
    component: lazyload('system/CountryMgmt')
  },
  {
    path: '/CreatorMgmt',
    name: 'CreatorMgmt',
    component: lazyload('system/CreatorMgmt')
  },
  {
    path: '/ForumMgmt',
    name: 'ForumMgmt',
    component: lazyload('system/ForumMgmt')
  },
  {
    path: '/IngredientMgmt',
    name: 'IngredientMgmt',
    component: lazyload('system/IngredientMgmt')
  },
  {
    path: '/MembershipMgmt',
    name: 'MembershipMgmt',
    component: lazyload('system/MembershipMgmt')
  },
  {
    path: '/ProductMgmt',
    name: 'ProductMgmt',
    component: lazyload('system/ProductMgmt')
  },
  {
    path: '/ProductTypeMgmt',
    name: 'ProductTypeMgmt',
    component: lazyload('system/ProductTypeMgmt')
  },
  {
    path: '/RatingItemMgmt',
    name: 'RatingItemMgmt',
    component: lazyload('system/RatingItemMasterMgmt')
  },
  {
    path: '/UserMgmt',
    name: 'UserMgmt',
    component: lazyload('system/UserMgmt')
  },
  {
    path: '/SessionCheck',
    name: 'SessionCheck',
    component: lazyload('SessionCheck')
  }
];

const router = new VueRouter({
  routes
});

export default router