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
      :items="users"
      :pagination.sync="pagination"
      :total-items="total"
      :loading="loading"
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.id }}</td>
        <td class="text-xs-center">{{ props.item.username}}</td>
        <td class="text-xs-center">{{ props.item.name}}</td>
        <td class="text-xs-center">{{ props.item.phone}}</td>
        <td class="justify-center layout px-0">
          <v-btn color="info" @click="editUser(props.item)">
            编辑
          </v-btn>
          <v-btn color="warning" @click="deleteUser(props.item)">
            删除
          </v-btn>
        </td>
      </template>
    </v-data-table>
    <!--弹出的对话框-->
    <v-dialog max-width="500" v-model="show" persistent scrollable>
      <v-card>
        <!--对话框的标题-->
        <v-toolbar dense dark color="primary">
          <v-toolbar-title>{{'修改'}}用户</v-toolbar-title>
          <v-spacer/>
          <!--关闭窗口的按钮-->
          <v-btn icon @click="closeWindow"><v-icon>close</v-icon></v-btn>
        </v-toolbar>
        <!--对话框的内容，表单-->
        <v-card-text class="px-5" style="height:400px">
          <user-form @close="closeWindow" :oldUser="oldUser" :isEdit="isEdit"/>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
  import UserForm from './UserForm'

  export default {
    name: "users",
    data() {
      return {
        search: '', // 搜索过滤字段
        total: 0, // 总条数
        users: [], // 当前页品牌数据
        loading: true, // 是否在加载中
        pagination: {}, // 分页信息
        headers: [
          {text: '用户编号', align: 'center', value: 'id'},
          {text: '用户账号', align: 'center', sortable: false, value: 'username'},
          {text: '用户角色', align: 'center', sortable: false, value: 'name'},
          {text: '手机号码', align: 'center', sortable: false, value: 'phone'},
          {text: '操作', align: 'center', value: 'id', sortable: false}
        ],
        show: false,// 控制对话框的显示
        oldUser: {}, // 即将被编辑的品牌数据
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
            this.$http.get("/user/check/"+this.user.id+"/7")
              .then(resp => {
                  if (resp.status === 200){
        // 发起请求
        this.$http.get("/user/page", {
          params: {
            key: this.search, // 搜索条件
            page: this.pagination.page,// 当前页
            rows: this.pagination.rowsPerPage,// 每页大小
            sortBy: this.pagination.sortBy,// 排序字段
            desc: this.pagination.descending// 是否降序
          }
        }).then(resp => { // 这里使用箭头函数
          console.log(resp);
          this.users = resp.data.items;
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

      editUser(oldUser) {
        this.$http.get("/user/role/" + oldUser.rid)
          .then(({data}) => {
            // 修改标记
            this.isEdit = true;
            // 控制弹窗可见：
            this.show = true;
            // 获取要编辑的brand
            this.oldUser = oldUser;
            // 回显商品分类
            //this.oldUser.name = data.item.name;
          })
      },
      deleteUser:function(t) {
        var e = this;
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function(){
          e.$http.delete("/user?id=" + t.id)
            .then(function(){
              e.$message.success("删除成功！")}).then(function (){
            e.getDataFromServer()
          })
        }).catch(function(){e.$message.info("删除已取消！")})
      },
      closeWindow() {
        // 重新加载数据
        this.getDataFromServer();
        // 关闭窗口
        this.show = false;
      }
    },
    components: {
      UserForm
    }
  }
</script>

<style scoped>

</style>
