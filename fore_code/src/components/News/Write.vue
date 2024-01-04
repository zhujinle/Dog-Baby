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
			<el-form ref="AddFormRef" :model="AddForm">
					<el-form-item label="标题" prop="title">
						<el-input v-model="AddForm.title" type="title" prefix-icon="el-icon-edit"></el-input>
					</el-form-item>
					<el-form-item label="内容URL（做成MarkDown放到云上，这里填URL）" prop="content">
						<el-input v-model="AddForm.content" type="content" prefix-icon="el-icon-link"></el-input>
					</el-form-item>
					<el-form-item label="创建时间" prop="date">
						<el-date-picker
							v-model="AddForm.date1"
							align="right"
							type="date"
							placeholder="选择日期"
							value-format="yyyy-MM-dd HH:mm:ss"
							:picker-options="pickerOptions">
						</el-date-picker>
					</el-form-item>
					<el-form-item label="头图URL（放在CDN。这里填URL）" prop="img">
						<el-input v-model="AddForm.img" type="img" prefix-icon="el-icon-link"></el-input>
					</el-form-item>
					<el-form-item label="简介" prop="summary">
						<el-input v-model="AddForm.summary" type="summary" prefix-icon="el-icon-link"></el-input>
					</el-form-item>
					<el-form-item >
						<el-button >取 消</el-button>
						<el-button type="info" @click="ResetAddForm">重置</el-button>
						<el-button type="primary" @click="AddNews">确 定</el-button>
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
					title: '',
					content: '',
					date1: '',
					img: '',
					summary: ''
				},
				pickerOptions: {
					disabledDate(time) {
						return time.getTime() > Date.now();
					},
					shortcuts: [{
						text: '今天',
						onClick(picker) {
						picker.$emit('pick', new Date());
						}
					}, {
						text: '昨天',
						onClick(picker) {
						const date = new Date();
						date.setTime(date.getTime() - 3600 * 1000 * 24);
						picker.$emit('pick', date);
						}
					}, {
						text: '一周前',
						onClick(picker) {
						const date = new Date();
						date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
						picker.$emit('pick', date);
						}
					}]
				}
			}
		},
		created() {
			
		},
		methods:{
			ResetAddForm () {
				this.$refs.AddFormRef.resetFields()
			},
			async AddNews () {
				const params = new URLSearchParams()
				params.append('token', window.sessionStorage.getItem('token'))
				params.append('title', this.AddForm.title)
				params.append('content', this.AddForm.content)
				params.append('date', this.AddForm.date1)
				params.append('img', this.AddForm.img)
				params.append('summary', this.AddForm.summary)
				const res = await this.$http.post('addNews', params)
				if (res.data.statusCode !== 200) return this.$message.error('添加失败')
				this.$message.success('添加成功！')
				this.$router.push('/view');
			},
		}
	}
</script>

<style>
</style>