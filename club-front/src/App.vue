<template>
  <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
  <div class="title">
    <div class="title-left">
      <div class="title-left-image"></div>
      <router-link to="/">
        <span>主页(展示活动)</span>
      </router-link>
      <!-- todo true换成是否为管理员 -->
      <router-link to="/usedOrder">
        <span class="">社长的社团管理</span>
      </router-link>
      <router-link to="/admin/club">
<!--        <router-link to="/admin/club">-->
        <span class="">管理员能看的社团管理</span>
      </router-link>
      <router-link to="/admin/activity">
        <span class="">管理员能看的活动管理</span>
      </router-link>
      <router-link to="/evaluate">
        <span class="">导航五</span>
      </router-link>
    </div>
    <div class="title-right">
      <el-dropdown v-if="isLogin">
        <el-avatar class="title-right-image" :src="avatarData" @click="() => avatarEvent('profile')" />
<!--        <custom-avatar class="title-right-image" src="/api/user/getAvatar" @click="() => avatarEvent('profile')" />-->
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="() => avatarEvent('profile')">个人中心</el-dropdown-item>
            <el-dropdown-item @click="() => avatarEvent('2')">留着</el-dropdown-item>
            <el-dropdown-item @click="() => avatarEvent('logout')">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>

    </div>
  </div>
  <h1>{{}}</h1>
  <transition name="fade">
      <router-view></router-view>
  </transition>
  <el-backtop :right="100" :bottom="100" />

</template>

<script lang="ts" setup>
import {useRouter} from "vue-router";
import {getCurrentUser} from "@/utils";
import {onMounted, ref, onUnmounted } from "vue";
import CustomAvatar from "@/components/CustomAvatar.vue";
import {getAvatar} from "@/api/backend-api/userAccountController";

const router = useRouter()

const isLogin = () =>{
  return sessionStorage.getItem('jwt');
}


const avatarEvent = (type: string) => {
  if(type === 'profile'){
    router.push({path: `/profile/${getCurrentUser().id}`})
  }
  else if(type === 'logout'){
    sessionStorage.clear();
    router.push({path: '/login'}).then(()=>window.location.reload());
  }
}

const avatarData = ref<string>();
onMounted(() => {
  getAvatar().then(({data})=> {
    avatarData.value = 'data:image/png;base64,' + data.data;
  })
})

const debounce = (fn, delay) => {

  let timer = null;

  return function () {

    let context = this;

    let args = arguments;

    clearTimeout(timer);

    timer = setTimeout(function () {

      fn.apply(context, args);

    }, delay);

  }

}



const _ResizeObserver = window.ResizeObserver;

window.ResizeObserver = class ResizeObserver extends _ResizeObserver {

  constructor(callback) {

    callback = debounce(callback, 16);

    super(callback);

  }

}



</script>

<style>
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */



.title{
  margin-left: 0px;
  height: 48px;
  width: 100%;
  background-color:#fff;
  border-bottom: 1px solid #DCDCDC;
  display: flex;
  justify-content:space-between;
}
.title-left{
  width: 100%;
  height: 48px;
  margin-left: 0;
  background:#fff;
  display: flex;

}
span{
  font-size: 16px
}
.title-left-image{
  height: 48px;
  width:120px;
  display: flex;
  align-items: center
}
/* router-link样式 */
a{
  /* width: 70px; */
  height: 48px;
  color: black;
  margin: 0px 10px;
  text-decoration: none;
  line-height: 48px;
  text-align: center
}
/* 被选中 */
a:hover{
  background-color:	#BEBEBE;
}
.title-right{
  width: 380px;
  height: 48px;
  display: flex;
  .title-right-image{
    width: 40px;
    height: 40px;
    //border: 1px solid black;
    border-radius: 20px;
    margin: auto;
    //margin-left: 40px;
  }
}
h1{
  text-align: center;
  margin: 0 auto;
  align-items: center;
  justify-content: center;
  color: black;
}


</style>
