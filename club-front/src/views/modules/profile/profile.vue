<!--<template>-->
<!--  <div>-->
<!--    <div class="PersonTop">-->
<!--      <div class="PersonTop_img">-->
<!--        <img v-image-preview :src="user.avatar" />-->
<!--      </div>-->
<!--      <div class="PersonTop_text">-->
<!--        <div class="user_text">-->
<!--          <div class="user_name">-->
<!--            <span> {{ user.name }} </span>-->
<!--          </div>-->
<!--&lt;!&ndash;          <div class="user-v" v-if="v === 3">&ndash;&gt;-->
<!--&lt;!&ndash;            <img src="@/assets/img/V.png" class="user-v-img" />&ndash;&gt;-->
<!--&lt;!&ndash;            <span class="user-v-font">优质媒体作者</span>&ndash;&gt;-->
<!--&lt;!&ndash;          </div>&ndash;&gt;-->
<!--          <div class="user_qianming">-->
<!--            <span> {{ '....' }}</span>-->
<!--          </div>-->
<!--          <div class="user_anniu">-->
<!--            <el-button-->
<!--                class="el-icon-edit"-->
<!--                v-if="this.$route.params.id === this.$store.state.id"-->
<!--                type="primary"-->
<!--                size="medium"-->
<!--                plain-->
<!--                @click="edit"-->
<!--            >编辑</el-button-->
<!--            >-->
<!--            <el-button-->
<!--                v-else-->
<!--                @click="follow"-->
<!--                type="primary"-->
<!--                size="medium"-->
<!--                icon="el-icon-check"-->
<!--            ></el-button>-->
<!--          </div>-->
<!--        </div>-->
<!--&lt;!&ndash;        <div class="user_num">&ndash;&gt;-->
<!--&lt;!&ndash;          <div style="cursor: pointer" @click="myfan">&ndash;&gt;-->
<!--&lt;!&ndash;            <div class="num_number">{{ fanCounts }}</div>&ndash;&gt;-->
<!--&lt;!&ndash;            <span class="num_text">粉丝</span>&ndash;&gt;-->
<!--&lt;!&ndash;          </div>&ndash;&gt;-->
<!--&lt;!&ndash;          <div style="cursor: pointer" @click="myfollow">&ndash;&gt;-->
<!--&lt;!&ndash;            <div class="num_number">{{ followCounts }}</div>&ndash;&gt;-->
<!--&lt;!&ndash;            <span class="num_text">关注</span>&ndash;&gt;-->
<!--&lt;!&ndash;          </div>&ndash;&gt;-->
<!--&lt;!&ndash;          <div>&ndash;&gt;-->
<!--&lt;!&ndash;            <div class="num_number">{{ goodCounts }}</div>&ndash;&gt;-->
<!--&lt;!&ndash;            <span class="num_text">获赞</span>&ndash;&gt;-->
<!--&lt;!&ndash;          </div>&ndash;&gt;-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div class="person_body">-->
<!--      <div class="person_body_left">-->
<!--        <el-card class="box-card" :body-style="{ padding: '0px' }">-->
<!--          <div slot="header" class="clearfix">-->
<!--            <span class="person_body_list" style="border-bottom: none"-->
<!--            >个人中心</span-->
<!--            >-->
<!--          </div>-->
<!--          &lt;!&ndash; <div-->
<!--            class="person_body_list"-->
<!--            v-for="(item, index) in person_body_list"-->
<!--            :key="index"-->
<!--          >-->
<!--            <router-link :to="{ name: item.name, params: item.params }">{{-->
<!--              item.label-->
<!--            }}</router-link>-->
<!--          </div> &ndash;&gt;-->
<!--          <el-menu-->
<!--              router-->
<!--              active-text-color="#00c3ff"-->
<!--              class="el-menu-vertical-demo"-->
<!--          >-->
<!--            <el-menu-item-->
<!--                index="info"-->
<!--                :route="{ name: 'info', params: $route.params.id }"-->
<!--            >-->
<!--              <i class="el-icon-user"></i>-->
<!--              <span slot="title">个人简介</span>-->
<!--            </el-menu-item>-->
<!--            <el-menu-item-->
<!--                index="myarticle"-->
<!--                :route="{ name: 'myarticle', params: $route.params.id }"-->
<!--            >-->
<!--              <i class="el-icon-edit-outline"></i>-->
<!--              <span slot="title">发帖</span>-->
<!--            </el-menu-item>-->
<!--            <el-menu-item-->
<!--                index="mycollect"-->
<!--                :route="{ name: 'mycollect', params: $route.params.id }"-->
<!--            >-->
<!--              <i class="el-icon-document"></i>-->
<!--              <span slot="title">收藏</span>-->
<!--            </el-menu-item>-->
<!--            <el-menu-item-->
<!--                index="myfan"-->
<!--                :route="{ name: 'myfan', params: $route.params.id }"-->
<!--            >-->
<!--              <i class="el-icon-tableware"></i>-->
<!--              <span slot="title">粉丝</span>-->
<!--            </el-menu-item>-->
<!--            <el-menu-item-->
<!--                index="myfollow"-->
<!--                :route="{ name: 'myfollow', params: $route.params.id }"-->
<!--            >-->
<!--              <i class="el-icon-circle-plus-outline"></i>-->
<!--              <span slot="title">关注</span>-->
<!--            </el-menu-item>-->
<!--          </el-menu>-->
<!--        </el-card>-->
<!--      </div>-->
<!--      <div class="person_body_right">-->
<!--        <router-view></router-view>-->
<!--      </div>-->
<!--    </div>-->
<!--    <profile-edit ref="dia" @flesh="reload" />-->
<!--  </div>-->
<!--</template>-->

