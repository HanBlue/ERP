<template>
  <v-form v-model="valid" ref="myUserForm">
    <v-text-field v-model="role.role_name" label="请输入角色名称" required :rules="nameRules"/>
    <v-text-field v-model="role.role_key" label="请选择权限字符" />
    <v-text-field v-model="role.role_sort" label="请输入权限顺位" />
    <v-layout class="my-4" row>
      <v-spacer/>
      <v-btn @click="submit" color="primary">提交</v-btn>
      <v-btn @click="clear">重置</v-btn>
    </v-layout>
  </v-form>
</template>

<script>
  export default {
    name: "role-form",
    props: {
      oldRole: {
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
        role: {
          role_name: '', // 用户账号
          role_key: '', // 用户角色
          role_sort: '',// 手机号码
        },
        nameRules: [
          v => !!v || "角色名字不能为空",
          v => v.length > 1 || "至少2位"
        ],
      }
    },
    methods: {
      submit() {
        // 表单校验
        if (this.$refs.myRoleForm.validate()) {
          this.$http({
            headers: {
              dataType: 'json',
              contentType: 'application/json',
            },
            method: 'put' ,
            url: '/role',
            data: this.$qs.stringify(this.role)
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
        this.$refs.myRoleForm.reset();
        // 需要手动清空商品分类
      }
    },
    watch: {
      oldRole: {// 监控oldUser的变化
        handler(val) {
          if (val) {
            // 注意不要直接复制，否则这边的修改会影响到父组件的数据，copy属性即可
            this.role = Object.deepCopy(val)
          } else {
            // 为空，初始化brand
            this.role = {
              role_id:'',
              role_name: '',
              role_key: '',
              role_sort: '',
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
