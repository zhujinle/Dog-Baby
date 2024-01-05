<template>
	<div>
		<!-- 面包屑导航区 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
		  <el-breadcrumb-item>首页</el-breadcrumb-item>
		  <el-breadcrumb-item>用户管理</el-breadcrumb-item>
		  <el-breadcrumb-item>添加用户</el-breadcrumb-item>
		</el-breadcrumb>

		<!-- 卡片试图 -->
		<el-card class="box-card">
			<span>信息输入</span>
			<el-form ref="AddFormRef" :model="AddForm">
					<el-form-item label="用户名" prop="title">
						<el-input v-model="AddForm.username" type="username" prefix-icon="el-icon-edit"></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="content">
						<el-input v-model="AddForm.password" type="password" prefix-icon="el-icon-link"></el-input>
					</el-form-item>
					<el-form-item label="人员类型(1:管理员|2:审核|3:编辑)" prop="summary">
						<el-input v-model="AddForm.type" type="type" prefix-icon="el-icon-link"></el-input>
					</el-form-item>
					<el-form-item >
						<el-button >取 消</el-button>
						<el-button type="info" @click="ResetAddForm">重置</el-button>
						<el-button type="primary" @click="AddUser">确 定</el-button>
					</el-form-item>
			</el-form>
		</el-card>
	</div>
</template>

<script>
	export default{
		data() {
			return{
				AddForm: {
					username: '',
					password: '',
					type: '',
				},
			}
		},
		created() {
			
		},
		methods:{
			ResetAddForm () {
				this.$refs.AddFormRef.resetFields()
			},
			async AddUser () {
				const params = new URLSearchParams()
				params.append('token', window.sessionStorage.getItem('token'))
				params.append('username', this.AddForm.username)
				params.append('password', this.AddForm.password)
				params.append('membertype', this.AddForm.type)
				const res = await this.$http.post('addUser', params)
				if (res.data.statusCode !== 200) return this.$message.error('添加失败')
				this.$message.success('添加成功！')
				this.$router.push('/userList');
			},
		}
	}
</script>

<style>
</style>