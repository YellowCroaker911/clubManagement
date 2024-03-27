<template>
  <h1>活动列表</h1>
  <div class="activities-class">
  <template v-for="(item, index) in list" :key="index">
  <!--    <el-card v-for="item in list" :body-style="{ padding: '0px', marginBottom: '1px' }">-->
    <el-descriptions class="activity-item-class" :title="item.name" border type="primary">
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="活动名称: ">{{ item.name }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="主题: ">{{ item.title }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="报名方式: ">{{ item.sign }}</el-descriptions-item>
<!--      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="详细信息: ">{{ item.info }}</el-descriptions-item>-->
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="活动地点: ">{{ item.address }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="活动时间: ">{{ item.beginTime }} 至 {{ item.endTime }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="3" label="已报名人数: ">{{ item.joinPeople }}</el-descriptions-item>
      <el-descriptions-item class-name="description-class" label-class-name="label-class" span="1" label="其他: ">
        <el-button type="primary" @click="() => {handleDetailActivity(item.id)}"> 活动详情 </el-button>
      </el-descriptions-item>

    </el-descriptions>
<!--      <img-->
<!--          src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"-->
<!--          class="image"-->
<!--      />-->
<!--      <div style="padding: 14px">-->
<!--        <span>Delicious hamburger</span>-->
<!--        <div class="bottom card-header">-->
<!--          <div class="time">{{ currentDate }}</div>-->
<!--          <el-button text class="button">operation button</el-button>-->
<!--        </div>-->
<!--      </div>-->
<!--    </el-card>-->
  </template>
  </div>
</template>

<script setup lang="ts">
import { getUUID } from '@/utils'
import {ElMessage, ElMessageBox} from 'element-plus'
import { ref, reactive, getCurrentInstance, } from "vue";
import router from "@/router";
// import {getToken} from "@/api/backend-api/loginController";
// import {register} from "@/api/backend-api/registerController";
const { proxy } = getCurrentInstance()


const loading = ref(false)
const currentDate = new Date().toDateString()
const list = ref([
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

const handleDetailActivity = (index: number) => {
  router.push({path: `/activity/${index}`})
}


function signUp(index: number) {
  ElMessageBox.confirm(`确定要报名活动 ${list.value[index].name}`)
  .then(() => {
  // todo 提交活动报名
    console.log("报名成功")
  })
  .catch(() => {
    console.log("报名取消")
  })
}

</script>

<style>

</style>
