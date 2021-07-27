import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

function route (path, file, name, children) {
  return {
    exact: true,
    path,
    name,
    children,
    component: () => import('../pages' + file)
  }
}

export default new Router({
  routes: [
    {
      path: '/login',
      name: "login",
      component: () => import('../pages/Login'),
    },
    {
      path:"/",
      redirect:"/login",
    },
    {
      path:"/layout", // 根路径，路由到 Layout组件
      component: () => import('../pages/Layout'),
      redirect:"/home/blog",
      children:[ // 其它所有组件都是 Layout的子组件
        route("/trade/dashboard","/Dashboard","Dashboard"),
        route("/item/category",'/item/Category',"Category"),
        route("/item/brand",'/item/Brand',"Brand"),
        route("/item/list",'/item/Goods',"Goods"),
        route("/item/specification",'/item/specification/Specification',"Specification"),
        route("/trade/promotion",'/trade/Promotion',"Promotion"),
        route("/trade/order",'/trade/Order',"Order"),
        route("/authority/user",'/user/User',"User"),
        route("/authority/role",'/user/Role',"Role"),
        route("/home/blog",'/Blog',"Blog"),
        route("/system/swagger",'/Swagger',"Swagger")
      ]
    }
  ]
})
