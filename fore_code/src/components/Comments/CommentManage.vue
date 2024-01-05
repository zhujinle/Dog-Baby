<template>
	<div>
		<!-- 面包屑导航区 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
		  <el-breadcrumb-item>首页</el-breadcrumb-item>
		  <el-breadcrumb-item>评论</el-breadcrumb-item>
		   <el-breadcrumb-item>评论管理</el-breadcrumb-item>
		</el-breadcrumb>
		<!-- 卡片视图区域 -->
		<el-card>
			<!-- 搜索与添加区域 -->
			<el-row :gutter="20">
				<el-col :span="10">
					<el-input placeholder="请输入筛选新闻ID" v-model="inputNID">
						<el-button slot="append" icon="el-icon-search"  @click="getCommonList"></el-button>
					</el-input>
				</el-col>
			</el-row>

			<!-- 审核列表 -->
			<template>
				<el-table :data="commetList" stripe style="width: 100%">
					<el-table-column prop="cid" label="评论ID" width="70"></el-table-column>
					<el-table-column prop="tonewid" label="新闻ID" width="70"></el-table-column>
					<el-table-column prop="fromuser" label="评论人" width="360"></el-table-column>
					<el-table-column prop="commit" label="评论内容" ></el-table-column>
					<el-table-column prop="time" label="评论时间" width="230"></el-table-column>
					<el-table-column label="操作" fixed="right" width="140">
						<template slot-scope="scope">
							<!-- 查看 -->
							<el-tooltip effect="dark" content="查看新闻" placement="top" :enterable="false">
								<el-button type="warning" icon="el-icon-zoom-in" size="mini" @click="info(scope.row)"></el-button>
							</el-tooltip>
							<el-tooltip effect="dark" content="删除评论" placement="top" :enterable="false">
								<el-button type="danger" icon="el-icon-delete" size="mini" @click="deletecommits(scope.row)"></el-button>
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
				commetList:[],
				inputNID: 0,
			}
		},
		created() {
			this.getCommonList()
		},
		methods:{
			async getCommonList() {
				// Make an API call to get the news list
				// Replace 'getNewsList' with the actual API endpoint
				const params = new URLSearchParams()
				if(this.inputNID == ''){
					this.inputNID = 0;
				}
				const res = await this.$http.get('listCommits?tonewsid='+this.inputNID)
				if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
				console.log(res.data.data)
				this.commetList = res.data.data
				this.inputNID = '';
      		},
			async info (newsinfo) {
				window.open('#/detail/'+ newsinfo.nid, '_blank');
			},
			async deletecommits (newsinfo) {
				const params = new URLSearchParams()
				params.append('token', window.sessionStorage.getItem('token'))
				params.append('commitid', newsinfo.cid)
				const res = await this.$http.post('deleteCommit', params)
				if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
				this.$message.success('删除成功！')
				this.getCommonList()
			},
		}
	}
</script>

<style>
</style>