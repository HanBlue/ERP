<template>
  <el-row  class="cat">
    <el-col :span="22"  :offset="1" >
      <el-card :body-style="{ padding: '0px' }">
        <img src="../assets/12.jpg" :fit="fit"class="image">
        <div style="padding: 30px;">
          <span class="head2">欢迎使用本系统，接下来将为您做功能介绍＜（＾－＾）＞</span>
          <div style="padding: 20px;">

            <tr><span class="text">{{ text1 }}</span></tr>
            <br>
            <tr><span class="header1">{{ text0 }}</span></tr>
            <tr><span class="text">{{ text2 }}</span></tr>

            <br>
            <tr><span class="header">{{ text3 }}</span></tr>
            <br>
            <tr>
              <div>
            <td width="30px" @click="brand1" >
              <div v-show=!brandseen >
            <el-avatar  shape="square" :size="80" :fit="fit" :src="'https://i.loli.net/2021/08/15/TPjiYhc68XKdRVy.png'" ></el-avatar>
              <p class="text" align="center" >品牌管理</p>
              </div>
            </td>
                <td  v-show = brandseen   >
                  <div  @click="brand2" >
                  <el-card :body-style="{ padding: '0px' }" >
                    <img src="https://i.loli.net/2021/08/11/Y1bukS4hfCGIUqm.jpg" class="image">
                      <span class="text" >品牌管理中，您可以新增、删除和修改品牌的属性；</span>
                      <div align="center"><el-button type="text" class="button" @click="brand" >现在访问<i class="el-icon-s-promotion el-icon--right"></i></el-button></div>
                  </el-card>
                  </div>
                </td>

                <td width="50px"></td>
                <td width="30px" @click="goods1" >
                  <div v-show=!goodsseen >
                    <el-avatar  shape="square" :size="80" :fit="fit" :src="'https://i.loli.net/2021/08/15/NvZCakj8prYyPWQ.png'" ></el-avatar>
                    <p class="text" align="center" >商品列表</p>
                  </div>
                </td>
                <td  v-show = goodsseen   >
                  <div  @click="goods2" >
                    <el-card :body-style="{ padding: '0px' }" >
                      <img src="https://i.loli.net/2021/08/11/S9rXKjynMDLf81A.jpg" class="image">
                      <span class="text" >商品列表功能中，您可以管理商品的属性，并进行上下架操作；</span>
                      <div align="center"><el-button type="text" class="button" @click="brand" >现在访问<i class="el-icon-s-promotion el-icon--right"></i></el-button></div>
                    </el-card>
                  </div>
                </td>

                <td width="50px"></td>
                  <td width="30px" @click="spec1" >
                    <div v-show=!specseen >
                      <el-avatar  shape="square" :size="80" :fit="fit" :src="'https://i.loli.net/2021/08/15/HTq9i6xmal1JONv.png'" ></el-avatar>
                      <p class="text" align="center" >规格参数</p>
                    </div>
                  </td>
                  <td  v-show=specseen  >
                    <div  @click="spec2" >
                      <el-card :body-style="{ padding: '0px' }" >
                        <img src="https://i.loli.net/2021/08/11/eCbkrwTYMVaBHnu.jpg" class="image">
                        <span class="text" >规格参数功能中，您可以对特定商品分类的规格进行修改；</span>
                        <div align="center"><el-button type="text" class="button" @click="spec" >现在访问<i class="el-icon-s-promotion el-icon--right"></i></el-button></div>
                      </el-card>
                    </div>
                  </td>

                <td width="50px"></td>
                <td width="30px" @click="cate1" >
                  <div v-show=!cateseen >
                    <el-avatar  shape="square" :size="80" :fit="fit" :src="'https://i.loli.net/2021/08/15/dQMR4YiUzp6qK8u.png'" ></el-avatar>
                    <p class="text" align="center" >分类管理</p>
                  </div>
                </td>
                <td  v-show=cateseen   >
                  <div  @click="cate2" >
                    <el-card :body-style="{ padding: '0px' }" >
                      <img src="https://i.loli.net/2021/08/11/2j6iTsPuqwdIXtH.jpg" class="image">
                      <span class="text" >分类管理功能中，您可以对商品的分类进行新增、修改和删除；</span>
                      <div align="center"><el-button type="text" class="button" @click="cate" >现在访问<i class="el-icon-s-promotion el-icon--right"></i></el-button></div>
                    </el-card>
                  </div>
                </td>
              </div>
            </tr>

            <br>
            <tr><span class="header">{{ text8 }}</span></tr>
            <br>
            <tr>
              <div>
                <td width="30px" @click="dash1" >
                  <div v-show=!dashseen >
                    <el-avatar  shape="square" :size="80" :fit="fit" :src="'https://i.loli.net/2021/08/15/vjd7Tbq5UL2z6Se.png'" ></el-avatar>
                    <p class="text" align="center" >交易统计</p>
                  </div>
                </td>
                <td  v-show = dashseen  width=500px >
                  <div  @click="dash2" >
                    <el-card :body-style="{ padding: '0px' }" >
                      <img src="https://i.loli.net/2021/08/11/XoxkPrWqhCfbcVv.jpg" class="image">
                      <span class="text" >交易统计功能中，您可以查看近期的交易数据和图表；</span>
                      <div align="center"><el-button type="text" class="button" @click="dash" >现在访问<i class="el-icon-s-promotion el-icon--right"></i></el-button></div>
                    </el-card>
                  </div>
                </td>

                <td width="50px"></td>
                <td width="30px" @click="order1" >
                  <div v-show=!orderseen >
                    <el-avatar  shape="square" :size="80" :fit="fit" :src="'https://i.loli.net/2021/08/15/t1qXI5VYbZLQygx.png'" ></el-avatar>
                    <p class="text" align="center" >订单管理</p>
                  </div>
                </td>
                <td  v-show = orderseen  width=500px >
                  <div  @click="order2" >
                    <el-card :body-style="{ padding: '0px' }" >
                      <img src="https://i.loli.net/2021/08/11/PH1MgmRn6yFTo4C.jpg" class="image">
                      <span class="text" >订单管理功能中，您可以查看订单的购买数据和物流信息；</span>
                      <div align="center"><el-button type="text" class="button" @click="order">现在访问<i class="el-icon-s-promotion el-icon--right"></i></el-button></div>
                    </el-card>
                  </div>
                </td>

                <td width="50px"></td>
                <td width="30px" @click="pro1" >
                  <div v-show=!proseen >
                    <el-avatar  shape="square" :size="80" :fit="fit" :src="'https://i.loli.net/2021/08/15/u5z2hKWyDJ4VeCt.png'" ></el-avatar>
                    <p class="text" align="center" >促销管理</p>
                  </div>
                </td>
                <td  v-show=proseen  width=500px >
                  <div  @click="pro2" >
                    <el-card :body-style="{ padding: '0px' }" >
                      <img src="https://i.loli.net/2021/08/11/aYHLsNwCVgbujtc.jpg" class="image">
                      <span class="text" >促销管理功能中，您可以上传，修改或删除促销活动；</span>
                      <div align="center"><el-button type="text" class="button" @click="pro" >现在访问<i class="el-icon-s-promotion el-icon--right"></i></el-button></div>
                    </el-card>
                  </div>
                </td>
              </div>
            </tr>

            <br>
            <tr><span class="header">{{ text12 }}</span></tr>
            <br>
            <tr>
              <div>
                <td width="30px" @click="role1" >
                  <div v-show=!roleseen >
                    <el-avatar  shape="square" :size="80" :fit="fit" :src="'https://i.loli.net/2021/08/15/QT2hRLuYnls9BEq.png'" ></el-avatar>
                    <p class="text" align="center" >权限管理</p>
                  </div>
                </td>
                <td  v-show = roleseen  width=500px >
                  <div  @click="role2" >
                    <el-card :body-style="{ padding: '0px' }" >
                      <img src="https://i.loli.net/2021/08/11/sfpDJgNzhI9uYG1.jpg" class="image">
                      <span class="text" >权限管理功能中，您可以对全局的权限信息进行修改；</span>
                      <div align="center"><el-button type="text" class="button" @click="role" >现在访问<i class="el-icon-s-promotion el-icon--right"></i></el-button></div>
                    </el-card>
                  </div>
                </td>

                <td width="50px"></td>
                <td width="30px" @click="user1" >
                  <div v-show=!userseen >
                    <el-avatar  shape="square" :size="80" :fit="fit" :src="'https://i.loli.net/2021/08/15/WZaeSsfNHMuE3dG.png'" ></el-avatar>
                    <p class="text" align="center" >用户管理</p>
                  </div>
                </td>
                <td  v-show = userseen  width=500px >
                  <div  @click="user2" >
                    <el-card :body-style="{ padding: '0px' }" >
                      <img src="https://i.loli.net/2021/08/11/nC9EIOMs5WAQbgi.jpg" class="image">
                      <span class="text" >用户管理功能中，您可以对所有账户的信息进行修改；</span>
                      <div align="center"><el-button type="text" class="button" @click="user">现在访问<i class="el-icon-s-promotion el-icon--right"></i></el-button></div>
                    </el-card>
                  </div>
                </td>

              </div>
            </tr>

            <br>
            <tr><span class="header">{{ text15 }}</span></tr>
            <br>
            <tr>
              <div>
                <td width="30px" @click="swagger1" >
                  <div v-show=!swaggerseen >
                    <el-avatar  shape="square" :size="80" :fit="fit" :src="'https://i.loli.net/2021/08/15/xUwjQ1kNH6mMJCy.png'" ></el-avatar>
                    <p class="text" align="center" >系统接口</p>
                  </div>
                </td>
                <td  v-show =swaggerseen  width=500px >
                  <div  @click="swagger2" >
                    <el-card :body-style="{ padding: '0px' }" >
                      <img src="https://i.loli.net/2021/08/11/WGtzO2k6Q9qyeV8.jpg" class="image">
                      <span class="text" >系统接口功能中，您可以查看利用Swagger注解展示的所有后台接口信息，方便开发人员进行工作;</span>
                      <div align="center"><el-button type="text" class="button" @click="swagger" >现在访问<i class="el-icon-s-promotion el-icon--right"></i></el-button></div>
                    </el-card>
                  </div>
                </td>

                <td width="50px"></td>
                <td width="30px" @click="eureka1" >
                  <div v-show=!eurekaseen >
                    <el-avatar  shape="square" :size="80" :fit="fit" :src="'https://i.loli.net/2021/08/15/OpjKPzFVHABnlh2.png'" ></el-avatar>
                    <p class="text" align="center" >微服务管理</p>
                  </div>
                </td>
                <td  v-show = eurekaseen  width=500px >
                  <div  @click="eureka2" >
                    <el-card :body-style="{ padding: '0px' }" >
                      <img src="https://i.loli.net/2021/08/11/d98bBkPS4uKQsqR.jpg" class="image">
                      <span class="text" >微服务管理功能中，您可以查看所有正常运作的微服务，这是 Spring Cloud 的特点；</span>
                      <div align="center"><el-button type="text" class="button" @click="eureka">现在访问<i class="el-icon-s-promotion el-icon--right"></i></el-button></div>
                    </el-card>
                  </div>
                </td>
              </div>
            </tr>

            <br>
            <tr><span class="header">{{ text16 }}<a :href="'http://Hanblue.github.io'">http://Hanblue.github.io</a></span></tr>
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

        brandseen:false,
        goodsseen:false,
        specseen:false,
        cateseen:false,
        dashseen:false,
        orderseen:false,
        proseen:false,
        roleseen:false,
        userseen:false,
        swaggerseen:false,
        eurekaseen:false,
        text0:"您可以点击图标查看功能预览",
        text1:"‘root’账户拥有超级管理员权限，‘han1’和‘HAN’拥有运维和游客权限。注册后默认只有游客权限，如需要请联系超级管理员赋权。",
        text2:" ",
        text3:"商品管理功能需要超级管理员或者运维权限",

        text8:"交易管理功能需要超级管理员或者运维权限" ,

        text12:"权限管理功能需要超级管理员权限" ,

        text15:"在系统管理中可以查看所有后台接口与微服务，供开发人员使用",
        text16:"更多技术详情，欢迎访问我的博客: "
      }
    },
    methods: {
      brand(){
        this.$router.push('/item/brand')
      },
      brand1(){
        this.brandseen=true;
      },
      brand2(){
        this.brandseen=false;
      },
      goods(){
        this.$router.push('/item/goods')
      },
      goods1(){
        this.goodsseen=true;
      },
      goods2(){
        this.goodsseen=false;
      },
      spec(){
        this.$router.push('/item/specification')
      },
      spec1(){
        this.specseen=true;
      },
      spec2(){
        this.specseen=false;
      },
      cate(){
        this.$router.push('/item/category')
      },
      cate1(){
        this.cateseen=true;
      },
      cate2(){
        this.cateseen=false;
      },
      dash(){
        this.$router.push('/trade/dashboard')
      },
      dash1(){
        this.dashseen=true;
      },
      dash2(){
        this.dashseen=false;
      },
      order(){
        this.$router.push('/trade/order')
      },
      order1(){
        this.orderseen=true;
      },
      order2(){
        this.orderseen=false;
      },
      pro(){
        this.$router.push('/trade/promotion')
      },
      pro1(){
        this.proseen=true;
      },
      pro2(){
        this.proseen=false;
      },
      role(){
        this.$router.push('/authority/role')
      },
      role1(){
        this.roleseen=true;
      },
      role2(){
        this.roleseen=false;
      },
      user(){
        this.$router.push('/authority/user')
      },
      user1(){
        this.userseen=true;
      },
      user2(){
        this.userseen=false;
      },
      swagger(){
        this.$router.push('/system/swagger')
      },
      swagger1(){
        this.swaggerseen=true;
      },
      swagger2(){
        this.swaggerseen=false;
      },
      eureka(){
        this.$router.push('/system/eureka')
      },
      eureka1(){
        this.eurekaseen=true;
      },
      eureka2(){
        this.eurekaseen=false;
      }
  }
  }
</script>

<style lang="scss">
  .text {
    font-size: 15px;
    color: #424242;
    font-family: tes2;
  }
 .head2{
  font-family: tes2;
   font-size: 25px;
}
  .header{
    font-size: 20px;
    color: #424242;
    font-family: tes2;
  }
  .header1{
    font-size: 17px;
    color: #3a8ee6;
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
    background-repeat: repeat-y;
    background-image: url("../assets/5.jpg");
    background-size: 130%;
  }

</style>

