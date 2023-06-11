import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  //auth是否需要登陆后才可以展示，默认为false
  //keepAlive 是否缓存当前页
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login.vue'),
    meta: { title: '登录', keepAlive: true, meta: false }
  },
  {
    path: '/',
    name: 'home',
    component: () => import('../views/Home.vue'),
    meta: { title: '首页', keepAlive: false, meta: true,auth:true }
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/register.vue'),
    meta: { title: '注册', keepAlive: false, meta: false }
  },
  {
    path: '/forgetPwd',
    name: 'forgetPwd',
    component: () => import('../views/forgetPwd.vue'),
    meta: { title: '忘记密码', keepAlive: false, meta: false }
  },
  {
    path: '/my',
    name: 'my',
    component: () => import('../views/my/index.vue'),
    meta: { title: '我的', keepAlive: false, meta: true }
  },
  {
    path: '/bill',
    name: 'bill',
    component: () => import('../views/bill/index.vue'),
    meta: { title: '账单', keepAlive: false, meta: true }
  },
  {
    path: '/success',
    name: 'success',
    component: () => import('../views/success/success.vue'),
    meta: { title: '支付成功', keepAlive: false, meta: false }
  },
  {
    path: '/oldInfomation',
    name: 'oldInfomation',
    component: () => import('../views/oldInfomation/oldInfomation.vue'),
    meta: { title: '老人信息', keepAlive: false, meta: true }
  },
  {
    path: '/nurseInfomation',
    name: 'nurseInfomation',
    component: () => import('../views/nurseInfomation/nurseInfomation.vue'),
    meta: { title: '老人信息', keepAlive: false, meta: true }
  },
  {
    path: '/process',
    name: 'process',
    component: () => import('../views/process/index.vue'),
    meta: { title: '办理进度', keepAlive: false, meta: true },
    // children:[
    // {
    //   path: '/process',
    //   name: 'process',
    //   component: () => import('../views/process/index.vue'),
    //   meta: { title: '办理进度', keepAlive: false },
    // }
    // ]
  },
  {
    path: '/manageInfo',
    name: 'manageInfo',
    component: () => import('../views/manageInfo/manageInfo.vue'),
    meta: { title: '管家信息', keepAlive: false, meta: true }
  },
  {
    path: '/newDetail',
    name: 'newDetail',
    component: () => import('../views/newDetail/newDetail.vue'),
    meta: { title: '新闻详情', keepAlive: false, meta: false }
  },
  {
    path: '/contract',
    name: 'contract',
    component: () => import('../views/contract/contract.vue'),
    meta: { title: '周转房续租', keepAlive: true, meta: false }
  },
  {
    path: '/income',
    name: 'income',
    component: () => import('../views/contract/income.vue'),
    meta: { title: '收入记录', keepAlive: false, meta: false }
  },
  {
    path: '/out',
    name: 'out',
    component: () => import('../views/contract/out.vue'),
    meta: { title: '支出记录', keepAlive: false, meta: false }
  },
  {
    path: '/selectHouse',
    name: 'selectHouse',
    component: () => import('../views/news/selectHouse.vue'),
    meta: { title: '周转房续租', keepAlive: false, meta: false }
  },
  {
    path: '/news',
    name: 'news',
    component: () => import('../views/news/index.vue'),
    meta: { title: '周转房续租', keepAlive: false, meta: false }
  },
  {
    path: '/backRent',
    name: 'backRent',
    component: () => import('../views/news/backRent.vue'),
    meta: { title: '周转房退租', keepAlive: false, meta: false }
  },
  {
    path: '/applyHouse',
    name: 'applyHouse',
    component: () => import('../views/news/applyHouse.vue'),
    meta: { title: '周转房申请', keepAlive: false, meta: false }
  },
]

const router = new VueRouter({
  routes,
  scrollBehavior: () => ({ y: 0 })
})

export default router
