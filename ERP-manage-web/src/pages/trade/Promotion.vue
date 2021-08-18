<template>
  <v-card>
    <v-card-title>
      <v-btn color="primary" @click="addPro">新增促销活动</v-btn>
      <!--搜索框，与search属性关联-->
      <v-spacer/>
      <v-flex xs3>
        <v-text-field label="输入关键字搜索" v-model.lazy="search" append-icon="search" hide-details/>
      </v-flex>
    </v-card-title>
    <v-divider/>
    <v-data-table
      :headers="headers"
      :items="promotions"
      :pagination.sync="pagination"
      :total-items="totalPromotions"
      :loading="loading"
      class="elevation-1"
      height="500"
    >
      <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.id }}</td>
        <td class="text-xs-center">{{ props.item.name }}</td>
        <td class="text-xs-center">{{ props.item.text}}</td>
        <td class="text-xs-center">
          <img v-if="props.item.image" :src="props.item.image" width="380" height="60">
          <span v-else>无</span>
        </td>

        <td class="justify-center  px-0">
          <el-button   icon="el-icon-edit" type="primary"  circle @click="editPro(props.item)">
          </el-button>
          <el-button   icon="el-icon-delete"  type="danger" circle @click="deletePro(props.item)">
          </el-button>
        </td>
      </template>
    </v-data-table>
    <!--弹出的对话框-->
    <v-dialog max-width="500" v-model="show" persistent scrollable>
      <v-card>
        <!--对话框的标题-->
        <v-toolbar dense dark color="primary">
          <v-toolbar-title>{{isEdit ? '修改' : '新增'}}促销活动</v-toolbar-title>
          <v-spacer/>
          <!--关闭窗口的按钮-->
          <v-btn icon @click="closeWindow"><v-icon>close</v-icon></v-btn>
        </v-toolbar>
        <!--对话框的内容，表单-->
        <v-card-text class="px-5" style="height:400px">
          <pro-form @close="closeWindow" :oldPromotion="oldPromotion" :isEdit="isEdit"/>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
  // 导入自定义的表单组件
  import ProForm from './ProForm'


  export default {
    name: "promotion",
    data() {
      return {
        search: '', // 搜索过滤字段
        totalPromotions: 0, // 总条数
        brands: [], // 当前页品牌数据
        loading: true, // 是否在加载中
        pagination: {}, // 分页信息
        headers: [
          {text: 'id', align: 'center', value: 'id'},
          {text: '促销名称', align: 'center', sortable: false, value: 'name'},
          {text: '描述', align: 'center', value: 'text', sortable: true,},
          {text: '图片', align: 'center', sortable: false, value: 'image'},
          {text: '操作', align: 'center', value: 'id', sortable: false}
        ],
        show: false,// 控制对话框的显示
        oldPromotion: {}, // 即将被编辑的品牌数据
        isEdit: false, // 是否是编辑
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
            this.$router.go(0);
          })
          .then(resp => {
            //查询权限
            this.user = resp.data;
            this.$http.get("/user/check/"+this.user.id+"/2")
              .then(resp => {
                  if (resp.status === 200){
        // 发起请求
        this.$http.get("/item/promotion/page", {
          params: {
            key: this.search, // 搜索条件
            page: this.pagination.page,// 当前页
            rows: this.pagination.rowsPerPage,// 每页大小
            sortBy: this.pagination.sortBy,// 排序字段
            desc: this.pagination.descending// 是否降序
          }
        }).then(resp => { // 这里使用箭头函数
          console.log(resp);
          this.promotions = resp.data.items;
          this.totalPromotions = resp.data.total;
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
      addPro() {
        // 修改标记
        this.isEdit = false;
        // 控制弹窗可见：
        this.show = true;
        // 把oldBrand变为null
        this.oldPromotion = null;
      },
      editPro(oldPromotion){
        // 根据品牌信息查询商品分类
            // 修改标记
            this.isEdit = true;
            // 控制弹窗可见：
            this.show = true;
            // 获取要编辑的brand
            this.oldPromotion = oldPromotion
            // 回显商品分类
      },
      deletePro:function(t){
        var e=this;
        this.$confirm('此操作将永久删除该活动, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function(){
          e.$http.delete("/item/promotion?id="+t.id)
            .then(function(){
            e.$message.success("删除成功！")}).then(function (){
            e.getDataFromServer()
          })
        }).catch(function(){e.$message.info("删除已取消！")})
      },
      closeWindow(){
        // 重新加载数据
        this.getDataFromServer();
        // 关闭窗口
        this.show = false;
      }
    },
    components:{
      ProForm
    }
  }
</script>

<style scoped>

</style>

