import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'

Vue.use(Router)


export default new Router({
	routes: [
	// 重定向网页，如下意思即为启动APP时，默认将网址定向到login页面
		{ path: '/', redirect:'login'},
    // 当用户在浏览器中访问/login'这个地址的时候，用component属性来展示Login组件
		{ path: '/login', component: Login }
	]
})
