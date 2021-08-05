<template>
  <el-row  class="cat">
    <el-col :span="22"  :offset="1" >
      <el-card :body-style="{ padding: '0px' }">
        <img src="https://i.loli.net/2021/07/23/PVaU7NKuqCxfyb5.jpg" :fit="fit"class="image">
        <div style="padding: 30px;">
          <span class="headline">欢迎使用本系统，接下来将为您做功能介绍</span>
          <div style="padding: 20px;">
            <tr><span class="header">{{ text0 }}</span></tr>
            <tr><span class="text">{{ text1 }}</span></tr>
            <tr><span class="text">{{ text2 }}</span></tr>
            <br>
            <tr><span class="header">{{ text3 }}</span></tr>
            <tr><el-button   type="text"  @click="brand">品牌管理
            </el-button><span class="text">{{ text4 }}</span></tr>
            <tr><el-button  type="text"  @click="goods">商品列表
            </el-button><span class="text">{{ text5 }}</span></tr>
            <tr><el-button  type="text"  @click="spec">规格参数
            </el-button><span class="text">{{ text6 }}</span></tr>
            <tr><el-button  type="text"  @click="cate">分类管理
            </el-button><span class="text">{{ text7 }}</span></tr>
            <br>
            <tr><span class="header">{{ text8 }}</span></tr>
            <tr><el-button  type="text"  @click="dash">交易统计
            </el-button><span class="text">{{ text9 }}</span></tr>
            <tr><el-button  type="text"  @click="order">交易管理
            </el-button><span class="text">{{ text10 }}</span></tr>
            <tr><el-button  type="text"  @click="pro">促销管理
            </el-button><span class="text">{{ text11 }}</span></tr>
            <br>
            <tr><span class="header">{{ text12 }}</span></tr>
            <tr><el-button  type="text"  @click="role">角色管理
            </el-button><span class="text">{{ text13 }}</span></tr>
            <tr><el-button  type="text"  @click="user">用户管理
            </el-button><span class="text">{{ text14 }}</span></tr>
            <br>
            <tr><span class="header">{{ text15 }}</span></tr>
            <tr><span class="text">{{ text16 }}<a :href="'http://Hanblue.github.io'">http://Hanblue.github.io</a></span></tr>
          </div>

        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    name:"Blog",
    data() {
      return {
        text0:"本系统主要是由平台商家进行使用，出于大量订单数据处理与数据保密的需要。",
        text1:"    您在使用本系统前必须登录，‘root’拥有超级管理员权限，‘han1’和‘HAN’拥有运维和游客权限。",
        text2:"    如果您没有账户，那注册后默认只有游客权限，如需要请联系超级管理员赋权。",
        text3:"商品管理功能需要超级管理员或者运维权限。",
        text4:"    中，您可以新增、删除和修改品牌的属性；" ,
        text5:"    中，您可以新增、修改和删除商品的属性，并进行上下架操作；" ,
        text6:"    中，您可以对特定商品分类的规格进行修改；" ,
        text7:"    中，您可以对商品的分类进行新增、修改和删除；",
        text8:"订单管理功能需要超级管理员或者运维权限。" ,
        text9:"    中，您可以查看近期的交易数据和图表；" ,
        text10:"   中，您可以查看订单的购买数据和物流信息；" ,
        text11:"   中，您可以上传，修改或删除促销活动；" ,
        text12:"权限管理功能需要超级管理员权限。" ,
        text13:"   中，您可以对权限或角色进行修改；" ,
        text14:"   中，您可以对所有账户的信息进行修改",
        text15:"在系统管理中可以查看所有后台接口与微服务，供开发人员使用。",
        text16:"更多技术详情，欢迎访问我的博客: "
      }
    },
    methods: {
      brand(){
        this.$router.push('/item/brand')
      },
      goods(){
        this.$router.push('/item/goods')
      },
      spec(){
        this.$router.push('/item/specification')
      },
      cate(){
        this.$router.push('/item/category')
      },
      dash(){
        this.$router.push('/trade/dashboard')
      },
      order(){
        this.$router.push('/trade/order')
      },
      pro(){
        this.$router.push('/trade/promotion')
      },
      role(){
        this.$router.push('/authority/role')
      },
      user(){
        this.$router.push('/authority/user')
      },
    }
  }
</script>

<style lang="scss">
  .text {
    font-size: 15px;
    color: #424242;
  }

  .header{
    font-size: 20px;
    color: #424242;
  }
  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .cat{
    background-repeat: repeat-y;
    background-image: url("../assets/66.jpg");
    background-size: 130%;
  }
  .el-card{
    background-repeat: no-repeat;
    background-image: url("../assets/8.jpg");
    background-size: 170%;
  }

</style>

