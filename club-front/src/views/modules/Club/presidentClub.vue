<template>
  <el-row class="tac">
    <el-col :span="2">
      <!--      <div class="flex-center">-->
      <!--        <span>Club</span>-->
      <!--      </div>-->
      <el-affix :offset="40">
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
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
          <!--        <el-menu-item @click="showPanel(2,null)">-->
          <!--          <el-icon><document /></el-icon>-->
          <!--          <span>活动管理</span>-->
          <!--        </el-menu-item>-->
        </el-menu>
      </el-affix>
    </el-col>

    <div class="person_body_right" v-if="opType == 1">
      <el-card style="width: 600px; margin-top: 20px; margin-left: 200px;" shadow="hover">
        <el-descriptions title="社团信息" style="margin-left: 250px">
        </el-descriptions>
        <el-form label-width="80px" size="large">
          <el-form-item label="社团头像" style="margin-left: 10px; margin-top: 10px;">
            <!--            <img :src="clubInfo.avatar">-->
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
      <el-tabs type="border-card">
        <el-tab-pane label="社员信息">
<!--          <el-card>-->
            <el-table :data="clubMemberInfo" border style="width: 100%">
              <el-table-column prop="id" label="社员id"/>
              <el-table-column prop="name" label="名字"/>
              <el-table-column prop="gender" label="性别"/>
              <el-table-column prop="money" label="贡献金额"/>
              <el-table-column prop="phone" label="电话"/>
              <el-table-column prop="email" label="邮箱"/>
              <el-table-column fixed="right" label="操作" width="220">
                <template #default="scope">
                  <el-button type="text" size="small" @click="handleRemoveMember(scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
<!--          </el-card>-->
        </el-tab-pane>
        <el-tab-pane label="活动信息">
          <h1>活动管理</h1>
          <el-button class="new_btn" type="primary" @click="handleNew">新增</el-button>
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
    </div>
    <!--    <div class="person_body_right">-->

    <!--    </div>-->
  </el-row>

</template>

<script lang="ts" setup>
import {onMounted, reactive, ref, toRefs} from "vue";
import {Edit, Upload} from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox} from "element-plus";
import Dialog from "@/views/modules/AdminClub/dialog.vue";
import DialogActivity from "@/views/modules/Club/dialogActivity.vue";
import {clubRegister, getSelfClubsAsPresident} from "@/api/backend-api/commonUserController";
import {
  activityAlterInfo, activityDelete,
  activityRelease,
  clubAlterInfo,
  getActivities,
  getUsers, userKick
} from "@/api/backend-api/presidentUserController";


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
    ElMessage.error("更新失败")
    console.log(e);
  })
}

const dialogShow = ref(false) // 新增/编辑弹框
const detailShow = ref(false)// 详情弹窗
const rowInfo = ref({}) // 新增/编辑的数据
const title = ref("") // 是新建还是修改
const activityInfo = ref<API.Activity[]>([
  {
    name: "111",
    title: "1233",
    address: "432",
    beginTime: "4433",
    endTime: "jidowq",
    money: 12
  },
  {
    name: "131231",
    title: "123dqds3",
    address: "43qsd2",
    beginTime: "4433",
    endTime: "jidoeqwewq",
    money: 11
  }
])