<!--<script lang="ts" setup>-->

<!--import profileEdit from "./profileEdit.vue";-->
<!--import {onMounted, reactive, ref} from "vue";-->
<!--import {getCurrentUser} from "@/utils";-->
<!--import {getinfo} from "@/api/backend-api/infoController";-->

<!--// components: { PersonalDia },-->
<!--// name: "Personal",-->
<!--// inject: ["reload"],-->
<!--const user = ref<API.User>({});-->
<!--// design: "",-->
<!--// followCounts: "",-->
<!--// fanCounts: "",-->
<!--// goodCounts: "",-->
<!--// isfollow: true,-->
<!--// followData: {-->
<!--//   fanId: "",-->
<!--//   followId: "",-->
<!--// },-->
<!--// isfollowid: [],-->


<!--  // watch: {-->
<!--  //   $route(to, from) {-->
<!--  //     if (to.path == `/newsuser/personal/${this.$store.state.id}`) {-->
<!--  //       this.reload();-->
<!--  //     } else if (to.path == `/newsuser/personal/${this.$route.params.id}`) {-->
<!--  //       this.reload();-->
<!--  //     }-->
<!--  //   },-->
<!--  // },-->
<!--const load => () {-->
<!--  getinfo().then(({data}) => {-->
<!--    console.log(data);-->
<!--    user.value = data.data || {};-->
<!--    // this.avatar = data.data.avatar;-->
<!--    // this.nickname = data.data.nickname;-->
<!--    // this.v = data.data.v;-->
<!--    // this.design = data.data.design;-->
<!--  }).catch((err) => {-->
<!--    console.log(err);-->
<!--  });-->

<!--  // myFollow(this.$store.state.id)-->
<!--  //     .then((res) => {-->
<!--  //       res.data.forEach((res) => {-->
<!--  //         this.isfollowid.push(res.id);-->
<!--  //       });-->
<!--  //     })-->
<!--  //     .catch((err) => {-->
<!--  //       console.log(err);-->
<!--  //     });-->
<!--  //-->
<!--  // followAndFanCount(this.$route.params.id)-->
<!--  //     .then((res) => {-->
<!--  //       this.followCounts = res.data.followCounts;-->
<!--  //       this.fanCounts = res.data.fanCounts;-->
<!--  //     })-->
<!--  //     .catch((err) => {-->
<!--  //       console.log(err);-->
<!--  //     });-->
<!--  //-->
<!--  // mygoodCount(this.$route.params.id)-->
<!--  //     .then((res) => {-->
<!--  //       this.goodCounts = res.data.goodCounts;-->
<!--  //     })-->
<!--  //     .catch((err) => {-->
<!--  //       console.log(err);-->
<!--  //     });-->
<!--}-->
<!--// myfan() {-->
<!--//   this.$router.push({-->
<!--//     path: `/newsuser/personal/myfan/${this.$route.params.id}`,-->
<!--//   });-->
<!--// },-->
<!--// myfollow() {-->
<!--//   this.$router.push({-->
<!--//     path:`/newsuser/personal/myfollow/${this.$route.params.id}`,-->
<!--//   });-->
<!--// },-->
<!--// follow() {-->
<!--//   if (!this.$store.state.id) {-->
<!--//     this.$message({-->
<!--//       showClose: true,-->
<!--//       message: "请登录后再进行操作哦",-->
<!--//       type: "warning",-->
<!--//     });-->
<!--//   } else {-->
<!--//     this.followData.followId = this.$route.params.id;-->
<!--//     this.followData.fanId = this.$store.state.id;-->
<!--//     if (this.isfollowid.indexOf(this.followData.followId) > -1) {-->
<!--//       this.isfollow = true;-->
<!--//     } else {-->
<!--//       this.isfollow = false;-->
<!--//     }-->
<!--//     if (this.isfollow) {-->
<!--//       deleteFollow(this.followData)-->
<!--//           .then((res) => {-->
<!--//             this.isfollow = false;-->
<!--//             this.$message({-->
<!--//               showClose: true,-->
<!--//               message: "已取消关注",-->
<!--//               type: "success",-->
<!--//             });-->
<!--//             this.reload();-->
<!--//           })-->
<!--//           .catch((err) => {-->
<!--//             console.log(err);-->
<!--//           });-->
<!--//     } else if (!this.isfollow) {-->
<!--//       addFollow(this.followData)-->
<!--//           .then((res) => {-->
<!--//             this.isfollow = true;-->
<!--//             this.$message({-->
<!--//               showClose: true,-->
<!--//               message: "已成功关注",-->
<!--//               type: "success",-->
<!--//             });-->
<!--//             this.reload();-->
<!--//           })-->
<!--//           .catch((err) => {-->
<!--//             console.log(err);-->
<!--//           });-->
<!--//     }-->
<!--//   }-->
<!--// },-->
<!--const edit = () => {-->
<!--  this.$refs.dia.open();-->
<!--}-->
<!--onMounted(() => {-->
<!--  load();-->
<!--})-->
<!--</script>-->

