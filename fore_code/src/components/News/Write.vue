<template>
	<div>
		<!-- 面包屑导航区 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
		  <el-breadcrumb-item>首页</el-breadcrumb-item>
		  <el-breadcrumb-item>新闻</el-breadcrumb-item>
		  <el-breadcrumb-item>写新闻</el-breadcrumb-item>
		</el-breadcrumb>

		<!-- 卡片试图 -->
		<el-card class="box-card">
			<span>信息输入</span>
			<el-form ref="AddFormRef" :model="AddForm" :rules="AddFormrules">
					<el-form-item label="标题" prop="title">
						<el-input v-model="AddForm.username" type="title" prefix-icon="el-icon-user"></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="password">
						<el-input v-model="AddForm.password" type="password" prefix-icon="el-icon-lock"></el-input>
					</el-form-item>
					<el-form-item label="手机号" prop="phone">
						<el-input v-model="AddForm.phone" prefix-icon="el-icon-user"></el-input>
					</el-form-item>
					<el-form-item label="用户类型选择" prop="Type">
						<template>
						<el-select v-model="AddForm.Type" placeholder="请选择用户类型">
						<el-option
						v-for="item in options"
						:key="item.value"
						:label="item.label"
						:value="item.value">
						</el-option>
						</el-select>
						</template>
					</el-form-item>
			</el-form>
			<!-- 底部 -->
			<span slot="footer" class="dialog-footer">
				<el-button >取 消</el-button>
				<el-button type="info" @click="ResetAddForm">重置</el-button>
				<el-button type="primary" @click="AddUser">确 定</el-button>
			</span>
		</el-card>
	</div>
</template>

<script>
	export default{
		data() {
			return{
				AddForm: {
					title: '',
					password: '',
					phone: '',
					Type: '',
					secretkey: '1'
				},
			}
		},
		created() {
			
		},
		methods:{
			ResetAddForm () {
				this.$refs.AddFormRef.resetFields()
			},

		}
	}
</script>

<style>
</style>