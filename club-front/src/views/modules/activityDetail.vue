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
          <el-button type="primary" @click="handleSignUpOrOut()"> {{ activityInfo.joinStatus!==2?'取消报名':'立即报名' }}</el-button>
          <el-button v-if="activityInfo.joinStatus!==2" type="primary" :disabled="activityInfo.joinStatus===1" @click="handleSignIn()"> {{ activityInfo.joinStatus===1?'已签到':'未签到' }}</el-button>
          <el-button v-if="activityInfo.joinStatus!==2" type="primary" :disabled="activityInfo.payStatus" @click="handlePayMoney()"> {{ activityInfo.payStatus?'已缴费':'未缴费' }}</el-button>
        </el-descriptions-item>
      </el-descriptions>
  </div>
</template>
<script lang="ts" setup>
import Vue, {onMounted, ref} from 'vue';
import {useRoute, useRouter} from "vue-router";
import Options from 'vue-class-component';
import {getActivityById} from "@/api/backend-api/globalQueryController";
import {ElMessage, ElMessageBox} from "element-plus";
import {
  activityCancel,
  activityPay,
  activitySignIn,
  activitySignUp,
  clubJoin
} from "@/api/backend-api/commonUserController";

const activityInfo = ref<API.ActivityWithUserStateVO>({});
const route = useRoute();
const activityId = parseInt(route.params.id);

const handleSignUpOrOut = () => {
  if(activityInfo.value.joinStatus === 2) {
    activitySignUp({id: '' + activityId}).then(({data}) => {
      ElMessage.success("报名成功");
      getInfo();
    }).catch(e => {
      console.log(e);
      ElMessage.error(`报名失败, ${e.message}`);
    })
  }
  else{
    activityCancel({id: ''+activityId}).then(({data}) => {
      ElMessage.success("取消成功");
      getInfo();
    }).catch(e => {
      console.log(e);
      ElMessage.error(`取消失败 ${e.message}`);
    })
  }
}

const handleSignIn = () => {
  activitySignIn({id:''+activityId}).then(({data}) => {
    ElMessage.success("签到成功");
    getInfo();
  }).catch(e => {
    console.log(e);
    ElMessage.error(`签到失败, ${e.message}`);
  })
}

const handlePayMoney = () => {
  ElMessageBox.confirm(`确定支付吗`).then(() => {
    activityPay({id: ''+activityId}).then(({data}) => {
      ElMessage.success("支付成功");
      getInfo();
    }).catch(e => {
      console.log(e);
      ElMessage.error(`支付失败, ${e.message}`);
    })
  })
  .catch(() => {
    console.log("取消支付")
  })

}

const getInfo = () => {
  getActivityById({id: activityId}).then(({data}) => {
    activityInfo.value = data.data;
  }).catch(e => {
    console.log(e);
    ElMessage.error("获取信息失败");
  })
}

onMounted(() => {
  getInfo();
})
</script>


<style scoped lang="scss">

</style>