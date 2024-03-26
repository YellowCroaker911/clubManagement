<template>
  <el-row class="tac">
    <el-col :span="2">
      <div class="flex-center">
        <span>Club</span>
      </div>
      <el-menu
          active-text-color="#ffd04b"
          background-color="#545c64"
          class="el-menu-vertical-demo"
          default-active="2"
          text-color="#fff"
          @open="handleOpen"
          @close="handleClose"
      >
        <el-menu-item index="1" @click="showPanel(1)">
<!--          <el-icon><icon-menu /></el-icon>-->
          <span>社团信息</span>
        </el-menu-item>
        <el-menu-item index="2" @click="showPanel(2)">
<!--          <el-icon><document /></el-icon>-->
          <span>活动管理</span>
        </el-menu-item>
      </el-menu>
    </el-col>
    <div class="person_body_right" v-if="opType == 1">
      <el-card style="width: 800px; margin-top: 20px; margin-left: 200px;" shadow="hover">
        <el-descriptions title="社团信息" style="margin-left: 250px">
        </el-descriptions>
        <el-form label-width="80px" size="large">
          <el-form-item label="社团名称" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="formData.name" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="社团简介" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="formData.info" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="社长" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="formData.president_id" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="活动地址" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="formData.address" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="联系方式" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="formData.contact" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="成员数量" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="formData.member" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="社团简介" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="formData.info" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
    <div class="person_body_right" v-if="opType == 2">
      <el-card>
        <el-descriptions class="margin-top" title="活动列表" :column="2" border>
        </el-descriptions>
      </el-card>
    </div>
  </el-row>

</template>

<script lang="ts" setup>
import {ref} from "vue";
// import {clubInfo} from "@/api/backend-api/ClubInfoController"

// 0 disable 1 Clubable 2 activity
const opType = ref(0);
const showPanel = (type) =>{
  opType.value = type;
}

const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}


const formData = ref({});
const formDataRef = ref();

</script>


<style scoped lang="scss">
.person_body_right {
  width: 70%;
  /* height: 500px; */
  border-radius: 5px;
  background-color: white;
}

</style>