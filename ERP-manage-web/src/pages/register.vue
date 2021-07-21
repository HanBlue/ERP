<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="用户名" prop="username"><el-input v-model="ruleForm.username"></el-input></el-form-item>
    <el-form-item label="密码" prop="password"><el-input type="password" v-model="ruleForm.password" auto-complete="off"></el-input></el-form-item>
    <el-form-item label="确认密码" prop="checkPass"><el-input type="password" v-model="ruleForm.checkPass" auto-complete="off"></el-input></el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import qs from 'qs'
  export default {
    data() {
      var validate1= (rule,value,callback)=> {
        //return new Promise(resolve => {
          this.$http.get("/user/check/" + value)
            .then(
              resp => {if (resp.data === false){(
              //resolve({valid: resp.data, data: "已存在!"})
              callback(new Error('用户名已经存在'))
            )}
        }
        )
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };

      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };

      return {
        activeName: 'second',
        ruleForm: {
          username: '',
          password: '',
          checkPass: ''
        },
        rules: {
          username: [{ required: true, message: '请输入账号', trigger: 'blur' }, { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }],
          password: [{ required: true, validator: validatePass, trigger: 'blur' }],
          checkPass: [{ required: true, validator: validatePass2, trigger: 'blur' }]
        }
      };
    },

    methods: {
      submitForm(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            this.$http.post("/user/register", qs.stringify(this.ruleForm)
            )
              .then(resp => {
                  if (resp.status === 201) {
                    this.$router.go(0);
                    this.$message({
                      type: 'success',
                      message: '注册成功，请登录',
                    });
                  }
                }
              )
              .catch(() => this.msg = '注册失败，用户名已存在')
          }
          }
        )
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  };
</script>
