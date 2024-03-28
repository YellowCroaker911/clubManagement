<template>
  <el-row class="tac">
    <el-col :span="2">
      <el-menu
          active-text-color="#ffd04b"
          background-color="#545c64"
          class="el-menu-vertical-demo"
          default-active="2"
          text-color="#fff"
          @open="handleOpen"
          @close="handleClose"
      >
        <el-menu-item @click="showPanel(1, null)">
          <!--          <el-icon><document /></el-icon>-->
          <span>个人信息</span>
        </el-menu-item>
        <el-menu-item @click="showPanel(2, null)">
          <!--          <el-icon><document /></el-icon>-->
          <span>修改密码</span>
        </el-menu-item>
        <el-sub-menu index="1">
          <template #title>
            <span>社团</span>
          </template>
          <el-menu-item-group v-for="item in clubs">
            <el-menu-item @click="showPanel(3, item)">{{item.name}}</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
      </el-menu>
    </el-col>
    <div class="person_body_right" v-if="opType == 1">
      <el-card style="width: 800px; margin-top: 20px; margin-left: 200px;" shadow="hover">
        <el-descriptions title="个人信息" style="margin-left: 250px">
        </el-descriptions>
        <el-form label-width="80px" size="large">
          <el-form-item label="头像" style="margin-left: 10px; margin-top: 10px;">
            <el-upload
              class="avatar-uploader"
              action="/api/account/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              style="display: inline-block;width: 300px"
              :headers="headers"
            >
<!--            <el-avatar src='/api/user/getAvatar' size="large" alt="上传头像"/>-->
<!--            <i v-else class="el-icon-plus avatar-uploader-icon" />-->
              <el-avatar :src='avatarData' size="large" alt="上传头像" />
            </el-upload>
          </el-form-item>

          <el-form-item label="账号" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.username" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="昵称" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.name" :disabled="editAble" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="性别" style="width: 400px; margin-left: 10px; margin-top: 10px;">
<!--            <el-select v-model="userInfo.gender" placeholder="请选择你的性别" :disabled="editAble">-->
<!--              <el-option label="男" value="0" :key="0" />-->
<!--              <el-option label="女" value="1" :key="1" />-->
<!--            </el-select>-->
            <el-radio-group v-model="userInfo.gender" :disabled="editAble">
              <el-radio :value="0">男</el-radio>
              <el-radio :value="1">女</el-radio>
            </el-radio-group>
<!--            <el-input v-model.trim="userInfo.gender" :disabled="editAble" autocomplete="off" style="width: 400px;"></el-input>-->
          </el-form-item>
          <el-form-item label="电话" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.phone" :disabled="editAble" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.email" :disabled="editAble" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="所在社团" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="briefClubs" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="贡献金额" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.money" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="margin-left: 40px" type="primary" :icon="Edit" @click="showAble(!editAble)">{{editAble?'修改':'暂存'}}</el-button>
            <el-button style="margin-left: 180px" type="primary" @click="handleUpdateInfo">保存</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
    <div class="person_body_right" v-if="opType == 2">
      <el-card style="width: 800px; margin-top: 20px; margin-left: 200px;" shadow="hover">
        <el-form label-width="80px" size="large" :model="alterPswData" :rules="rules" ref="formRef">
          <el-form-item prop="oldPassword" label="原密码" style="margin-left: 10px; margin-top: 10px;">
            <el-input type="password" v-model="alterPswData.oldPassword" autocomplete="off" style="width: 400px;" show-password></el-input>
          </el-form-item>
          <el-form-item prop="newPassword" label="新密码" style="margin-left: 10px; margin-top: 10px;">
            <el-input type="password" v-model="alterPswData.newPassword" autocomplete="off" style="width: 400px;" show-password></el-input>
          </el-form-item>
          <el-form-item prop="confirmedPassword" label="确认密码" style="margin-left: 10px; margin-top: 10px;">
            <el-input type="password" v-model="alterPswData.confirmedPassword" autocomplete="off" style="width: 400px;" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="margin-left: 180px" type="primary" @click="onAlterPsw(formRef)">修改密码</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
    <div class="person_body_right" v-if="opType == 3">
      <el-card style="width: 600px; margin-top: 20px; margin-left: 200px;" shadow="hover">
        <el-descriptions title="社团信息" style="margin-left: 250px">
        </el-descriptions>
        <el-form label-width="80px" size="large">
          <el-form-item label="社团头像" style="margin-left: 10px; margin-top: 10px;">
            <img :src="clubInfo.avatar">
          </el-form-item>

          <el-form-item label="社团名称" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="clubInfo.name" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="社团简介" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="clubInfo.info" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="社长" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="clubInfo.presidentId" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="活动地址" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="clubInfo.address" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="联系方式" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="clubInfo.contact" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="成员数量" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="clubInfo.member" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="公费" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="clubInfo.money" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </el-row>

