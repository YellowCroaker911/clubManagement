<template>
  <div class="box">
    <h1>社团管理</h1>
    <el-button class="new_btn" type="primary" @click="handleNew">新增</el-button>
    <el-table :data="clubInfo" border style="width: 100%">
      <el-table-column prop="id" label="社团id" />
      <el-table-column prop="name" label="社团名" />
      <el-table-column prop="avatar" label="头像" />  <!-- todo 图片 -->
      <el-table-column prop="info" label="信息" />
      <el-table-column prop="address" label="地址" />
      <el-table-column prop="contact" label="联系方式" />
      <el-table-column prop="money" label="经费" />
      <el-table-column prop="president_name" label="社长名称" />
      <el-table-column prop="is_admitted" label="社团状态" >
        <template #default="scope">
          <el-tag
              :type="scope.row.is_admitted === 0 ? 'warning' : 'success'"
              disable-transitions
          >{{ scope.row.is_admitted === 0 ? "审核中" : "正常"}}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="220">
        <template #default="scope">
          <el-button type="text" size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="text" size="small" v-if="scope.row.is_admitted === 0" @click="handleDetail(scope.row)">通过</el-button>
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
        @add="addRow"
        @update="editRow"
    />
    <!-- 详情弹窗 -->
<!--    <Detail v-if="detailShow" :rowInfo="rowInfo" @closeDetail="closeDetail" />-->
  </div>
</template>

<script lang="ts">
import { reactive, ref, toRefs } from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import Dialog from "./dialog.vue";
import {register1} from "@/api/backend-api/clubRegisterController";



export default {
  components: { Dialog },
  setup() {
    const data = reactive({
      dialogShow: false, // 新增/编辑弹框
      detailShow: false, // 详情弹窗
      rowInfo: {}, // 新增/编辑的数据
      title: "", // 是新建还是修改
      clubInfo: [
        {
          id: 13,
          name: "111",
          avatar: "123",
          info: "1233",
          address: "432",
          contact: "32132",
          member: "4433",
          money: "jidowq",
          president_name: "xuxu",
          is_admitted: 1
        },
        {
          id: 22,
          name: "131231",
          avatar: "dqwd23",
          info: "123dqds3",
          address: "43qsd2",
          contact: "32qwe132",
          member: "4433",
          money: "jidoeqwewq",
          president_name: "xuxu",
          is_admitted: 0
        }
      ]
    });
    const method = reactive({
      handleNew() {
        data.title = "新增";
        data.rowInfo = {};
        data.dialogShow = true;
      },
      handleDetail(val: any) {
        console.log(val);
        // data.detailShow = true;
        // data.rowInfo = val;
        // todo 发送审核通过请求
      },
      handleEdit(val: any) {
        console.log(val);
        data.title = "修改";
        data.dialogShow = true;
        data.rowInfo = val;
      },
      handleDel(val: any) {
        console.log(val);
        ElMessageBox.confirm("你确定删除这个社团吗?", "提示", {
          confirmButtonText: "确认",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(() => {
          // todo 发送请求删除社团
        })
        .catch(() => {
          // catch error
        });
      },
      // 添加行
      addRow(val: any) {
        console.log("val", val);
        // ElMessage.error("此页面不支持添加");
        register1({
          ...val
        }).then((res) => {
          ElMessage.success('提交成功');
        }).catch(e => {
          ElMessage.error(`提交失败, ${e.message}`)
        })
        // data.studentInfo.push(val);
      },
      // 编辑行
      editRow(val: any) {
        // todo 发送更新请求

        // let index = data.studentInfo.findIndex(
        //     (item, index) => item.id === val.id
        // );
        // data.studentInfo.splice(index, 1, val);
      },
      // 关闭详情弹窗
      closeDetail() {
        data.detailShow = false;
      },
    });
    return { ...toRefs(data), ...method };
  },
};
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
