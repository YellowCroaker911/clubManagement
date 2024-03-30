<template>
  <h1>活动列表</h1>
  <div class="activities-class">
  <template v-for="(item, index) in list" :key="index">
    <el-descriptions class="activity-item-class" :title="item.name" border type="primary">
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="活动名称: ">{{ item.name }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="主办社团: ">{{ item.clubName }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="主题: ">{{ item.title }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="报名方式: ">{{ item.sign }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="活动地点: ">{{ item.address }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="活动时间: ">{{ item.beginTime }} 至 {{ item.endTime }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="已报名人数: ">{{ item.joinPeople }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="1" label="其他: ">
        <el-button type="primary" @click="() => {handleDetailActivity(item.id)}"> 活动详情 </el-button>
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
import {getAllActivity} from "@/api/backend-api/globalQueryController";
import {getSelfClubActivities} from "@/api/backend-api/commonUserController";
const { proxy } = getCurrentInstance()


const loading = ref(false)
const currentDate = new Date().toDateString()
const list = ref<API.ActivityWithClubNameVO[]>([])

const handleDetailActivity = (index: number) => {
  router.push({path: `/activity/${index}`})
}


function signUp(index: number) {
  ElMessageBox.confirm(`确定要报名活动 ${list.value[index].name}`)
  .then(() => {

    console.log("报名成功")
  })
  .catch(() => {
    console.log("报名取消")
  })
}

onMounted(() => {
  getSelfClubActivities().then(({data}) => {
    list.value = data.data;
  })
})

</script>

<style>

</style>
