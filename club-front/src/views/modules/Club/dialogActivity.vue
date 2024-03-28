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
        <el-date-picker
            v-model="formData.beginTime"
            type="datetime"
            placeholder="选择开始时间"
            format="YYYY-MM-DD HH:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="结束时间：" prop="endTime">
        <el-date-picker
            v-model="formData.endTime"
            type="datetime"
            placeholder="选择开始结束"
            format="YYYY-MM-DD HH:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="报名费用：" prop="money">
        <el-input v-model="formData.money"></el-input>
      </el-form-item>
      <el-form-item v-if="title === '修改'" label="总结：" prop="summary">
        <el-input v-model="formData.summary"></el-input>
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


const props = withDefaults(defineProps<{title:string, dialogShow:boolean, rowInfo: API.Activity | undefined}>(),{
  title: "",
  dialogShow: false,
  rowInfo: undefined,
})
const formRef = {};
const dialogFlag = ref();

const formData = ref<API.ActivityAlterInfoRequestDTO>({});

const rules = reactive<FormRules<API.ActivityAlterInfoRequestDTO>>({
  name:[
    {required:true, message: '输入活动名称', trigger: 'blur'},
    {pattern: /^[a-zA-Z0-9\u4e00-\u9fff]{1,20}$/, message: '活动名只能包含中英文字符和数字，不能超过20位', trigger: 'blur'}
  ],
  info:[
    {required:true, message: '输入活动信息', trigger: 'blur'},
    {pattern: /^.{0,500}$/, message: '活动信息不能超过500个字符', trigger: 'blur'}
  ],
  title:[
    {required:true, message: '输入活动主题', trigger: 'blur'},
    {pattern: /^.{0,50}$/, message: '活动主题不能超过50个字符', trigger: 'blur'}
  ],
  address:[
    {required:true, message: '输入活动地址', trigger: 'blur'},
    {pattern: /^.{0,10}$/, message: '活动地点不能超过100个字符', trigger: 'blur'}
  ],
  sign:[
    {required:true, message: '输入邮箱联系方式', trigger: 'blur'},
    {pattern: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, message: '联系邮箱号码不符规范', trigger: 'blur'}
  ],
  beginTime:[
    {required:true, message: '输入开始时间', trigger: 'blur'}
  ],
  endTime:[
    {required:true, message: '输入结束时间', trigger: 'blur'}
  ],
  money:[
    {required:true, message: '输入报名费用', trigger: 'blur'},
    {pattern: /^(([1-9]{1}\d*)|(0{1}))(\.\d{1,2})?$/, message: '两位小数，不得超过1亿元', trigger: 'blur'}
  ],
  summary:[
    {pattern: /^.{0,500}$/, message: '活动总结不能超过500个字符', trigger: 'blur'}
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
        emit("update", formData.value);
      }
      else {
        console.log("add", props.rowInfo);
        emit("add", formData.value);
      }
      console.log('submit!')
    } else {
      console.log('error submit!', fields)
    }
  })
}
onMounted(() => {
  const tmp = Object.assign({}, props.rowInfo);
  formData.value = {...tmp};
  dialogFlag.value = props.rowInfo;
});
</script>

<style lang="scss" scoped>
</style>
