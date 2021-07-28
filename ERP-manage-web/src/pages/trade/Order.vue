<template>
  <v-card>
    <v-card-title>
      <!--搜索框，与search属性关联-->
      <v-flex xs3>
        <v-text-field label="输入关键字搜索" v-model.lazy="search" append-icon="search" hide-details/>
      </v-flex>
    </v-card-title>
    <v-divider/>
    <v-data-table
      :headers="headers"
      :items="orders"
      :pagination.sync="pagination"
      :total-items="total"
      :loading="loading"
      class="elevation-5"
      loader-height=8
    >
      <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.id }}</td>
        <td class="text-xs-center" >{{ props.item.create_time}}</td>
        <td class="text-xs-center">{{ props.item.buyer_name}}</td>
        <td class="text-xs-center">{{ props.item.title}}</td>
        <td class="text-xs-center">{{ props.item.own_spec}}</td>
        <td class="text-xs-center">{{ props.item.num}}</td>
        <td class="text-xs-center">{{ props.item.price}}</td>

        <td class="text-xs-center">{{ props.item.buyer_message}}</td>
        <td class="justify-center layout px-0">
          <v-btn color="info" @click="intoOrder(props.item)">
            物流详情
          </v-btn>

        </td>
      </template>
    </v-data-table>
    <!--弹出的对话框-->
    <v-dialog max-width="750" v-model="show" persistent scrollable>
      <v-card>
        <!--对话框的标题-->
        <v-toolbar dense dark color="primary">
          <v-toolbar-title>{{'查看'}}物流详情</v-toolbar-title>
          <v-spacer/>
          <!--关闭窗口的按钮-->
          <v-btn icon @click="closeWindow"><v-icon>close</v-icon></v-btn>
        </v-toolbar>
        <!--对话框的内容，表单-->
        <v-card-text class="px-5" style="height:400px">
          <order-detail :title="title" @close="closeWindow" :oldOrder="oldOrder"/>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
  import OrderDetail from './OrderDetail'
  export default {
    name: "order",

    data() {
      return {
        search: '', // 搜索过滤字段
        total: 0, // 总条数
        users: [], // 当前页品牌数据
        loading: true, // 是否在加载中
        pagination: {}, // 分页信息
        headers: [
          {text: '订单编号', align: 'center', value: 'id'},
          {text: '创建时间', align: 'center', sortable: false, value: 'create_time'},
          {text: '买家昵称', align: 'center', sortable: false, value: 'buyer_name'},
          {text: '商品标题', align: 'center', sortable: false, value: 'title'},
          {text: '商品属性', align: 'center', sortable: false, value: 'own_spec'},
          {text: '购买数量', align: 'center', sortable: false, value: 'num'},
          {text: '支付金额', align: 'center', sortable: false, value: 'price'},

          {text: '买家留言', align: 'center', sortable: false, value: 'buyer_message'},
          {text: '操作', align: 'center', value: 'id', sortable: false}
        ],
        show: false,// 控制对话框的显示
        oldOrder: {}, // 即将被编辑的品牌数据
      }
    },
    mounted() { // 渲染后执行
      // 查询数据
      this.getDataFromServer();
    },
    watch: {
      pagination: { // 监视pagination属性的变化
        deep: true, // deep为true，会监视pagination的属性及属性中的对象属性变化
        handler() {
          // 变化后的回调函数，这里我们再次调用getDataFromServer即可
          this.getDataFromServer();
        }
      },
      search: { // 监视搜索字段
        handler() {
          this.getDataFromServer();
        }
      }
    },
    methods: {
      getDataFromServer() { // 从服务的加载数的方法。
        this.$http.get("/auth/verify/" )
          .catch(() => {
            // 去登录
            this.$router.push("/login");
          })
          .then(resp => {
            //查询权限
            this.user = resp.data;
            this.$http.get("/user/check/"+this.user.id+"/1")
              .then(resp => {
                  if (resp.status === 200){
        // 发起请求
        this.$http.get("/item/order/page", {
          params: {
            key: this.search, // 搜索条件
            page: this.pagination.page,// 当前页
            rows: this.pagination.rowsPerPage,// 每页大小
            sortBy: this.pagination.sortBy,// 排序字段
            desc: this.pagination.descending// 是否降序
          }
        }).then(resp => { // 这里使用箭头函数
          this.orders = resp.data.items;
          this.total = resp.data.total;
          // 完成赋值后，把加载状态赋值为false
          this.loading = false;
        })
                  }})
              .catch((error) =>{
                if (error.response.status === 401) {
                  this.$message({
                    type: 'error',
                    message: '抱歉，您无权访问'
                  })
                }
              })
          })
      },
      intoOrder(oldOrder) {
            // 控制弹窗可见：
            this.show = true;
            this.oldOrder = oldOrder;
      },
      closeWindow() {
        // 重新加载数据
        this.getDataFromServer();
        // 关闭窗口
        this.show = false;
      }
    },
    components: {
      OrderDetail
    }
  }
</script>

<style scoped>

</style>

