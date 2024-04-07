<template>
  <el-row :gutter="20">
    <el-col :span="3">
      <!--      <div class="flex-center">-->
      <!--        <span>Club</span>-->
      <!--      </div>-->
      <el-affix :offset="40">
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            style="height: 100vh; width: 100%"
            default-active="2"
            text-color="#fff"
            @open="handleOpen"
            @close="handleClose"
        >
          <el-sub-menu index="1">
            <template #title>
              <span>社团信息</span>
            </template>
            <el-menu-item-group v-for="item in clubs">
              <el-menu-item @click="showPanel(1, item)">{{ item.name }}</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
                  <el-menu-item @click="showPanel(2,null)">
<!--                    <el-icon><document /></el-icon>-->
                    <span>社团申请</span>
                  </el-menu-item>
        </el-menu>
      </el-affix>
    </el-col>
    <el-col :span="17">
      <div class="person_body_right" v-if="opType == 1">
        <el-card style="width: 600px; margin-top: 20px; margin-left: 200px;" shadow="hover">
          <el-descriptions title="社团信息" style="margin-left: 250px">
          </el-descriptions>
          <el-tag :type="clubInfo.isAdmitted ? 'success' : ''" disable-transitions>
            {{ clubInfo.isAdmitted?"审核通过":"审核中" }}
          </el-tag>
          <el-form label-width="80px" size="large">
            <el-form-item label="社团头像" style="margin-left: 10px; margin-top: 10px;">
              <!--            <img :src="clubInfo.avatar">-->
                <el-upload
                    class="avatar-uploader"
                    :action="uploadClubAvatarUrl"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload"
                    style="display: inline-block;width: 300px"
                    :headers="headers"
                >

                  <el-avatar :src='avatarData' size="large" alt="上传头像" />
                </el-upload>
            </el-form-item>

            <el-form-item label="社团名称" style="margin-left: 10px; margin-top: 10px;">
              <el-input v-model.trim="clubInfo.name" disabled autocomplete="off"
                        style="width: 400px;"></el-input>
            </el-form-item>
            <el-form-item label="社团简介" style="margin-left: 10px; margin-top: 10px;">
              <el-input v-model.trim="clubInfo.info" :disabled="editAble" autocomplete="off"
                        style="width: 400px;"></el-input>
            </el-form-item>
            <el-form-item label="社长" style="margin-left: 10px; margin-top: 10px;">
              <el-input v-model.trim="clubInfo.presidentId" disabled autocomplete="off" style="width: 400px;"></el-input>
            </el-form-item>
            <el-form-item label="活动地址" style="margin-left: 10px; margin-top: 10px;">
              <el-input v-model.trim="clubInfo.address" :disabled="editAble" autocomplete="off"
                        style="width: 400px;"></el-input>
            </el-form-item>
            <el-form-item label="联系方式" style="margin-left: 10px; margin-top: 10px;">
              <el-input v-model.trim="clubInfo.contact" :disabled="editAble" autocomplete="off"
                        style="width: 400px;"></el-input>
            </el-form-item>
            <el-form-item label="成员数量" style="margin-left: 10px; margin-top: 10px;">
              <el-input v-model.trim="clubInfo.member" disabled autocomplete="off" style="width: 400px;"></el-input>
            </el-form-item>
            <el-form-item label="公费" style="margin-left: 10px; margin-top: 10px;">
              <el-input v-model.trim="clubInfo.money" disabled autocomplete="off" style="width: 400px;"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button style="margin-left: 40px" type="primary" :icon="Edit" @click="showAble(!editAble)">
                {{ editAble ? '修改' : '暂存' }}
              </el-button>
              <el-button style="margin-left: 180px" type="primary" :icon="Upload" @click="handleUpdateInfo">保存
              </el-button>
            </el-form-item>
          </el-form>
        </el-card>
        <transition name="el-zoom-in-top">
          <el-tabs v-if="!isShowActivityMember" type="border-card" style="width: 1000px; margin-top: 20px; margin-left: 20px;">
            <el-tab-pane label="社员信息">
              <h1>社员管理</h1>
    <!--          <el-card>-->
                <el-table :data="clubMemberInfo" border style="width: 100%; margin-top: 20px">
                  <el-table-column prop="userId" label="社员id"/>
                  <el-table-column prop="username" label="用户名"/>
                  <el-table-column prop="name" label="名字"/>
                  <el-table-column prop="gender" label="性别"/>
                  <el-table-column prop="contributionMoney" label="贡献金额"/>
                  <el-table-column prop="phone" label="电话"/>
                  <el-table-column prop="email" label="邮箱"/>
  <!--                <el-table-column prop="email" label="邮箱"/>-->
                  <el-table-column prop="isPassed" label="状态" width="100">
                    <template #default="scope">
                      <el-tag :type="scope.row.isPassed ? 'success' : ''" disable-transitions>
                        {{ scope.row.isPassed ? "正常":"审核中" }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column fixed="right" label="操作" width="150">
                    <template #default="scope">
                      <el-button v-if="!scope.row.isPassed" type="text" size="small" @click="handlePassMember(scope.row)">通过</el-button>
                      <el-button type="text" size="small" @click="handleRemoveMember(scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
    <!--          </el-card>-->
            </el-tab-pane>
            <el-tab-pane label="活动信息">
              <h1>活动管理</h1>
              <el-button class="new_btn" type="primary" :disabled="!clubInfo.isAdmitted" @click="handleNew">新增</el-button>
              <el-table :data="activityInfo" border style="width: 1100px">
                <el-table-column prop="id" label="活动id"/>
                <el-table-column prop="name" label="活动名称"/>
                <el-table-column prop="title" label="活动主题"/>
                <el-table-column prop="address" label="活动地址"/>
                <el-table-column prop="sign" label="联系方式"/>
                <el-table-column prop="beginTime" label="开始时间"/>
                <el-table-column prop="endTime" label="结束时间"/>
                <el-table-column prop="money" label="报名费用"/>
                <el-table-column fixed="right" label="操作" width="220">
                  <template #default="scope">
                    <el-button type="text" size="small" @click="handleEdit(scope.row)">编辑</el-button>
                    <!--            <el-button type="text" size="small" v-if="scope.row.is_admitted === 0" @click="handleDetail(scope.row)">通过</el-button>-->
                    <el-button type="text" size="small" @click="handleDel(scope.row)">删除</el-button>
                    <el-button type="text" size="small" @click="handleActivityDetail(scope.row, scope.$index)">报名情况</el-button>
                  </template>
                </el-table-column>
              </el-table>
              <!-- 新建/编辑弹框 -->
              <dialog-activity
                  v-if="dialogShow"
                  v-model:dialogShow="dialogShow"
                  :rowInfo="rowInfo"
                  :title="title"
                  :arrayNum="activityInfo.length"
                  @add="addRow"
                  @update="editRow"
              />
            </el-tab-pane>
          </el-tabs>
        </transition>
        <transition name="el-zoom-in-top">
          <el-card v-if="isShowActivityMember" style="width: 1000px; margin-top: 20px; margin-left: 20px;">
          <el-page-header @back="isShowActivityMember=!isShowActivityMember">
            <template #content>
              <span class="text-large font-600 mr-3"> {{ activityInfo[selectId].name }} 报名情况 </span>
            </template>
          </el-page-header>
          <el-table :data="activityMemberInfo" border style="width: 100%">
            <el-table-column fixed prop="id" label="id" width="150" />
            <el-table-column prop="name" label="姓名" width="120" />
            <el-table-column prop="gender" label="性别" width="120" >
              <template #default="scope">
                <el-tag :type="scope.row.gender ? 'danger' : 'primary'">
                  {{ scope.row.gender ? "女":"男" }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="phone" label="联系电话" width="120" />
            <el-table-column prop="joinStatus" label="是否签到" width="120" >
              <template #default="scope">
                <el-tag :type="scope.row.joinStatus ? 'success' : 'warning'">
                  {{ scope.row.joinStatus ? "已签到":"未签到" }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="payStatus" label="是否支付" width="120" >
              <template #default="scope">
                <el-tag :type="scope.row.payStatus ? 'success' : 'danger'">
                  {{ scope.row.payStatus ? "已支付":"未支付" }}
                </el-tag>
              </template>
            </el-table-column>
          </el-table>
          </el-card>
        </transition>
      </div>
      <div class="person_body_right" v-if="opType == 2">
      <el-card style="width: 600px; margin-top: 20px; margin-left: 200px;" shadow="hover">
        <el-descriptions title="社团注册" style="margin-left: 250px" />

        <el-form label-width="80px" size="large" ref="applyForm" :model="applyClubInfo" @submit.prevent>
          <el-form-item prop="name" label="社团名称" style="margin-left: 10px; margin-top: 10px;" :rules="[{required: true, message: '输入社团名称', trigger: 'blur'}]">
            <el-input v-model="applyClubInfo.name" autocomplete="off" style="width: 400px;"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="margin-left: 180px" type="primary" :icon="Upload" @click="()=>applyClub(applyForm)">申请社团</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
    </el-col>
  </el-row>

</template>

<script lang="ts" setup>
import {onMounted, reactive, ref, toRefs} from "vue";
import {Edit, Upload} from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox, FormInstance} from "element-plus";
import Dialog from "@/views/modules/AdminClub/dialog.vue";
import DialogActivity from "@/views/modules/Club/dialogActivity.vue";
import {clubRegister, getSelfClubsAsPresident} from "@/api/backend-api/commonUserController";
import {
  activityAlterInfo, activityDelete,
  activityRelease,
  clubAlterInfo, clubPass, listUserByActivityId, userKick
} from "@/api/backend-api/presidentUserController";
import {getActivities, getClubAvatar, getUserClubExtendUser} from "@/api/backend-api/globalQueryController";
import {fen2Yuan} from "@/utils";




const editAble = ref(1)
const showAble = (type) => {
  editAble.value = type;
}

const handleUpdateInfo = () => {
  if (clubInfo.value.id === undefined) {
    ElMessage.error("错误的id");
    return;
  }
  clubAlterInfo({
    id: `${clubInfo.value.id}`,
    avatar: clubInfo.value.avatar,
    info: clubInfo.value.info,
    address: clubInfo.value.address,
    contact: clubInfo.value.contact,
  }).then(({data}) => {
    ElMessage.success("更新成功");
  }).catch(e => {
    ElMessage.error(`更新失败, ${e.message}`)
    console.log(e);
  })
}

const dialogShow = ref(false) // 新增/编辑弹框
const rowInfo = ref({}) // 新增/编辑的数据
const title = ref("") // 是新建还是修改
const activityInfo = ref<API.Activity[]>([])

const clubMemberInfo = ref<API.UserClubExtendUserVO[]>([]);
const clubs = ref<API.Club[]>([])


const handleNew = () => {
  title.value = "新增";
  rowInfo.value = {};
  dialogShow.value = true;
}
const handleDetail = (val: any) => {
  console.log(val);
  // data.rowInfo = val;
}
const handleEdit = (val: any) => {
  console.log(val);
  title.value = "修改";
  dialogShow.value = true;
  rowInfo.value = val;
}
const handleDel = (val: any) => {
  console.log(val);
  ElMessageBox.confirm("你确定删除这个活动吗?", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    activityDelete({
      id: val.id,
    }).then(({data}) => {
      ElMessage.success("删除成功");
      updateClubToTalInfo(''+clubInfo.value.id)
    }).catch(e => {
      ElMessage.error("删除错误");
      console.log(e);
    })
  }).catch(e => {});
}

const selectId = ref<number>(0);
const isShowActivityMember = ref(false);
const activityMemberInfo = ref<API.UserWithUserActivityStateVO>();
const handleActivityDetail = (row: any, index) => {
  console.log(index);
  selectId.value = index;
  listUserByActivityId({activityId: row.id}).then(({data}) => {
    ElMessage.success("查询成功");
    activityMemberInfo.value = data.data;
    isShowActivityMember.value = !isShowActivityMember.value;
  }).catch(e => {
    ElMessage.error(`查询失败 ${e.message}`);
  })
}


// 添加行
const addRow = (val: any) => {
  console.log("val", val);
  // ElMessage.error("此页面不支持添加");
  activityRelease({
    ...val,
    clubId: clubInfo.value.id,
    id: undefined,
    money: val.money*100
  }).then((res) => {
    ElMessage.success('提交成功');
    dialogShow.value = false;
    updateClubToTalInfo(''+clubInfo.value.id)
  }).catch(e => {
    ElMessage.error(`提交失败, ${e.message}`)
  })
  // data.studentInfo.push(val);
}
// 编辑行
const editRow = (val: any) => {
  activityAlterInfo({
    ...val,
    money: val.money*100
  }).then((res) => {
    ElMessage.success("提交成功");
    dialogShow.value = false;
    updateClubToTalInfo(''+clubInfo.value.id)
  }).catch(e => {
    ElMessage.error(`提交失败 ${e.message}`)
  })
}

// 0 disable 1 clubInfo  2 activityInfo
const opType = ref(0);
const clubInfo = ref<API.Club>({});
const showPanel = async (type, item) => {
  if (type == 1) {
    clubInfo.value = item;
    updateClubToTalInfo(item.id).then(() => {
      opType.value = type;
    })
  }
  else{
    opType.value = type;
  }
}

const updateClubToTalInfo = async (clubId: string) => {
  getActivities({
    id: clubId,
  }).then(({data}) => {
    for(let i in data.data||[]) {
      data.data[i].money = fen2Yuan(data.data[i].money);
    }
    activityInfo.value = data.data;
  }).catch(e=>{
    console.log(e);
    ElMessage.error("活动列表获取失败");
  })
  getClubAvatar({
    id: clubId,
  }).then(({data}) => {
    avatarData.value = 'data:image/png;base64,' + data.data;
  }).catch(e => {
    console.log(e);
    ElMessage.success("获取社团头像失败");
  })
  await getUserClubExtendUser({
    clubId,
  }).then(({data}) => {
    for(let i in data.data||[]) {
      data.data[i].money = fen2Yuan(data.data[i].money);
    }
    clubMemberInfo.value = data.data;
  }).catch(e => {
    console.log(e);
    ElMessage.error("社员列表获取失败")
  })

}


const handleRemoveMember = (row: any) => {
  ElMessageBox.confirm("你确定踢掉该成员吗", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    userKick({
      userId: ''+row.id,
      clubId: ''+clubInfo.value.id,
    }).then(({data}) => {
      ElMessage.success("删除成功");
      updateClubToTalInfo(''+clubInfo.value.id);
    }).catch(e => {
      ElMessage.error("删除失败");
      console.log(e);
    })
  }).catch(e => {});
}

