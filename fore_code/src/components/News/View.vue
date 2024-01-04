<template>
	<div>
		<!-- 面包屑导航区 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item>首页</el-breadcrumb-item>
			<el-breadcrumb-item>新闻</el-breadcrumb-item>
			<el-breadcrumb-item>全部新闻</el-breadcrumb-item>
		</el-breadcrumb>
		
		<!-- 卡片视图区域 -->
		<el-card>
			<!-- 搜索与添加区域 -->
			<el-row :gutter="20">
				<el-col :span="10">
					<el-input placeholder="请输入新闻ID" v-model="inputNewId">
						<el-button slot="append" icon="el-icon-search"></el-button>
					</el-input>
				</el-col>
			</el-row>
			<!-- 用户列表区域 -->
			<el-table :data="NewsList">
				<el-table-column label="新闻编号" prop="nid"></el-table-column>
				<el-table-column label="新闻标题" prop="title"></el-table-column>
				<el-table-column label="新闻内容" prop="content"></el-table-column>
				<el-table-column label="发布日期" prop="date"></el-table-column>
				<el-table-column label="预览图" prop="img"></el-table-column>
				<el-table-column label="新闻概要" prop="summary"></el-table-column>
			</el-table>
		</el-card>
	</div>
</template>

<script>
	export default{
		data() {
			return{
				// 获取新闻列表的参数对象
				nid:'',
				
				NewsList:[]
				// inputNewId: '',
			}
		},
		created() {
			this.getNewsList()
		},
		methods:{
			async getNewsList(){
				// const params = new URLSearchParams()
				// params.append('nid', this.inputNewId)
				// const res = await this.$http.get('getNews', params)
				
				const res = await this.$http.get('getNews', {params: this.nid})
        		if (res.data.statusCode !== 200) return this.$message.error(res.data.msg)
        		this.NewsList = res.data.news
			}
		}
	}
	
</script>

<style lang="less" scoped>
	
</style>