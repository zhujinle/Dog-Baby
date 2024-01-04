import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'
import Admin from '../components/Admin.vue'
import Views from '../components/News/Views.vue'
import Write from '../components/News/Write.vue'
import Done from '../components/Audit/Done.vue'
import Pending from '../components/Audit/Pending.vue'
import Comments from '../components/Else/Comments.vue'
import MyInfo from '../components/Else/MyInfo.vue'
import Manage from '../components/Else/Manage.vue'

Vue.use(Router)

const router = new Router({
	routes: [
	// 重定向网页，如下意思即为启动APP时，默认将网址定向到login页面
		{ path: '/', redirect:'Login'},
    // 当用户在浏览器中访问/login'这个地址的时候，用component属性来展示Login组件
		{ path: '/login', component: Login },
		{ 
			path: '/admin', 
			component: Admin, 
			redirect: '/views',
			children: [
				{path: '/views', component: Views}, 
				{path: '/write', component: Write},
				{path: '/done', component: Done},
				{path: '/pending', component: Pending},
				{path: '/comments', component: Comments},
				{path: '/myInfo', component: MyInfo},
				{path: '/manage', component: Manage}
			]
		}		
	]
})

// 挂载路由导航守卫（这段代码会影响生产过程中检查别的组件，所以在项目完成前暂时先备注掉）
// router.beforeEach((to, from, next) => {
// 	if(to.path ==='/login') return next();
// 	// 获取SecretKey
// 	const SceretKey = window.sessionStorage.getItem('SecretKey')
// 	  if (!SceretKey) {
// 	    return next('/login')
// 	  }
// 	  next()
// })
export default router