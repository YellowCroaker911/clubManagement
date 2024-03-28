<template>
  <div class="activities-class">
      <!--    <el-card v-for="item in list" :body-style="{ padding: '0px', marginBottom: '1px' }">-->
      <el-descriptions class="activity-item-class" :title="activityInfo.name" border type="primary">
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="活动名称: ">{{ activityInfo.name }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="主题: ">{{ activityInfo.title }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="报名方式: ">{{ activityInfo.sign }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="详细信息: ">{{ activityInfo.info }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="活动地点: ">{{ activityInfo.address }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="活动时间: ">{{ activityInfo.beginTime }} 至 {{ activityInfo.endTime }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="已报名人数: ">{{ activityInfo.joinPeople }}</el-descriptions-item>
        <el-descriptions-item class-name="description-class" label-class-name="label-class" span="1" label="其他: ">
          <el-button type="primary" :disabled="activityInfo.isJoin" @click="handleSignUpOrOut()"> {{ activityInfo.isJoin?'取消报名':'立即报名' }}</el-button>
          <el-button v-if="activityInfo.isJoin" type="primary" :disabled="activityInfo.isSign" @click="handleSignIn()"> {{ activityInfo.isSign?'已签到':'未签到' }}</el-button>
        </el-descriptions-item>
      </el-descriptions>
  </div>
</template>
<script lang="ts" setup>
import Vue, {onMounted, ref} from 'vue';
import {useRoute, useRouter} from "vue-router";
import Options from 'vue-class-component';
import {getActivityById} from "@/api/backend-api/globalQueryController";
import {ElMain, ElMessage} from "element-plus";
import {activityCancel, activitySignUp} from "@/api/backend-api/commonUserController";
const activityInfo = ref<API.Activity>({
  id: 1,
  name: "卖书",
  info: "这是一个卖书活动的信息",
  title: "义卖",
  beginTime: "2024-01-01 19:00",
  endTime: "2024-01-07 00:00",
  address: "操场",
  sign: "网站报名",
  joinPeople: 100,
  // isJoin: false,
  // isSign: false,
});
const route = useRoute();
const activityId = parseInt(route.params.id);

const handleSignUpOrOut = () => {
  if(!activityInfo.isJoin) {
    activitySignUp({id: '' + activityId}).then(({data}) => {
      ElMessage.success("报名成功");
    }).catch(e => {
      console.log(e);
      ElMessage.error("报名失败");
    })
  }
  else{
    activityCancel({id: ''+activityId}).then(({data}) => {
      ElMessage.success("取消成功");
    }).catch(e => {
      console.log(e);
      ElMessage.error("取消失败");
    })
  }
}

const handleSignIn = () => {
  activitySignUp({id:''+activityId}).then(({data}) => {
    ElMessage.success("签到成功");
  }).catch(e => {
    console.log(e);
    ElMessage.error("签到失败");
  })
}

onMounted(() => {
  getActivityById({id: activityId}).then(({data}) => {
    activityInfo.value = data.data;
  }).catch(e => {
    console.log(e);
    ElMessage.error("获取信息失败");
  })
})
</script>


<style scoped lang="scss">

</style>