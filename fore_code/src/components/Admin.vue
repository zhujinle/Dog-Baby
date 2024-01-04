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
					:collapse-transition="false" router>
						<!-- API(v-for循环读取菜单数据，从menulist中读取每一条一级菜单的数据，在此命名为item) -->
						<!-- 一级菜单 -->
						<el-submenu :index="item.id + '' " v-for="item in menulist" :key="item.id">
							<template slot="title">
								<i :class="iconsObj[item.id]"></i>
								<span>{{item.authName}}</span>
							</template>
							
							<!-- 二级菜单(利用v-for从menulist中读取上述一级菜单item下的二级菜单item.children，并命名为subItem) -->
							<el-menu-item :index="'/' + subItem.path" v-for="subItem in item.children" :key="subItem.id">
								<template slot="title">
									<i class="el-icon-menu"></i>
									<span>{{subItem.authName}}</span>
								</template>
							</el-menu-item>	
						</el-submenu>
					</el-menu>
			</el-aside>
			<!-- 右侧内容主体-->
			<el-main>
				<!-- 路由占位符 -->
				<router-view></router-view>
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
				// 左侧菜单图标
				iconsObj: {
				        '100': 'iconfont icon-xinwen',
				        '200': 'iconfont icon-a-shenhe1',
				        '300': 'iconfont icon-pinglun',
				        '400': 'iconfont icon-yonghuguanli',
				      },
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
				const params = new URLSearchParams()
				params.append('token', window.sessionStorage.getItem('token'))
				const res = await this.$http.post('getmenus', params)
				if(res.data.statusCode !== 200) return this.$message.error(res.data.msg)
				this.menulist = res.data.data
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
	.iconfont{
		margin-right: 10px;
		font-size: 20px;
	}
</style>