const avatarData = ref('');
const headers = ref({'Authorization':sessionStorage.getItem('jwt')})
const handleAvatarSuccess = (res, file) => {
  if(res.status !== 100){
    ElMessage.error("上传头像错误");
  }
  else {
    // imageUrl.value = URL.createObjectURL(file.raw) + "?uuid=" + getUUID();
    ElMessage.success("上传头像成功");
    getClubAvatar({
      id: ''+clubInfo.value.id,
    }).then(({data}) => {
      avatarData.value = 'data:image/png;base64,' + data.data;
    }).catch(e => {
      console.log(e);
      ElMessage.success("获取社团头像失败");
    })
    // console.log(URL.createObjectURL(file.raw))
    // console.log(imageUrl.value)
    // console.log(res)
  }
}
const uploadBaseUrl = '/api/president/upload'
const uploadClubAvatarUrl = ref('');
const beforeAvatarUpload = (file) => {
  uploadClubAvatarUrl.value = `${uploadBaseUrl}?id=${clubInfo.value.id}`;
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

const handlePassMember = (row) => {
  clubPass({userId:row.userId, clubId: clubInfo.value.id}).then(({data}) => {
    ElMessage.success("通过成功");
    updateClubToTalInfo(''+clubInfo.value.id);
  }).catch(e => {
    ElMessage.error(`审核通过失败 ${e.message}`);
  })
}

const applyClubInfo = ref<API.ClubRegisterRequestDTO>({name:''});
const applyForm = ref();
const applyClub = (formEl: FormInstance | undefined) => {
  console.log(formEl);
  if (!formEl) return
  formEl.validate((valid, fields) => {
    console.log(applyClubInfo.value);
    if (valid) {
      clubRegister({name: applyClubInfo.value.name}).then(({data}) => {
        ElMessage.success("注册成功");
        getClubs();
      }).catch(e => {
        ElMessage.error(`注册失败, ${e.message}`);
      })
    } else {
      console.log('error submit!', fields)
    }
  })
}

const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const getClubs = () => {
  getSelfClubsAsPresident().then(({data}) => {
    for(let i in data.data||[]) {
      data.data[i].money = fen2Yuan(data.data[i].money);
    }
    clubs.value = data.data;
  }).catch(e => {
    ElMessage.error(`获取社团信息失败 ${e.message}`);
  })
}

onMounted(() => {
  getClubs();
})


</script>


<style scoped lang="scss">
.person_body_right {
  width: 70%;
  /* height: 500px; */
  border-radius: 5px;
  background-color: white;
}

.activities-class {
  padding: 20px;
  width: 80%;
  text-align: center;
  margin: 0 auto;
  align-items: center;
  justify-content: center;
}


.box {
  padding: 20px;
  margin: 50px;

  .new_btn {
    margin-bottom: 10px;
  }

  ::v-deep .el-table__cell {
    text-align: center;
  }
}

</style>