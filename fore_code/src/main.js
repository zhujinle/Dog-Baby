import Vue from 'vue'
import App from './App.vue'
import router from './router/index'

// 导入全局样式表
import './assets/css/global.css'

import'./plugins/element.js'

// 字体、图标
import'./fonts/user/iconfont.css'
import'./fonts/lock/iconfont.css'
import'./fonts/menus/iconfont.css'

import axios from 'axios'
// 配置请求的根路径（在每个components中定义相对路径，且相对路径的名称需要与对应的API名称保持一致，这样就能自动拼接成一个可访问的网址）
// 只要在main.js（全局）的baseURL中定义根路径为https://127.0.0.1.8888/ ，那么启动这个APP，所有的地址前缀都必定是这一串，“/”后面的路径则由访问的每个组件中所编写的相对路径自动补充。
axios.defaults.baseURL = 'http://10.218.220.81:8080/api/v1/'
Vue.prototype.$http = axios

Vue.config.productionTip = false
new Vue({
	router,
  render: h => h(App),
}).$mount('#app')
