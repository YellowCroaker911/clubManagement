<template>
  <div class="box">
    <h1>活动管理</h1>
<!--    <el-button class="new_btn" type="primary" @click="handleNew">新增</el-button>-->
    <el-table :data="data.activityInfo" border style="width: 100%; margin-top: 10px">
      <el-table-column prop="id" label="活动id"/>
      <el-table-column prop="name" label="活动名称"/>
      <el-table-column prop="title" label="活动主题"/>
      <el-table-column prop="address" label="活动地址"/>
      <el-table-column prop="sign" label="联系方式"/>
      <el-table-column prop="clubId" label="举办社团"/>
      <el-table-column prop="beginTime" label="开始时间"/>
      <el-table-column prop="endTime" label="结束时间"/>
      <el-table-column prop="money" label="报名费用"/>
      <el-table-column fixed="right" label="操作" width="220">
        <template #default="scope">
<!--          <el-button type="text" size="small" @click="handleEdit(scope.row)">编辑</el-button>-->
          <!--            <el-button type="text" size="small" v-if="scope.row.is_admitted === 0" @click="handleDetail(scope.row)">通过</el-button>-->
          <el-button type="text" size="small" @click="handleDel(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref, toRefs} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {clubRegister} from "@/api/backend-api/commonUserController";
import {activityDelete} from "@/api/backend-api/presidentUserController";
import {getAllActivity} from "@/api/backend-api/globalQueryController";
import {fen2Yuan} from "@/utils";

const data = reactive({
  dialogShow: false, // 新增/编辑弹框
  detailShow: false, // 详情弹窗
  rowInfo: {}, // 新增/编辑的数据
  title: "", // 是新建还是修改
  activityInfo: []
});
const handleNew = () => {
  data.title = "新增";
  data.rowInfo = {};
  data.dialogShow = true;
}
const handleDetail = (val: any) => {
  console.log(val);
  // data.detailShow = true;
  // data.rowInfo = val;
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
      updateTable()
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
  clubRegister({
    ...val
  }).then((res) => {
    ElMessage.success('提交成功');
  }).catch(e => {
    ElMessage.error(`提交失败, ${e.message}`)
  })
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
const closeDetail =() => {
  data.detailShow = false;
}
const updateTable = () => {
  getAllActivity().then((res) => {
    for(let i in res.data.data||[]){
      res.data.data[i].money = fen2Yuan(res.data.data[i].money);
    }
    data.activityInfo = res.data.data;
  }).catch(e => {
    ElMessage.error("查询活动失败");
    console.log(e);
  })
}
onMounted(() => {
  updateTable();
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
