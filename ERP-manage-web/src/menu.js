var menus = [
  {
    action: "home",
    title: "首页",
    path:"/home",
    items: [{ title: "欢迎页", path: "/blog" }]
  },
  {
    action: "apps",
    title: "商品管理",
    path:"/item",
    items: [
      { title: "分类管理", path: "/category" },
      { title: "品牌管理", path: "/brand" },
      { title: "商品列表", path: "/list" },
      { title: "规格参数", path: "/specification" }
    ]
  },
  {
    action: "attach_money",
    title: "交易管理",
    path:"/trade",
    items: [
      { title: "交易统计", path: "/dashboard" },
      { title: "订单管理", path: "/order" },
      { title: "促销管理", path: "/promotion" }
    ]
  },
  {
    action: "settings",
    title: "权限管理",
    path:"/authority",
    items: [
      { title: "角色管理", path: "/role" },
      { title: "用户管理", path: "/user" }
    ]
  },
  {
    action: "settings",
    title: "系统管理",
    path:"/system",
    items: [
      { title: "系统接口", path: "/swagger" },
    ]
  }
]

export default menus;
