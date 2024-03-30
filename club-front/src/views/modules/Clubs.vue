<template>
  <h1>社团列表</h1>
  <div class="activities-class">
    <template v-for="(item, index) in list" :key="index">
      <!--    <el-card v-for="item in list" :body-style="{ padding: '0px', marginBottom: '1px' }">-->
      <el-descriptions class="activity-item-class" :title="item.name" border type="primary">
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="社团名称: ">{{ item.name }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="社团头像: ">
          <el-avatar v-if='item.avatar && item.avatar[0]!=="i"' :src="item.avatar" size="large" alt="上传头像" />
        </el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="社团信息: ">{{ item.info }}</el-descriptions-item>
        <!--      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="详细信息: ">{{ item.info }}</el-descriptions-item>-->
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="社团活动地点: ">{{ item.address }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="社团人数: ">{{ item.member }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="联系方式: ">{{ item.contact }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="社团活动数: ">{{ item.activityNumber }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="社团状态: ">
          <el-tag :type="item.isAdmitted ? 'success' : ''" disable-transitions>
            {{ item.isAdmitted?"正常":"社团审核中" }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="1" label="其他: ">
          <el-button v-if="item.isPassed==2" type="primary" @click="() => {signinClub(item.id, index)}"> 报名参加社团 </el-button>
          <el-button v-if="item.isPassed==0" disabled type="primary"> 审核中 </el-button>
          <el-button v-if="item.isPassed==1" disabled type="primary"> 已加入 </el-button>
        </el-descriptions-item>

      </el-descriptions>
    </template>
  </div>
</template>

<script setup lang="ts">
import { getUUID } from '@/utils'
import {ElMessage, ElMessageBox} from 'element-plus'
import {ref, reactive, getCurrentInstance, onMounted,} from "vue";
import router from "@/router";
import {
  getAllActivity,
  getAllClub,
  getClubAvatar,
  getUserClubExtendClub
} from "@/api/backend-api/globalQueryController";
import {
  clubJoin, getAllClubWithCurrentUser,
  getSelfClubActivities,
  getSelfClubs
} from "@/api/backend-api/commonUserController";
// import {getToken} from "@/api/backend-api/loginController";
// import {register} from "@/api/backend-api/registerController";
const { proxy } = getCurrentInstance()


const loading = ref(false)
const currentDate = new Date().toDateString()
const list = ref<API.ClubWithUserStateVO[]>([])

function signinClub(clubId: number, index) {
  ElMessageBox.confirm(`确定要报名该社团 ${list.value[index].name} 吗`)
  .then(() => {
    clubJoin({id: clubId}).then(({data}) => {
      ElMessage.success("报名成功");
      updateClubInfo();
      list.value[index].isPassed = 1;
    }).catch(e => {
      ElMessage.error("报名失败");
      console.log(e);
    })
  })
  .catch(() => {
    console.log("报名取消")
  })
}

const updateClubInfo = () => {
  getAllClubWithCurrentUser().then(({data}) => {
    list.value = data.data;
  }).then(() => {
    for(let i in list.value) {
      getClubAvatar({id: list.value[i].id}).then(({data}) => {
        list.value[i].avatar = 'data:image/png;base64,' + data.data;
      })
    }
  }).catch(e => {
    ElMessage.error("获取社团信息失败");
    console.log(e);
  })
  // getAllClub().then(({data}) => {
  //   list.value = data.data;
  //   // list.value.forEach(item => {
  //   //   item.isPass = 1;
  //   // })
  //   for(let i in list.value){
  //     getUserClubExtendClub({clubId: list.value[i].id}).then(({data}) => {
  //       userClubStatus.value = [];
  //       data.data.find((value, index, arr) => {
  //         return value.id === list.value[i].id;
  //       })
  //     })
  //   }
  // })
}

onMounted(() => {
  updateClubInfo();
})

</script>

<style>

</style>
