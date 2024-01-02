import Login from '../components/Login.vue'

// 以下三行为vue2的路由版本，现用的是vue3,所以需要换方式编写路由
// import Vue from 'vue'
// import Router from 'vue-router'
// Vue.use(Router)

// vue3路由
import { createRouter, createWebHistory } from 'vue-router'
const routerHistory = createWebHistory()
const router = createRouter({
  history: routerHistory,
  routes: [
    // 当用户在浏览器中访问/login'这个地址的时候，用component属性来展示Login组件
    { path: '/login', component: Login }
  ]
})
