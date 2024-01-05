<template>
	<div>
		<!-- 面包屑导航区 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
		  <el-breadcrumb-item>首页</el-breadcrumb-item>
		  <el-breadcrumb-item>用户管理</el-breadcrumb-item>
		  <el-breadcrumb-item>修改用户信息</el-breadcrumb-item>
		</el-breadcrumb>
		<!-- 卡片视图区域 -->
		<el-card>
			<!-- 搜索与添加区域 -->
			<el-row :gutter="20">
				<el-col :span="10">
					<el-input placeholder="请输入筛选用户ID" v-model="inputUID">
						<el-button slot="append" icon="el-icon-search"  @click="getUserList"></el-button>
					</el-input>
				</el-col>
			</el-row>

			<!-- 审核列表 -->
			<template>
				<el-table :data="userList" stripe style="width: 100%">
					<el-table-column prop="uid" label="用户ID" width="170"></el-table-column>
					<el-table-column prop="username" label="用户名" width="570"></el-table-column>
					<el-table-column prop="membertype" label="用户类型（1:管理|2:审核|3:编辑）"></el-table-column>
					<el-table-column label="操作" fixed="right" width="340">
						<template slot-scope="scope">
							<el-tooltip effect="dark" content="修改用户信息" placement="top" :enterable="false">
								<el-button type="primary" icon="el-icon-edit" size="mini" @click="info(scope.row)"></el-button>
							</el-tooltip>
							<el-tooltip effect="dark" content="删除用户" placement="top" :enterable="false">
								<el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteUser(scope.row)"></el-button>
							</el-tooltip>
						</template>
					</el-table-column>
				</el-table>
			</template>
		</el-card>
	</div>
</template>

<script>
	export default{
		data() {
			return{
				userList:[],
				inputUID: '',
			}
		},
		created() {
			this.getUserList()
		},
		methods:{
			async getUserList() {
				// Make an API call to get the news list
				// Replace 'getNewsList' with the actual API endpoint
				const params = new URLSearchParams()
				if(this.inputUID != ''){
					params.append('uid', this.inputUID)
				}
				params.append('token', window.sessionStorage.getItem('token'))
				const res = await this.$http.post('listUser',params)
				if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
				console.log(res.data.data)
				this.userList = res.data.data
				this.inputNID = '';
      		},
			async info (newsinfo) {
				window.open('#/detail/'+ newsinfo.nid, '_blank');
			},
			async deleteUser (newsinfo) {
				const params = new URLSearchParams()
				params.append('token', window.sessionStorage.getItem('token'))
				params.append('uid', newsinfo.uid)
				const res = await this.$http.post('deleteUser', params)
				if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
				this.$message.success('删除成功！')
				this.getUserList()
			},
		}
	}
</script>

<style>
</style>