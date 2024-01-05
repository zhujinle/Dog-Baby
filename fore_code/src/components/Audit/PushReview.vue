<template>
	<div>
		<!-- 面包屑导航区 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
		  <el-breadcrumb-item>首页</el-breadcrumb-item>
		  <el-breadcrumb-item>审核</el-breadcrumb-item>
		   <el-breadcrumb-item>推送审核</el-breadcrumb-item>
		</el-breadcrumb>

		<!-- 卡片视图区域 -->
		<el-card>
			<!-- 新闻列表 -->
			<template>
				<el-table :data="newsList" stripe style="width: 100%">
					<el-table-column prop="nid" label="新闻ID" width="70"></el-table-column>
					<el-table-column prop="title" label="标题" width="300"></el-table-column>
					<el-table-column prop="author" label="作者名称" width="120"></el-table-column>
					<el-table-column prop="authorUID" label="作者id" width="70"></el-table-column>
					<el-table-column prop="date" label="创建时间" width="180"></el-table-column>
					<el-table-column prop="summary" label="简介" width="300"></el-table-column>
					<el-table-column prop="type" label="状态（1:草稿|2:待审核|3:未通过|4:已通过|5:已发表）" width="380"></el-table-column>
					<el-table-column label="操作" fixed="right" width="240">
						<template slot-scope="scope">
							<!-- 查看 -->
							<el-tooltip effect="dark" content="查看详细" placement="top" :enterable="false">
								<el-button type="warning" icon="el-icon-view" size="mini" @click="info(scope.row)"></el-button>
							</el-tooltip>
							<el-tooltip effect="dark" content="推送审核" placement="top" :enterable="false">
								<el-button type="primary" icon="el-icon-edit" size="mini" @click="push(scope.row)"></el-button>
							</el-tooltip>
							<el-tooltip effect="dark" content="发表新闻" placement="top" :enterable="false">
								<el-button type="success" icon="el-icon-finished" size="mini" @click="public(scope.row)"></el-button>
							</el-tooltip>
							<el-tooltip effect="dark" content="删除新闻" placement="top" :enterable="false">
								<el-button type="danger" icon="el-icon-delete" size="mini" @click="deletenews(scope.row)"></el-button>
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
				newsList:[],
				inputUID: 0,
			}
		},
		created() {
			this.getNewsList()
		},
		methods:{
			async getNewsList() {
            // Make an API call to get the news list
            // Replace 'getNewsList' with the actual API endpoint
			const params = new URLSearchParams()
			params.append('token', window.sessionStorage.getItem('token'))
            const res = await this.$http.get('getNewsList?token='+window.sessionStorage.getItem('token'))
            if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
            console.log(res.data.data)
            this.newsList = res.data.data
			this.inputUID = '';
			},
			async info (newsinfo) {
				window.open('#/detail/'+ newsinfo.nid, '_blank');
			},
			async edit (newsinfo) {
				window.open('#/edit/'+ newsinfo.nid, '_blank');
			},
			async deletenews (newsinfo) {
				const params = new URLSearchParams()
				params.append('token', window.sessionStorage.getItem('token'))
				params.append('nid', newsinfo.nid)
				const res = await this.$http.post('deleteNews',params)
				if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
				this.$message.success('删除成功！')
				this.getNewsList()
			},
			
			async push(newsinfo){
				const params = new URLSearchParams()
				params.append('token', window.sessionStorage.getItem('token'))
				params.append('nid', newsinfo.nid)
				const res = await this.$http.post('pushToReview',params)
				if (res.data.statusCode !== 200) return this.$message.error(res.data.msg)
				this.$message.success('推送成功！')
				this.getNewsList()
			},
			async public(newsinfo){
				const params = new URLSearchParams()
				params.append('token', window.sessionStorage.getItem('token'))
				params.append('nid', newsinfo.nid)
				const res = await this.$http.post('publicNews',params)
				if (res.data.statusCode !== 200) return this.$message.error(res.data.msg)
				this.$message.success('发表成功！')
				this.getNewsList()
			},
		}
	}
</script>

<style>
</style>