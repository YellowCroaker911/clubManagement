<template>
  <div class="login-body">
    <div class="bg"></div>
    <div class="login-panel">
      <el-form ref="formRef" class="login-register" :model="formData"  :rules="rules">
        <div class="login-title">社团管理系统</div>
        <el-tabs class="login-title" v-model="tabName" @tab-click="handleClick" :disabled="isLoading">
          <el-tab-pane label="登录" name="login" />
          <el-tab-pane label="注册" name="register" />
        </el-tabs>
        <el-form-item prop="username">
          <el-input size="large" clearable placeholder="用户名" v-model="formData.username" maxlength="150">
            <template #prefix>
            <span class="iconfont icon-account"></span>
            </template>
          </el-input>
        </el-form-item>
        <!--密码输入-->
        <el-form-item prop="password">
          <el-input type="password" size="large" placeholder="请输入密码" v-model="formData.password" show-password>
            <template #prefix>
            <span class="iconfont icon-password"></span>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item v-if="tabName === 'register'" prop="confirmedPassword">
          <el-input type="password" size="large" placeholder="确认密码" v-model="formData.confirmedPassword"
            show-password>
            <template #prefix>
            <span class="iconfont icon-password"></span>
            </template>
          </el-input>
        </el-form-item>
        <!--验证码-->
        <!-- <el-form-item pro="checkCode">
            <div class="check-code-panel">
            <el-input
              size="large"
              placeholder="请输入验证码"
              v-model="formData.checkCode"
              >
              <template #prefix>
                <span class="iconfont icon-checkcode"></span>
              </template>
            </el-input>
            <img :src="captchaPath" class="check-code" @click="changeCheckCode()" alt=""/>
            </div>
          </el-form-item> -->
        <!--下拉框-->
        <!-- <el-form-item>
            <div class="rememberme-panel">
              <el-checkbox v-mode="formData.rememberMe">记住我</el-checkbox>
            </div> 
            <div class="no-account">
              <a href="javasciprt:void(0)" class="a-link">忘记密码?</a>
              <a href="javasciprt:void(0)" class="a-link">没有账号?</a>
            </div>
          </el-form-item> -->
        <!--登录按钮-->
        <el-form-item>
          <el-button type="primary" class="op-btn" size="large" @click="onSubmit(formRef)" :loading="isLoading">
            {{ tabName === 'login' ? '登录' : '注册' }}
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { getUUID } from '@/utils'
import {ElMessage, FormInstance, FormRules} from 'element-plus'
import { ref, reactive, getCurrentInstance, } from "vue";
import httpRequest from "@/api/httpRequest";
import {getInfo, getToken, userRegister} from "@/api/backend-api/userController";
// import {getToken} from "@/api/backend-api/loginController";
// import {register} from "@/api/backend-api/registerController";
const { proxy } = getCurrentInstance()


const router = useRouter();
const tabName = ref('login')
const isLoading = ref(false)
const formData = reactive<API.UserRegisterRequestDTO>({
  username: '',
  password: '',
  confirmedPassword: '',
  role: '0',
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
  username:[
    {required:true, message: '用户名不能为空', trigger: 'blur'},
    {min:1, max:20, message: '用户名长度需要在1-20之间', trigger: 'blur'}
  ],
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

const captchaPath = ref('')

function changeCheckCode() {
  // 给一个不同的url重发请求
  captchaPath.value = `/api/captcha.jpg?uid=${getUUID()}`;
}
const handleClick = (tab, even) => {
  if(tabName.value !== tab.props.name) {
    formRef.value.clearValidate();
    formData.username = '';
    formData.password = '';
    formData.confirmedPassword = '';
  }
}
const onSubmit = async (formEl: FormInstance | undefined) => {
  if(!formEl)return;
  formEl.validate((valid, fields) => {
    if(valid){
      console.log("qweqwe");
      console.log(formData);
      console.log(formData.username);
      console.log(formData.password);
      isLoading.value = true;
      if(tabName.value === 'login'){
        getToken({
          username: formData.username,
          password: formData.password
        }).then(({data}) => {
          console.log(data);
          console.log("px", proxy)
          // proxy.$http.defaults.headers.common['Authorization'] = `Bearer ${data.data.token}`;
          ElMessage.success('登录成功');
          sessionStorage.setItem('jwt', `Bearer ${data.data.token}`);
          getInfo().then((res:any) => {
            sessionStorage.setItem('user', JSON.stringify(res.data.data));
            console.log("123");
            router.push({path: '/'}).then(()=>window.location.reload());
          })
        }).catch(e => {
          ElMessage.error(`登陆失败, ${e.message}`)
          console.log("error", e);
        }).finally(() => {
          isLoading.value = false;
        });
        // proxy.$http.post("/api/user/account/token/", {
        //   username: formData.username,
        //   password: formData.password
        // }).then(({ data }) => {
        //   console.log(data);
        //   // sessionStorage.setItem('jwt', `Bearer ${data.data.token}`);
        //   proxy.$http.defaults.headers.common['Authorization'] = `Bearer ${data.data.token}`;
        //
        // }).catch(e => {
        //   ElMessage.error('Oops, this is a error message.')
        //   console.log("error", e);
        // });
      }
      else{
        userRegister({
          ...formData,
          role: '0'
        }).then(({data}) => {
          console.log("ok", data);
          ElMessage.success('注册成功');
          window.location.reload();
        }).catch(e => {
          console.log("error", e);
          ElMessage.error(`注册失败, ${e.message}`)
        }).finally(() => {
          isLoading.value = false;
        })
      }
    }
    else{
      console.log(fields)
      ElMessage.error("填写有误");
    }
  })

}
</script>

<style lang="scss" scoped>
.login-body {
  height: calc(100vh);
  background-size: cover;
  background: url("@/assets/login_bg.jpg");
  display: flex;

  .bg {
    flex: 1;
    background-size: cover;
    background-position: center;
    background-size: 800px;
    background-repeat: no-repeat;
    background-image: url("@/assets/login_img.png");
  }

  .login-panel {
    width: 430px;
    margin-right: 15%;
    margin-top: calc((100vh - 500px) / 2);

    .login-register {
      padding: 25px;
      background: #fff;
      border-radius: 5px;

      .login-title {
        text-align: center;
        font-size: 18px;
        font-weight: bold;
        margin-bottom: 20px;
      }

      .send-email-panel {
        display: flex;
        width: 100%;
        justify-content: space-between;

        .send-email-btn {
          margin-left: 5px;
        }
      }

      .rememberme-panel {
        width: 100%;
      }

      .no-account {
        width: 100%;
        display: flex;
        justify-content: space-between;
      }

      .op-btn {
        width: 100%;
      }
    }
  }

  .check-code-panel {
    width: 100%;
    display: flex;

    .check-code {
      margin-left: 5px;
      cursor: pointer;
    }
  }

  .login-btn-qq {
    margin-top: 20px;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;

    img {
      cursor: pointer;
      margin-left: 10px;
      width: 20px;
    }
  }
}
</style>
<!-- 

  eyJhbGciOiJIUzI1NiJ9.
  eyJqdGkiOiJjYjNkN2EwMTA3Y2E0ZDZlOTA2OTk4MDA1ZDhhMzgyZSIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTcxMTE4NjgxMCwiZXhwIjoxNzEyMzk2NDEwfQ.
  a-Ie7b_ZvsBPrKWpIgAZxfz5ZMIX1WRHoSPTXzXYGSM"

 -->