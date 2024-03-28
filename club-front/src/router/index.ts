import { createRouter, createWebHistory } from 'vue-router'
import {getCurrentUser} from "@/utils";
import {getSelfInfo} from "@/api/backend-api/userAccountController";


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: ()=> import("@/views/common/login.vue")
    },
    {
      path: '/',
      name: 'home',
      component: ()=> import("@/views/modules/home.vue"),
      meta: { requiresAuth: true }
    },
    {
      path: '/usedOrder',
      name: 'UsedOrder',
      component: ()=> import("@/views/modules/Club/presidentClub.vue"),
      meta: { requiresAuth: true }
    },
    {
      path: '/admin/club',
      name: 'AdminClub',
      component: ()=> import("@/views/modules/AdminClub/AdminClub.vue"),
      meta: { requiresAuth: true, requiresAdmin: true }
    },
    {
      path: "/profile/:id",
      name: "profile",
      meta: { title: "个人主页", requiresAuth: true },
      component: () => import("@/views/modules/profile/profile.vue")
    },
    {
      path: "/activity/:id",
      name: "活动详情页",
      meta: { title: "活动详情页", requiresAuth: true },
      component: () => import("@/views/modules/activityDetail.vue")
    },
  ]
})


router.beforeEach((to, from, next) => {
  const authStore = sessionStorage.getItem('jwt');
  const isAuthenticated = !!authStore;
  if(to.meta.requiresAuth || to.meta.requiresAdmin) {
    if(!isAuthenticated)next({name: "login"});
    else {
      getSelfInfo().then(({data}) => {
        sessionStorage.setItem('user', JSON.stringify(data.data));
      })
      if (to.meta.requiresAdmin) {
        // @ts-ignore
        const role = getCurrentUser().role;
        if (role !== 1) next({name: 'home'});
        else next();
      }
      else next();
    }
  }
  else{
    next();
  }
});


export default router
