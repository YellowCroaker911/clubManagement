<template>
  <div class="box">
    <h1>活动管理</h1>
        <el-button class="new_btn" type="primary" @click="handleBackup">备份当前数据库</el-button>
    <el-table :data="backupInfo" border style="width: 100%; margin-top: 10px">
      <el-table-column prop="timeStamp" label="备份时间戳" width="300"/>
      <el-table-column prop="fileName" label="文件名称"/>
      <el-table-column fixed="right" label="操作" width="300">
        <template #default="scope">
          <el-button type="primary" size="small" @click="handleRecovery(scope.row)">恢复</el-button>
          <el-button type="primary" size="small" @click="handleDownload(scope.row)">点击下载</el-button>
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
import {backup, download, getAllBackUpFiles, recovery} from "@/api/backend-api/adminUserController";
import {downloadByUrl} from "@/api/httpRequest";
import {AxiosResponse} from "axios";

const backupInfo =  ref<API.FileDetailsVO[]>([]);
const handleRecovery = (row) => {
  recovery({
    path: row.filePath,
  }).then((res) => {
    ElMessage.success("恢复成功");
  }).catch(e => {
    ElMessage.error(`恢复失败，${e.message}`);
    console.log(e);
  })
}
const handleBackup = () => {
  backup().then((res) => {
    ElMessage.success('备份成功');
    updateTable();
  }).catch(e => {
    ElMessage.error(`备份失败失败, ${e.message}`)
  })
}
const handleDownload = (row) => {
  downloadByUrl(
      `/api/admin/download?path=${row.fileName}`,
  ).then((response: AxiosResponse<any>) => {
    // 提取文件名
    const fileName = row.fileName;
    console.log(fileName);
    // 将二进制流转为blob
    const blob = new Blob([response.data], { type: 'application/octet-stream' })
    // 创建新的URL并指向File对象或者Blob对象的地址
    const blobURL = window.URL.createObjectURL(blob)
    // 创建a标签，用于跳转至下载链接
    const tempLink = document.createElement('a')
    tempLink.style.display = 'none'
    tempLink.href = blobURL
    tempLink.setAttribute('download', decodeURI(fileName))
    // 兼容：某些浏览器不支持HTML5的download属性
    if (typeof tempLink.download === 'undefined') {
      tempLink.setAttribute('target', '_blank')
    }
    // 挂载a标签
    document.body.appendChild(tempLink)
    tempLink.click()
    document.body.removeChild(tempLink)
    // 释放blob URL地址
    window.URL.revokeObjectURL(blobURL)
  }).catch(e=> {
    ElMessage.error(`下载失败 ${e.message}`);
    console.log(e);
  })
}

const updateTable = () => {
  getAllBackUpFiles().then(({data}) => {
    for(let i in data.data || []) {
      let dt = new Date();
      dt.setTime(data.data[i].timeStamp);
      data.data[i].timeStamp = dt.toJSON().replace('T', ' ').replace('Z', '');
    }
    backupInfo.value = data.data || [];
  }).catch(e => {
    ElMessage.error(`查询备份信息失败 ${e.message}`);
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
