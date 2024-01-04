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
		</el-card>
	</div>
</template>

<script>
	export default{
		data() {
			return{
				NewsList:[],
				inputNewId: 0,
			}
		},
		created() {
			this.getNewsList()
		},
		methods:{
			async getNewsList(){
				// const params = new URLSearchParams()
				// params.append('nid', this.inputNewId)
				
				
				// const{data:res} = await this.$http.get('getNews')
				// if (res.data.statusCode !== 200) return this.$message.error(res.data.msg)
				// console.log(res)
				// this.NewsList = res.data.news
				try {
        		const response = await axios.get('getNews', {
         		params: {
            		nid: this.inputNewId,
          		},
        		})

        		if (response.data.statusCode !== 200) {
          		this.$message.error(response.data.msg)
          		return
        		}

        		this.NewsList = response.data.data.news
      			}
				
			}
		}
	}
	
</script>

<style lang="less" scoped>
	
</style>