<!--<style scoped>-->
<!--.me-video-player {-->
<!--  background-color: transparent;-->
<!--  width: 100%;-->
<!--  height: 100%;-->
<!--  object-fit: fill;-->
<!--  display: block;-->
<!--  position: fixed;-->
<!--  left: 0;-->
<!--  z-index: 0;-->
<!--  top: 0;-->
<!--}-->
<!--.PersonTop {-->
<!--  width: 1000px;-->
<!--  height: 140px;-->
<!--  padding-top: 20px;-->
<!--  background-color: white;-->
<!--  margin-top: 30px;-->
<!--  position: absolute;-->
<!--  left: 50%;-->
<!--  transform: translateX(-50%);-->
<!--  display: flex;-->
<!--  border-radius: 5px;-->
<!--}-->

<!--.PersonTop_img {-->
<!--  width: 150px;-->
<!--  height: 120px;-->
<!--  background-color: #8c939d;-->
<!--  margin-right: 24px;-->
<!--  margin-left: 20px;-->
<!--  overflow: hidden;-->
<!--  border-radius: 20px;-->
<!--}-->

<!--.PersonTop_img img {-->
<!--  width: 100%;-->
<!--  height: 100%;-->
<!--  border-radius: 20px;-->
<!--}-->

<!--.PersonTop_text {-->
<!--  height: 120px;-->
<!--  width: 880px;-->
<!--  display: flex;-->
<!--}-->

<!--.user_text {-->
<!--  width: 60%;-->
<!--  height: 100%;-->
<!--  line-height: 30px;-->
<!--}-->

<!--.user_name {-->
<!--  font-weight: bold;-->
<!--}-->
<!--.user-v {-->
<!--  margin-bottom: -5px;-->
<!--}-->
<!--.user-v-img {-->
<!--  width: 15px;-->
<!--  height: 15px;-->
<!--}-->
<!--.user-v-font {-->
<!--  font-size: 15px;-->
<!--  color: #00c3ff;-->
<!--}-->
<!--.user_qianming {-->
<!--  font-size: 14px;-->
<!--  color: #999;-->
<!--}-->

<!--.user_num {-->
<!--  width: 40%;-->
<!--  height: 100%;-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--}-->

<!--.user_num > div {-->
<!--  text-align: center;-->
<!--  border-right: 1px dotted #999;-->
<!--  box-sizing: border-box;-->
<!--  width: 80px;-->
<!--  height: 40px;-->
<!--  line-height: 20px;-->
<!--}-->