</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {  Edit, Upload } from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox, FormInstance, FormRules} from "element-plus";
import {useRouter} from "vue-router";
import {alterPassword, alterSelfInfo, getAvatar, getSelfInfo} from "@/api/backend-api/userAccountController";
import {getUUID} from "@/utils";
import CustomImg from "@/App.vue";

const editAble = ref(1)
const headers = ref({'Authorization':sessionStorage.getItem('jwt')})
const showAble = (type) =>{
  editAble.value = type;
}

// 0 disable 1 userInfo  2 resetPassword  3 clubInfo
const opType = ref(1);
const clubInfo = ref();

const showPanel = (type, data) =>{
  opType.value = type;
  if(type == 3){
    clubInfo.value = data;
  }
}

const userInfo = ref<API.User>({});
const briefClubs = ref<string>('');
const clubs = ref<API.Club[]>([
  {
    id: 1,
    name: "fex",
    avatar: undefined,
    info: undefined,
    presidentId: undefined,
    address: undefined,
    contact: undefined,
    member: undefined,
    money: undefined
  },
  {
    id: 2,
    name: "maple",
    avatar: undefined,
    info: undefined,
    presidentId: undefined,
    address: undefined,
    contact: undefined,
    member: undefined,
    money: undefined
  }
])

const router = useRouter();
const isLoading = ref(false)
const alterPswData = reactive<API.UserAlterPasswordRequestDTO>({
  oldPassword: '',
  newPassword: '',
  confirmedPassword: '',
});
const formRef = ref();

const equalToPassword = (rule: any, value: any, callback: any) => {
  if (alterPswData.newPassword !== value) {
    callback(new Error("两次输入的密码不一致"));
  } else {
    callback();
  }
};
const rules = reactive<FormRules>({
  oldPassword:[
    {required:true, message: '密码不能为空', trigger: 'blur'},
    {min:6, max:20, message: '密码必须在6到20位之间', trigger: 'blur'}
  ],
  newPassword:[
    {required:true, message: '密码不能为空', trigger: 'blur'},
    {min:6, max:20, message: '密码必须在6到20位之间', trigger: 'blur'}
  ],
  confirmedPassword:[
    {required:true, message: '请确认输入密码', trigger: 'blur'},
    {min:6, max:20, message: '密码必须在6到20位之间', trigger: 'blur'},
    { required: true, validator: equalToPassword, trigger: "blur" }
  ],
})



const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}


const onAlterPsw = async (formEl: FormInstance | undefined) => {
  console.log(alterPswData);
  console.log(formEl);
  if(!formEl)return;
  console.log("123123");
  formEl.validate((valid, fields) => {
    console.log(valid, fields);
    if(valid) {
      isLoading.value = true;
      //todo 检验密码
      alterPassword({
        ...alterPswData
      }).then(({data}) => {
        ElMessage.success('修改密码成功');
      }).catch((e) => {
        ElMessage.error(`修改密码失败， ${e.message}`);
      })

    }
    else{
      console.log(fields)
      ElMessage.error("填写有误");
    }
  })
}

const handleUpdateInfo = () => {
  alterSelfInfo({
    name: userInfo.value.name,
    gender: `${userInfo.value.gender}`,
    phone: userInfo.value.phone,
    email: userInfo.value.email,
  }).then(({data}) => {
    ElMessage.success("修改成功");
  }).catch(e => {
    ElMessage.error(`修改密码失败， ${e.message}`);
  })
}


const imageUrl = ref('');
const handleAvatarSuccess = (res, file) => {
  if(res.status !== 100){
    ElMessage.error("上传头像错误");
  }
  else {
    // imageUrl.value = URL.createObjectURL(file.raw) + "?uuid=" + getUUID();
    ElMessage.success("上传头像成功");
    window.location.reload();
    // console.log(URL.createObjectURL(file.raw))
    // console.log(imageUrl.value)
    // console.log(res)
  }
}
const beforeAvatarUpload = (file) => {
  const isJPG = file.type === 'image/jpeg';
  const isLt2M = file.size / 1024 / 1024 < 2;

  if (!isJPG) {
    ElMessage.error('上传头像图片只能是 JPG 格式!');
  }
  if (!isLt2M) {
    ElMessage.error('上传头像图片大小不能超过 2MB!');
  }
  return isJPG && isLt2M;
}


const avatarData=ref();
onMounted(() => {
  getSelfInfo().then(({data}) => {
    sessionStorage.setItem('user', JSON.stringify(data.data));
    userInfo.value = data.data || {};
  })
  getAvatar().then(({data})=> {
    avatarData.value = 'data:image/png;base64,' + data.data;
  })
})

</script>


<style scoped lang="scss">
.person_body_right {
  width: 70%;
  /* height: 500px; */
  border-radius: 5px;
  background-color: white;
}

.activities-class{
  padding: 20px;
  width: 80%;
  text-align: center;
  margin: 0 auto;
  align-items: center;
  justify-content: center;
}

</style>