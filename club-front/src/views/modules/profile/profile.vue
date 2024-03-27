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
      </el-menu>
    </el-col>
    <div class="person_body_right" v-if="opType == 1">
      <el-card style="width: 800px; margin-top: 20px; margin-left: 200px;" shadow="hover">
        <el-descriptions title="个人信息" style="margin-left: 250px">
        </el-descriptions>
        <el-form label-width="80px" size="large">
          <el-form-item label="头像" style="margin-left: 10px; margin-top: 10px;">
            <img :src="userInfo.avatar">
          </el-form-item>

          <el-form-item label="账号" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.username" disabled="editAble" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="昵称" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.name" disabled="editAble" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="性别" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.gender" disabled="editAble" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="电话" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.phone" disabled="editAble" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="所在社团" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.clubs" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="贡献金额" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.money" disabled autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="margin-left: 40px" type="primary" :icon="Edit" @click="showAble(1)"/>
            <el-button style="margin-left: 180px" type="primary" :icon="Upload"/>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
    <div class="person_body_right" v-if="opType == 2">
      <el-card style="width: 800px; margin-top: 20px; margin-left: 200px;" shadow="hover">
        <el-form label-width="80px" size="large" :rules="rules" :ref="formRef">
          <el-form-item label="原密码" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.username" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="新密码" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.name" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" style="margin-left: 10px; margin-top: 10px;">
            <el-input v-model.trim="userInfo.name" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item>
              <el-button style="margin-left: 180px" type="primary" :icon="Upload" @click="onSubmit(formRef)"/>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </el-row>

</template>

<script lang="ts" setup>
import {reactive, ref} from "vue";
import {  Edit, Upload } from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox, FormInstance, FormRules} from "element-plus";
import {useRouter} from "vue-router";
import {getToken} from "@/api/backend-api/loginController";
import {getinfo} from "@/api/backend-api/infoController";
import {register} from "@/api/backend-api/registerController";

const editAble = ref(0)

const showAble = (type) =>{
  editAble.value = type;
}

// 0 disable 1 userInfo  2 resetPassword
const opType = ref(0);

const showPanel = (type) =>{
  opType.value = type;
}

const userInfo = ref({
  username: 1,
  avatar: 1,
  name: 1,
  gender: 1,
  phone: 1,
  money: 1,
  clubs: 1
})

const router = useRouter();
const isLoading = ref(false)
const formData = reactive<API.>({
  username: '',
  password: '',
  confirmedPassword: '',
});
const formRef = ref();

const equalToPassword = (rule: any, value: any, callback: any) => {
  if (formData.password !== value) {
    callback(new Error("两次输入的密码不一致"));
  } else {
    callback();
  }
};
const rules = reactive<FormRules>({
  password:[
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


const onSubmit = async (formEl: FormInstance | undefined) => {
  if(!formEl)return;
  formEl.validate((valid, fields) => {
    if(valid) {
      isLoading.value = true;
      //todo 检验密码
    }
    else{
      console.log(fields)
      ElMessage.error("填写有误");
    }
  })

}

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