<!--.num_text {-->
<!--  color: #999;-->
<!--}-->

<!--.num_number {-->
<!--  font-size: 20px;-->
<!--  color: #333;-->
<!--}-->
<!--.el-menu-item>span {-->
<!--  font-size: 16px;-->
<!--  color: #999;-->
<!--}-->

<!--/*下面部分样式*/-->
<!--.person_body {-->
<!--  width: 1000px;-->
<!--  margin-top: 210px;-->
<!--  display: flex;-->
<!--  position: absolute;-->
<!--  left: 50%;-->
<!--  transform: translateX(-50%);-->
<!--  border-radius: 5px;-->
<!--}-->

<!--.person_body_left {-->
<!--  width: 27%;-->
<!--  height: 600px;-->
<!--  border-radius: 5px;-->
<!--  margin-right: 3%;-->
<!--  text-align: center;-->
<!--}-->

<!--.person_body_list {-->
<!--  width: 100%;-->
<!--  height: 50px;-->
<!--  margin-top: 25px;-->
<!--  font-size: 22px;-->
<!--  border-bottom: 1px solid #f0f0f0;-->
<!--  background-image: -webkit-linear-gradient(-->
<!--      left,-->
<!--      rgb(42, 134, 141),-->
<!--      #e9e625dc 20%,-->
<!--      #3498db 40%,-->
<!--      #e74c3c 60%,-->
<!--      #09ff009a 80%,-->
<!--      rgba(82, 196, 204, 0.281) 100%-->
<!--  );-->
<!--  -webkit-text-fill-color: transparent;-->
<!--  -webkit-background-clip: text;-->
<!--  -webkit-background-size: 200% 100%;-->
<!--  -webkit-animation: masked-animation 4s linear infinite;-->
<!--}-->

<!--.el-menu-item {-->
<!--  margin-top: 22px;-->
<!--}-->

<!--.person_body_right {-->
<!--  width: 70%;-->
<!--  /* height: 500px; */-->
<!--  border-radius: 5px;-->
<!--  background-color: white;-->
<!--}-->

<!--.box-card {-->
<!--  height: 500px;-->
<!--}-->

<!--/*ui样式*/-->
<!--.el-button {-->
<!--  width: 84px;-->
<!--}-->
<!--</style>-->


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
        <el-menu-item @click="showPanel(1)">
          <!--          <el-icon><document /></el-icon>-->
          <span>个人信息</span>
        </el-menu-item>
        <el-menu-item @click="showPanel(2)">
          <!--          <el-icon><document /></el-icon>-->
          <span>修改密码</span>
        </el-menu-item>
        <el-sub-menu index="1">
          <template #title>
            <span>社团信息</span>
          </template>
          <el-menu-item-group v-for="item in clubs">
            <el-menu-item @click="showPanel(item)">{{item.name}}</el-menu-item>
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
              action="/api/user/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              style="display: inline-block;width: 300px"
            >
            <el-avatar src='/api/user/getAvatar' size="large" alt="上传头像"/>
<!--            <i v-else class="el-icon-plus avatar-uploader-icon" />-->
            </el-upload>
          </el-form-item>

          <el-form-item label="账号" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.username" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="昵称" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.name" :disabled="editAble" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="性别" style="width: 400px; margin-left: 10px; margin-top: 10px;">
            <el-select v-model="userInfo.gender" placeholder="请选择你的性别" :disabled="editAble">
              <el-option label="男" value="0" />
              <el-option label="女" value="1" />
            </el-select>
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
  </el-row>

</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {  Edit, Upload } from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox, FormInstance, FormRules} from "element-plus";
import {useRouter} from "vue-router";
import {alterInfo, alterPassword, getInfo} from "@/api/backend-api/userController";
import {getUUID} from "@/utils";

const editAble = ref(1)

const showAble = (type) =>{
  editAble.value = type;
}

// 0 disable 1 userInfo  2 resetPassword
const opType = ref(1);

const showPanel = (type) =>{
  opType.value = type;
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
  alterInfo({
    name: userInfo.value.name,
    gender: userInfo.value.gender,
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



onMounted(() => {
  getInfo().then(({data}) => {
    sessionStorage.setItem('user', JSON.stringify(data.data));
    userInfo.value = data.data || {};
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