<template>
  <div class="box">
    <h1>社团管理</h1>
<!--    <el-button class="new_btn" type="primary" @click="handleNew">新增</el-button>-->
    <el-table :data="clubInfo" border style="width: 100%; margin-top: 30px">
      <el-table-column prop="id" label="社团id" />
      <el-table-column prop="name" label="社团名" />
      <el-table-column prop="avatar" label="头像" >
        <template #default="scope">
          <el-avatar :src="scope.row.avatar" size="large" alt="上传头像" />
        </template>
      </el-table-column>
      <el-table-column prop="info" label="信息" />
      <el-table-column prop="address" label="地址" />
      <el-table-column prop="contact" label="联系方式" />
      <el-table-column prop="money" label="经费" />
      <el-table-column prop="president_name" label="社长名称" />
      <el-table-column prop="is_admitted" label="社团状态" >
        <template #default="scope">
          <el-tag
              :type="scope.row.isAdmitted === 0 ? 'warning' : 'success'"
              disable-transitions
          >{{ scope.row.isAdmitted === 0 ? "审核中" : "正常"}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="220">
        <template #default="scope">
          <el-button type="text" size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="text" size="small" v-if="scope.row.isAdmitted === 0" @click="handleDetail(scope.row)">通过</el-button>
          <el-button type="text" size="small" @click="handleDel(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新建/编辑弹框 -->
    <Dialog
        v-if="dialogShow"
        v-model:dialogShow="dialogShow"
        :rowInfo="rowInfo"
        :title="title"
        :arrayNum="clubInfo.length"
        @update="editRow"
    />
    <!-- 详情弹窗 -->
<!--    <Detail v-if="detailShow" :rowInfo="rowInfo" @closeDetail="closeDetail" />-->
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref, toRefs} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import Dialog from "./dialog.vue";
import {clubRegister} from "@/api/backend-api/commonUserController";
import {getAllActivity, getAllClub, getClubAvatar} from "@/api/backend-api/globalQueryController";
import {clubAdmit, clubDelete} from "@/api/backend-api/adminUserController";

const dialogShow = ref(false) // 新增/编辑弹框
const detailShow = ref(false) // 详情弹窗
const rowInfo = ref({}) // 新增/编辑的数据
const title = ref("") // 是新建还是修改
const clubInfo = ref<API.Club[]>([])


const handleNew = () => {
  title.value = "新增";
  rowInfo.value = {};
  dialogShow.value = true;
}
const handleDetail = (val: any) => {
  console.log(val);
  // data.detailShow = true;
  // data.rowInfo = val;
  clubAdmit({id: val.id}).then(({data}) => {
    updateClubsInfo();
  }).catch(e => {
    console.log(e);
    ElMessage("通过失败");
  })
}
const handleEdit = (val: any) => {
  ElMessage.error("不支持管理员编辑社团信息")
  return;
  // console.log(val);
  // title.value = "修改";
  // dialogShow.value = true;
  // rowInfo.value = val;
}
const handleDel = (val: any) => {
  console.log(val);
  ElMessageBox.confirm("你确定删除这个社团吗?", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
  .then(() => {
    clubDelete({id: val.id}).then(({data}) => {
      ElMessage.success("删除成功");
      updateClubsInfo();
    }).catch(e => {
      console.log(e);
      ElMessage.error("删除失败");
    })
  })
  .catch(() => {
    // catch error
  });
}
// 添加行
const addRow = (val: any) => {
  console.log("val", val);
  ElMessage.error("此页面不支持添加");
  // clubRegister({
  //   ...val
  // }).then((res) => {
  //   ElMessage.success('提交成功');
  // }).catch(e => {
  //   ElMessage.error(`提交失败, ${e.message}`)
  // })
  // data.studentInfo.push(val);
}
// 编辑行
const editRow = (val: any) => {

  // let index = data.studentInfo.findIndex(
  //     (item, index) => item.id === val.id
  // );
  // data.studentInfo.splice(index, 1, val);
}
// 关闭详情弹窗
const closeDetail = () => {
  detailShow.value = false;
}
const updateClubsInfo = () => {
  getAllClub().then(({data}) => {
    clubInfo.value = data.data;
  }).then(() => {
    clubInfo.value.forEach(item => {
      const it = item;
      getClubAvatar({id: it.id}).then(({data}) => {
        it.avatar = 'data:image/png;base64,' + data.data;
      })
    })
  })
}
onMounted(() => {
  updateClubsInfo();
})
</script>

<style lang="scss" scoped>
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
