<template>
  <v-form v-model="valid" ref="myProForm">
    <v-text-field v-model="promotion.name" label="请输入活动名称" required :rules="nameRules"/>
    <v-text-field v-model="promotion.text" label="请输入促销描述" />

    <v-layout row>
      <v-flex xs3>
        <span style="font-size: 16px; color: #444">活动图片</span>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex>
        <v-upload
          v-model="promotion.image" url="/upload/image" :multiple="false" :pic-width="400" :pic-height="100"
        />
      </v-flex>
    </v-layout>
    <v-layout class="my-4" row>
      <v-spacer/>
      <v-btn @click="submit" color="primary">提交</v-btn>
      <v-btn @click="clear">重置</v-btn>
    </v-layout>
  </v-form>
</template>

<script>
  export default {
    name: "pro-form",
    props: {
      oldPromotion: {
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
        promotion: {
          id:'',
          name: '', // 名称
          text: '', // 描述
          image: '',// 品牌logo
        },
        nameRules: [
          v => !!v || "名称不能为空",
          v => v.length > 1 || "名称至少2位"
        ]
      }
    },
    methods: {
      submit() {
        // 表单校验
        if (this.$refs.myProForm.validate()) {
          this.$http({
            headers: {
              dataType: 'json',
              contentType: 'application/json',
            },
            method: this.isEdit ? 'put' : 'post',
            url: '/item/promotion',
            data: this.$qs.stringify(this.promotion)
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
        this.$refs.myProForm.reset();
      }
    },
    watch: {
      oldPromotion: {// 监控oldBrand的变化
        handler(val) {
          if (val) {
            // 注意不要直接复制，否则这边的修改会影响到父组件的数据，copy属性即可
            this.promotion= Object.deepCopy(val)
          } else {
            // 为空，初始化brand
            this.promotion = {
              name: '',
              text: '',
              image: '',
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
