import Vue from 'vue'
import App from './App.vue'
import router from './router'

import './assets/css/reset.css'
import './assets/css/common.css'
import './components/icon/index'

import Dialog from './components/dialog'
import Loading from './components/loading'
import toast from './components/toast'

import './JS/plugin'
import './JS/FastClick'
import filters from './JS/filter'
import utils from './JS/utils'
import VueScroller from 'vue-scroller'
import { get, post } from './JS/ajax'
import lodash from 'lodash'

import vant from './ui/vant'
import 'vant/lib/index.css'

Vue.use(VueScroller)
Vue.use(utils)
Vue.use(vant)

Vue.prototype.$lodash = lodash
Vue.prototype.$dialog = Dialog
Vue.prototype.$loading = Loading
Vue.prototype.$toast = toast
Vue.prototype.$http = { get, post }

// 注入全局过滤器
Object.keys(filters).forEach(item => {
  Vue.filter(item, filters[item])
})

Vue.config.productionTip = false
//陆游守卫判断是否有用户信息，有进入首页，没有返回登陆页面
router.beforeEach((to, from, next) => {
  document.title = to.meta.title
  const username = sessionStorage.getItem('username') || null
  
  if (!username && to.meta.auth) {
    next('/login')
  } else {
    next()
  }
})

export default new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
