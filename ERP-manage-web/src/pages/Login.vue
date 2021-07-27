<template>
  <v-app>
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4 >
            <v-card class="elevation-12" >
              <v-toolbar dark color="primary">
                <v-toolbar-title >ERP订单管理系统</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
  <p class="login">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="登录" name="first">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="名称" prop="username" ><el-input v-model="ruleForm.username" placeholder="root" ></el-input></el-form-item>
          <el-form-item label="密码" prop="password"><el-input type="password" v-model="ruleForm.password" auto-complete="off"placeholder="55555"></el-input></el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>

            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>

      <el-tab-pane label="注册" name="second">
        <register></register>
      </el-tab-pane>
    </el-tabs>
  </p>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
      </v-content>
    </v-app>
</template>

<script>
  import register from '../pages/register';
  import qs from 'qs'
  export default {
    data() {
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

      return {
        activeName: 'first',
        ruleForm: {
          username: '',
          password: '',
          //checkPass: ''
        },
        rules: {
          name: [{ required: true, message: '请输入您的名称', trigger: 'blur' }, { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }],
          pass: [{ required: true, validator: validatePass, trigger: 'blur' }]
        }
      };
    },
    methods: {
      //选项卡切换
      handleClick(tab, event) {
      },
      //重置表单
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      //提交表单
      submitForm(formName) {
        this.$refs[formName].validate(valid => {
            if (valid) {
              this.$http.post("/auth/accredit", qs.stringify(this.ruleForm)
              )
                .then(resp => {
                    if (resp.status === 200) {

                      this.$message({
                        type: 'success',
                        message: '登录成功',
                      });
                      this.$router.push('/layout');
                    }
                  }
                )
                .catch( (error) =>{
                  if (error.response.status === 401) {
                    this.$message({
                    type: 'error',
                    message: '登录失败,用户名或密码错误'
                  })
                }
                })
            }
          }
        )
      },
    },
      components: {
        register
      }
    }
</script>

<style lang="scss">
  .login {
    width: 400px;
    margin: 0 auto;
  }

  .el-tabsitem {
    text-align: center;
    width: 60px;
  }

  .container{
    background-repeat: repeat-y;
    background-size: 105%;
    background-image: url("../assets/0.jpg");
  }

 </style>
