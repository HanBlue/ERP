<template>
  <el-table
    :data="desserts"
    border
    stripe
    style="width: 100%">
    <el-table-column
      prop="name"
      label="属性"
      width="200">
    </el-table-column>
    <el-table-column
      prop="calories"
      label="详情"
      width="450">
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    name: "order-detail",
    props: {
      oldOrder: {
        type: Object
      }},
    data() {
      return {
        desserts: [
          {
            name: "订单编号",
            calories: this.oldOrder.id,
          },
          {
            name: '物流名称',
            calories: this.oldOrder.shipping_name,
          },
          {
            name: '物流单号',
            calories: this.oldOrder.shipping_code,
          },
          {
            name: '收货人姓名',
            calories: this.oldOrder.receiver,
          },
          {
            name: '收货手机号',
            calories: this.oldOrder.receiver_mobile,
          },
          {
            name: '收货地址',
            calories: this.oldOrder.receiver_address,
          }
          ],
        oldOrder: {
          id: '',
          shipping_name: '',
          shipping_code: '',
          receiver: '',
          receiver_mobile:'',
          receiver_address:'',
        },
      }
    },
    methods: {},
    watch: {
      oldOrder: {// 监控oldUser的变化
        handler(val) {
          if (val) {
            // 注意不要直接复制，否则这边的修改会影响到父组件的数据，copy属性即可
            this.OldOrder = Object.deepCopy(val)
          }else {
            // 为空，初始化brand
            this.OldOrder = {
              id: '',
              shipping_name: '',
              shipping_code: '',
              receiver: '',
              receiver_mobile:'',
              receiver_address:'',
            }}
        },
        deep: true
      },
    }
  }
</script>

<style scoped>

</style>
