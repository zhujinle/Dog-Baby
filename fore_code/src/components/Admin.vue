<template>
	<el-container class="admin-container">
		<!-- 头部区域 -->
		<el-header>
			<img src="../assets/logo.png">
			<span>Dog-Baby后台管理系统</span>
			<el-button type="info" @click = "logout()">退出</el-button>
		</el-header>
		<!-- 页面主体区域 -->
		<el-container>
			<!-- 侧边栏区域 -->
			<el-aside :width="isCollapse ? '64px' : '200px' ">
				<div class="toggle-button" @click="toggleCollapse">|||</div>
				<!-- 侧边栏菜单区域 -->
				<el-menu background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" unique-opened :collapse="isCollapse"
				:collapse-transition="false">
					<!-- 对上API接口后，一级菜单和二级菜单各进行一个for循环来自动获取菜单数据（另外还需注意span中的authName是否与API中的一致） -->
					<!-- 一级菜单 -->
					<!-- <el-submenu index="item.id + '' " v-for="item in menulist" :key="item.id">
						<template slot="title">
							<i class="el-icon-location"></i>
							<span>{{item.authName}}</span>
						</template> -->
						
							<!-- 二级菜单 -->
						<!-- <el-menu-item index="subItem.id + '' " v-for="subItem in item.children" :key="subItem.id">
							<template slot="title">
								<i class="el-icon-menu"></i>
								<span>{{subItem.authName}}</span>
							</template>
						</el-menu-item>	 -->
						
						
					<!-- 一级菜单 -->
					<el-submenu index="1">
						<template slot="title">
							<i class="el-icon-location"></i>
							<span>新闻</span>
						</template>
							
						<!-- 二级菜单 -->
						<el-menu-item index="1-1">
							<template slot="title">
								<i class="el-icon-menu"></i>
								<span>我的文章</span>
							</template>
						</el-menu-item>			
						
						<el-menu-item index="1-2">
							<template slot="title">
								<i class="el-icon-menu"></i>
								<span>写文章</span>
							</template>
						</el-menu-item>	
					</el-submenu>
								
					<el-submenu index="2">
						<template slot="title">
							<i class="el-icon-location"></i>
							<span>评论</span>
						</template>			
					</el-submenu>
					
					<el-submenu index="3">
						<template slot="title">
							<i class="el-icon-location"></i>
							<span>个人资料</span>
						</template>
					</el-submenu>
				</el-menu>
			</el-aside>
			<!-- 右侧内容主体 -->
			<el-main>
				
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	export default{
		data() {
			return {
				// 左侧菜单数据
				menulist: [],
				// 是否折叠
				isCollapse: false
			}
		},
		created(){
			this.getMenuList()
		},
		methods: {
			logout() {
				window.sessionStorage.clear();
				this.$router.push("/login");
			},
			
			// 获取所有菜单（'menus'路径需根据API调整）
			async getMenuList(){
				const {data: res} = await this.$http.get('getmenus')
				if(res.data.statusCode !== 200) return this.$message.error(res.data.msg)

				window.sessionStorage.setItem('token', res.data.token)	
				
				this.menulist = res.data
				console.log(res)
			},
			// 点击按钮，展开/折叠菜单
			toggleCollapse(){
				this.isCollapse = !this.isCollapse
			}
		}
	};
</script>

<style lang="less" scoped>
	.admin-container{
		height: 100%;
	}
	.el-header{
		background-color: #3f3f3f;
		display: flex;
		justify-content: space-between;
		padding-left: 10px;
		align-items: center;
		color: #fff;
		font-size: 20px;
	}
	.el-header img{
		padding-left: 10px;
		height: 80px;
		width: 80px;
	}
	.el-aside{
		background-color: #545c64;
	}
	.el-menu{
		border-right: none;
	}
	.el-main{
		background-color: #fffae8;
	}
	.toggle-button{
		background-color: #a6a9ad;
		font-size: 17px;
		line-height: 30px;
		color: #fff;
		text-align: center;
		letter-spacing: 0.2em;
	}
</style>