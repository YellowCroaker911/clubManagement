<template>
  <el-dialog :model-value="true" :title="title" @close="handleClose">
    <el-form
        ref="formRef"
        :model="formData"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
    >
      <el-form-item label="社团名称：" prop="name">
        <el-input v-model="formData.name"></el-input>
      </el-form-item>
      <el-form-item label="社长id：" prop="president_id">
        <el-input v-model="formData.presidentId"></el-input>
      </el-form-item>
<!--      <el-form-item label="年龄：" prop="age">-->
<!--        <el-input v-model="formData.age"></el-input>-->
<!--      </el-form-item>-->
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


const props = withDefaults(defineProps<{title:string, dialogShow:boolean, rowInfo: API.ClubRegisterRequestDTO | undefined}>(),{
  title: "",
  dialogShow: false,
  rowInfo: undefined,
})
const formRef = {};
const dialogFlag = ref();
const formData = reactive<API.ClubRegisterRequestDTO>({
  name: '',
  presidentId: ''
});

const rules = reactive<FormRules<API.ClubRegisterRequestDTO>>({
  name:[
    {required:true, message: '输入社团名称', trigger: 'blur'}
  ],
  presidentId:[
    {required:true, message: '输入社长id', trigger: 'blur'}
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
  formData.name = tmp.name;
  formData.presidentId = tmp.presidentId;
  dialogFlag.value = props.rowInfo;
});
</script>

<style lang="scss" scoped>
</style>
