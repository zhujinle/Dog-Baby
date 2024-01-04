import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'
import Admin from '../components/Admin.vue'
// 新闻
import View from '../components/News/View.vue'
import Write from '../components/News/Write.vue'
import NewsManage from '../components/News/NewsManage.vue'
// 审核
import Done from '../components/Audit/Done.vue'
import Pending from '../components/Audit/Pending.vue'
// 评论
import CommentList from '../components/Comments/CommentList.vue'
import CommentManage from '../components/Comments/CommentManage.vue'
// 用户管理
import UserList from '../components/Manage/UserList.vue'
import ModifyUser from '../components/Manage/ModifyUser.vue'


import index from '../components/index/index.vue'


Vue.use(Router)

const router = new Router({
	routes: [
	// 重定向网页，如下意思即为启动APP时，默认将网址定向到login页面
		{ path: '/', component:index},
    // 当用户在浏览器中访问/login'这个地址的时候，用component属性来展示Login组件
		{ path: '/login', component: Login },
		{ 
			path: '/admin', 
			component: Admin, 
			redirect: '/view',
			children: [
				{path: '/view', component: View}, 
				{path: '/write', component: Write},
				{path: '/newsManage', component: NewsManage},
				
				{path: '/done', component: Done},
				{path: '/pending', component: Pending},
				
				{path: '/commentList', component: CommentList},
				{path: '/commentManage', component: CommentManage},
				
				{path: '/userList', component: UserList},
				{path: '/modifyUser', component: ModifyUser}
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