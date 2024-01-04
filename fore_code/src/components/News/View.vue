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
					<el-input placeholder="请输入作者ID" v-model="inputUID">
						<el-button slot="append" icon="el-icon-search"  @click="getNewsList"></el-button>
					</el-input>
				</el-col>
			</el-row>

			<!-- 审核列表 -->
			<template>
				<el-table :data="newsList" stripe style="width: 100%">
					<el-table-column prop="nid" label="新闻ID" width="70"></el-table-column>
					<el-table-column prop="title" label="标题" width="360"></el-table-column>
					<el-table-column prop="author" label="作者名称" width="120"></el-table-column>
					<el-table-column prop="authorUID" label="作者id" width="80"></el-table-column>
					<el-table-column prop="date" label="创建时间" width="230"></el-table-column>
					<el-table-column prop="summary" label="简介" width="380"></el-table-column>
					<el-table-column label="操作" fixed="right" width="80">
						<template slot-scope="scope">
							<!-- 查看 -->
							<el-tooltip effect="dark" content="查看详细" placement="top" :enterable="false">
								<el-button type="warning" icon="el-icon-zoom-in" size="mini" @click="info(scope.row)"></el-button>
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
			if(this.inputUID == ''){
				this.inputUID = 0;
			}
            const res = await this.$http.get('getNewsList?uid='+this.inputUID)
            if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
            console.log(res.data.data)
            this.newsList = res.data.data
			this.inputUID = '';
        },
			async info (newsinfo) {
			window.open('#/detail/'+ newsinfo.nid, '_blank');
		},
		}
	}
	
</script>

<style lang="less" scoped>
	
</style>