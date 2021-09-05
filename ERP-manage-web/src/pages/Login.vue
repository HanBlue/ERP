<template>
  <div class="login-container">
    <tr>
      <div class="hed">
        <td><a class="tool" href="https://www.hanblue.top" style="text-decoration-line: none" >Starter</a></td>
        <td><a class="tool3" href="https://blog.hanblue.top/2021/0715undefined.html" target="_blank" style="text-decoration-line: none" >Learn</a></td>
        <td><a class="tool3" href="https://blog.hanblue.top/2021/0728undefined.html" target="_blank" style="text-decoration-line: none" >View</a></td>
        <td><a class="tool3" href="https://blog.hanblue.top/2021/0812undefined.html" target="_blank" style="text-decoration-line: none" >Deploy</a></td>
        <td><a class="tool2" href="https://blog.hanblue.top/" target="_blank" style="text-decoration-line: none" >HANBlUE</a></td>
        <td><a class="tool4" href="https://blog.hanblue.top/archives/" target="_blank" style="text-decoration-line: none" >archives</a></td>
        <td><a class="tool3" href="https://blog.hanblue.top/about/" target="_blank" style="text-decoration-line: none" >about</a></td>
      </div>
    </tr>

    <div>
    <td >
    <div class="h1" >
      <v-responsive >
       <tr><t class="t2">ERP 企业资源计划</t></tr>
        <tr><t class="t2">订单管理系统</t></tr>
        <div class="line-left-right"></div>
        <div class="h2">
        <tr><p class="t1"> Due to the need for data processing and data confidentiality of orders,
          today's e-commerce merchants mostly use their own development or purchase of finished products.</p> </tr>
        </div>
        <div class="line-left-right1"></div>

        <v-btn elevation="2"  large  plain  @click="flag=!flag" class="bt" >Login  Now</v-btn>

      </v-responsive>
    </div>
    </td>

    <td>
      <transition name="slide-fade">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="60px" class="login-form" v-show="!flag" label-position="center" >
      <h2 class="t3" >ERP 订单管理系统</h2>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane   label="登录" name="first" >
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="60px" class="demo-ruleForm">
          <el-form-item label="账号"  prop="username"><el-input v-model="ruleForm.username" placeholder="root" ></el-input></el-form-item>
          <el-form-item label="密码" prop="password"><el-input type="password" v-model="ruleForm.password" auto-complete="off" placeholder="55555"></el-input></el-form-item>

          <el-form-item>
            <el-button type="primary" plain @click="submitForm('ruleForm')" align="center">登录<i class="el-icon-s-promotion el-icon--right"></i></el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="注册" name="second">
        <register></register>
      </el-tab-pane>
    </el-tabs>
    </el-form>
      </transition>
    </td>


    <span>
      <tr>
        <p class="boot">1 — 1</p>
      </tr>
      </span>
    <br>
    <div>

    <span >
      <p class= "tb" style="color:lightgray;" align="center" >
        Copyright © 2021 HAN蓝海
      </p>
    </span>

    <span >
      <p align="center">
      <a href="http://www.beian.miit.gov.cn/" class="tb" style="color:lightgray ; text-decoration-line: none; " target="_blank" >皖ICP备2021011752号-1</a>
      </p>
    </span>
  </div>
  </div>
  </div>
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
        flag: true,
        isShow: true,
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
  .slide-fade-enter-active {
    transition: all .8s cubic-bezier(0.5, 1, 0.5, 0.7);
  }
  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }
  .slide-fade-enter, .slide-fade-leave-to
    /* .slide-fade-leave-active 用于 2.1.8 以下版本 */ {
    transform: translateX(10px);
    opacity: 0;
  }
  .login {
    width: 400px;
    margin: 0 auto;
  }

  .el-tabsitem {
    text-align: center;
    width: 60px;
  }

  .login-container {
        position: absolute;
         width: 100%;
         height: auto;
        background: url("../assets/0.jpg");
        background-size: contain;
    }
  .login-form {
    width: 400px;
    height: 390px;
    margin: 0px 0px 0px 0px; /* 上下间距160px，左右自动居中*/
    background-color: rgb(255, 255, 255, 0.7); /* 透明背景色 */
    padding: 40px;
    border-radius: 20px; /* 圆角 */
  }
  .login-title {
    text-align: center;
    color: rgb(0,0,0);
  }
 .t1{
  text-align: left;
   color: rgb(255,255,255);
   font-size: 18px;
   font-family: english1;
  }
  .t2{
    text-align: left;
    color: rgb(255,255,255);
    font-size: 45px;
    font-family: tes2;
    line-height: 1.5em;
    white-space: nowrap;
  }
  .t3{
    text-align: center;
    color: rgb(0,0,0,0.6);
    font-size: 25px;
    font-family: tes2;
    margin: 0px 0px 20px 0px;

  }
  .line-left-right {
    padding:  0px 0px;
    margin: 30px 0px;
    line-height: 1px;
    border-left: 80px solid rgb(221, 221, 221);
    border-right: 0px solid rgb(221, 221, 221);
    text-align: center;
    height: 2px;
  }
  .line-left-right1 {
    padding:  0px 0px;
    margin: 30px 0px;
    line-height: 1px;
    border-left: 90px solid rgb(221, 221, 221);
    border-right: 0px solid rgb(221, 221, 221);
    text-align: center;
    height: 0px;
  }
.h1{
  margin: 90px 280px 0px 220px;
  width: 500px;
  height: 430px;
}
  .bt{
    margin: 0px 0px 0px 0px;
  }
  .h2{
    line-height: 2.8em;
  }
  .hed{
    margin: 25px 0px;
  }
  .tool{
    margin: 0px 0px 0px 195px;
    font-size: 20px;
    color: rgb(255,255,255);
    font-family: english;
  }
  .tool3{
    margin: 0px 0px 0px 55px;
    font-size: 20px;
    color: rgb(255,255,255);
    font-family: english;
  }
  .tool2{
    margin: 0px 300px 0px 110px;
    font-size: 35px;
    color: rgb(255,255,255);
    font-family: english2;
  }
  .tool4{
    margin: 0px 0px 0px 0px;
    font-size: 20px;
    color: rgb(255,255,255);
    font-family: english;
  }
  .boot{
    margin: 100px 10px 10px 200px;
    font-size: 14px;
    color: rgb(255,255,255);
    font-family: english;
  }
  .tb{
    font-size: 15px;
  }

 </style>
