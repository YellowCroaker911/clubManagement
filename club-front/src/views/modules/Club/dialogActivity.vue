<template>
  <el-dialog :model-value="true" :title="title" @close="handleClose">
    <el-form
        ref="formRef"
        :model="formData"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
    >
      <el-form-item label="活动名称：" prop="name">
        <el-input v-model="formData.name"></el-input>
      </el-form-item>
      <el-form-item label="活动主题：" prop="title">
        <el-input v-model="formData.title"></el-input>
      </el-form-item>
      <el-form-item label="活动信息：" prop="info">
        <el-input v-model="formData.info"></el-input>
      </el-form-item>
      <el-form-item label="活动地址：" prop="address">
        <el-input v-model="formData.address"></el-input>
      </el-form-item>
      <el-form-item label="联系方式：" prop="sign">
        <el-input v-model="formData.sign"></el-input>
      </el-form-item>
      <el-form-item label="开始时间：" prop="beginTime">
        <el-input v-model="formData.beginTime"></el-input>
      </el-form-item>
      <el-form-item label="结束时间：" prop="endTime">
        <el-input v-model="formData.endTime"></el-input>
      </el-form-item>
      <el-form-item label="报名费用：" prop="money">
        <el-input v-model="formData.money"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(formRef)">确定</el-button>
        <el-button @click="resetForm(formRef)">重置</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script lang="ts" setup>
import {PropType, reactive, onMounted, ref} from "vue"
import {FormInstance, FormRules} from "element-plus";
const emit = defineEmits(["update:dialogShow", "update", "add"])


const props = withDefaults(defineProps<{title:string, dialogShow:boolean, rowInfo: API.ActivityReleaseRequestDTO | undefined}>(),{
  title: "",
  dialogShow: false,
  rowInfo: undefined,
})
const formRef = {};
const dialogFlag = ref();

const formData = reactive<API.ActivityReleaseRequestDTO>({
  clubId: '',
  name: '',
  info: '',
  title: '',
  beginTime: '',
  endTime: '',
  address: '',
  sign: '',
  money: '',
});

const rules = reactive<FormRules<API.ActivityReleaseRequestDTO>>({
  name:[
    {required:true, message: '输入活动名称', trigger: 'blur'}
  ],
  info:[
    {required:true, message: '输入活动信息', trigger: 'blur'}
  ],
  title:[
    {required:true, message: '输入活动主题', trigger: 'blur'}
  ],
  address:[
    {required:true, message: '输入活动地址', trigger: 'blur'}
  ],
  sign:[
    {required:true, message: '输入联系方式', trigger: 'blur'}
  ],
  beginTime:[
    {required:true, message: '输入开始时间', trigger: 'blur'}
  ],
  endTime:[
    {required:true, message: '输入结束时间', trigger: 'blur'}
  ],
  money:[
    {required:true, message: '输入报名费用', trigger: 'blur'}
  ]
})
// 关闭弹窗
function handleClose() {
  emit("update:dialogShow", false);
}
// 重置
const resetForm = (formEl) => {
  if (!formEl) return
  formEl.resetFields()
}

// 提交表单内容
const submitForm = async (formEl: FormInstance | undefined) => {
  console.log(formEl);
  if (!formEl) return
  formEl.validate((valid, fields) => {
    console.log(props.rowInfo);
    if (valid) {
      if(props.rowInfo?.name) {
        console.log("upd");
        emit("update", formData);
      }
      else {
        console.log("add");
        emit("add", formData);
      }
      console.log('submit!')
    } else {
      console.log('error submit!', fields)
    }
  })
}
onMounted(() => {
  const tmp = Object.assign({}, props.rowInfo);
  formData.clubId = tmp.clubId;
  formData.name = tmp.name;
  formData.title = tmp.title;
  formData.info = tmp.info;
  formData.address = tmp.address;
  formData.sign = tmp.sign;
  formData.beginTime = tmp.beginTime;
  formData.endTime = tmp.endTime;
  formData.money = tmp.money;
  dialogFlag.value = props.rowInfo;
});
</script>

<style lang="scss" scoped>
</style>