const clubMemberInfo = ref<API.User[]>([]);
//todo 获取所有活动信息
const activitys = ref([
  {
    id: 1,
    name: "卖书",
    info: "这是一个卖书活动的信息",
    title: "义卖",
    beginTime: "2024-01-01 19:00",
    endTime: "2024-01-07 00:00",
    address: "操场",
    sign: "网站报名",
    joinPeople: "100"
  },
  {
    id: 2,
    name: "健康中国春播行动全国项目",
    info: "该项目以基层为重点，在全国范围内开展形式多样的中医诊疗技术专项培训、健康科普宣传、专家巡诊、免费中医智能体质辨识、慢病筛查等活动，提升基层医务人员健康管理技能，加强基层的健康管理水平和中医药诊疗的标准化水平，提高基层群众对医疗卫生服务的满意度和获得感。",
    title: "志愿者",
    beginTime: "2024-03-14",
    endTime: "2026-12-31",
    address: "国英园-1号楼",
    sign: "网站报名",
    joinPeople: "100"
  },
  {
    id: 3,
    name: "中国公益万里行志愿者",
    info: "1.服务内容与职责：积极传播公益理念，普及公益常识，运用互联网技术帮助慈善组织、公益项目、公益人做好公益理念传播，助力其发展，为国家共同富裕发展和现代化治理体系完善作出公益行业应有贡献。同时让中国公益慈善事业的发展状况、中国的公益文化、现代公益理念更好向社会和世界展示，唤醒公众的公益意识，强化网络文明建设。 2.活动介绍：严格审核、规范制作新媒体产品，利用新媒体平台和账号作为网络传播载体。同时赋能受益对象在新媒体宣传和新媒体方面的技能服务和培训。 民政部门备案号： 53330000A933770284A20137 3.招募对象： ①新媒体从业类：专业自媒体人、业余自媒体人 ②在校学生类：本专科新传类、摄影类专业（拍摄工具不限)大学生，或非专业具备一定同技能的大学生/研究生。 ③社会人士类：可熟练使用相机、手机的拍摄/视频制作软件的各行业人员。 4.活动流程： ①创作：志愿者参与本地线下公益活动的拍摄、公益活动视频剪辑，基金会负责线上新媒体推广的方式开展活动。 ②审核：由河北省新益公益基金等公益机构老师进行内容审核（包含意识形态)。 ③发布：通过中国公益万里行机构官宣平台进行项目的展示发布。 5.活动由公益机构开具志愿服务证明，记录时长。优秀贡献志愿者授予荣誉证书。 6.活动报名：请直接关注微信号:hdzc2020中国公益万里行参与活动（关注后会有引导信息，请详细阅读)。",
    title: "志愿者",
    beginTime: "2024-03-01",
    endTime: "2024-12-31",
    address: "河北省邯郸市丛台区人民东路312号",
    sign: "网站报名",
    joinPeople: "100"
  },
  {
    id: 4,
    name: "珍爱湿地 守护未来 推进湿地保护全球行动",
    info: "珍爱湿地 守护未来 推进湿地保护全球行动 ---共同珍爱湿地、共创美好湿地、共谋湿地保护 古往今来，人类逐水而居，文明伴水而生，人类生产生活同湿地有着密切联系。共同珍爱湿地、共创美好湿地、共谋湿地保护，具有十分重要的意义。深化认识、加强合作，共同推进湿地保护全球行动。 大力宣传湿地的生态功能和保护修复湿地的重要性，积极倡导“共同珍爱湿地 共创美好湿地 共谋湿地保护”的社会风尚。每年的2月2日是世界湿地日。这是湿地国际联盟组织于1996年3月确定的。从1997年开始，世界各国在这一天都举行不同形式的活动来宣传保护自然资源和生态环境。湿地与森林、海洋并称为地球三大生态系统，具有涵养水源、调节气候、改善环境、维护生物多样性等多种生态功能，被形容为“地球之肾”。 招募新青年志愿者：线上文案活动内容策划、活动招募、推广等志愿服务。新媒体传播，拍摄保护区自然风光、动植物、课程活动和重大事件等影像资料及新媒体运营、视频剪辑、制作等志愿服务。协助参与保护区开展生物多样性调查及保护志愿服务。 道阻且长，行则将至；行而不辍，未来可期。让我们共同努力，谱写全球湿地保护新篇章。",
    title: "志愿者",
    beginTime: "2024-02-28",
    endTime: "2026-12-31",
    address: "中国黄海湿地博物馆",
    sign: "网站报名",
    joinPeople: "100"
  }
])
//todo 获取当前社长所在的俱乐部
const clubs = ref<API.Club[]>([
  {
    id: 1,
    name: "fex",
    avatar: undefined,
    info: undefined,
    address: undefined,
    contact: undefined,
    member: undefined,
    activityNumber: undefined,
    money: undefined,
    presidentId: undefined,
    createTime: undefined,
    updateTime: undefined
  },
  {
    id: 2,
    name: "maple",
    avatar: undefined,
    info: undefined,
    address: undefined,
    contact: undefined,
    member: undefined,
    activityNumber: undefined,
    money: undefined,
    presidentId: undefined,
    createTime: undefined,
    updateTime: undefined
  },
])


const handleNew = () => {
  title.value = "新增";
  rowInfo.value = {};
  dialogShow.value = true;
}
const handleDetail = (val: any) => {
  console.log(val);
  // data.detailShow = true;
  // data.rowInfo = val;
  // todo 发送审核通过请求
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
      // todo 发送请求删除社团
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
    updateClubToTalInfo(''+clubInfo.value.id)
  }).catch(e => {
    ElMessage.error(`提交失败 ${e.message}`)
  })
}
// 关闭详情弹窗
const closeDetail = () => {
  detailShow.value = false;
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
}

const updateClubToTalInfo = async (clubId: string) => {
  getActivities({
    id: clubId,
  }).then(({data}) => {
    activityInfo.value = data.data;
  }).catch(e=>{
    console.log(e);
    ElMessage.error("活动列表获取失败");
  })
  await getUsers({
    id: clubId,
  }).then(({data}) => {
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

const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

onMounted(() => {
  getSelfClubsAsPresident().then(({data}) => {
    clubs.value = data.data;
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