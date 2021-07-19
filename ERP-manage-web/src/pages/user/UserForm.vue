<template>
  <v-form v-model="valid" ref="myUserForm">
    <v-text-field v-model="user.username" label="请输入用户账号" required :rules="nameRules"/>
    <v-text-field v-model="user.nickname" label="请选择用户角色" />
    <v-text-field v-model="user.nickname" label="请输入用户手机号" />
    <v-layout class="my-4" row>
      <v-spacer/>
      <v-btn @click="submit" color="primary">提交</v-btn>
      <v-btn @click="clear">重置</v-btn>
    </v-layout>
  </v-form>
</template>

<script>
  export default {
    name: "user-form",
    props: {
      oldUser: {
        type: Object
      },
      isEdit: {
        type: Boolean,
      default: false
      }
    },
    data() {
      return {
        valid: false, // 表单校验结果标记
        user: {
          username: '', // 用户账号
          nickname: '', // 用户角色
          phone: '',// 手机号码
        },
        nameRules: [
          v => !!v || "账号不能为空",
          v => v.length > 1 || "账号至少2位"
        ],
      }
    },
      methods: {
        submit() {
          // 表单校验
          if (this.$refs.myUserForm.validate()) {
            // 定义一个请求参数对象，通过解构表达式来获取brand中的属性
            const {username, nickname, ...params} = this.user;
            // 数据库中只要保存分类的id即可，因此我们对categories的值进行处理,只保留id，并转为字符串
            //params.ids = categories.map(c => c.id).join(",");
            // 将字母都处理为大写
            //params.letter = letter.toUpperCase();
            // 将数据提交到后台
            // this.$http.post('/item/brand', this.$qs.stringify(params))
            this.$http({
              headers: {
                dataType: 'json',
                contentType: 'application/json',
              },
              method: 'put' ,
              url: '/user',
              data: this.$qs.stringify(params)
            }).then(() => {
              // 关闭窗口
              this.$emit("close");
              this.$message.success("保存成功！");
            })
              .catch(() => {
                this.$message.error("保存失败！");
              });
          }
        },
        clear() {
          // 重置表单
          this.$refs.myUserForm.reset();
          // 需要手动清空商品分类
        }
      },
      watch: {
        oldUser: {// 监控oldUser的变化
          handler(val) {
            if (val) {
              // 注意不要直接复制，否则这边的修改会影响到父组件的数据，copy属性即可
              this.user = Object.deepCopy(val)
            } else {
              // 为空，初始化brand
              this.user = {
                username: '',
                nickname: '',
                phone: '',
              }
            }
          },
          deep: true
        }
      }
    }

</script>

<style scoped>

</style>
