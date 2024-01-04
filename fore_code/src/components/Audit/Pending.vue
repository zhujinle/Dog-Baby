<template>
	<div>
		<!-- 面包屑导航区 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
		  <el-breadcrumb-item>首页</el-breadcrumb-item>
		  <el-breadcrumb-item>审核</el-breadcrumb-item>
		  <el-breadcrumb-item>待审核</el-breadcrumb-item>
		</el-breadcrumb>
		<!-- 卡片试图 -->
		<el-card class="box-card">
      <!-- 搜索 -->
      <el-row :gutter="20">
        <el-col :span="7">
            <el-input placeholder="搜索作者ID" v-model="inputUID" clearable @clear="getNewsList">
                <el-button slot="append" icon="el-icon-search" @click="getNewsList"></el-button>
            </el-input>
        </el-col>
      </el-row>

      <!-- 订单列表 -->
      <template>
        <el-table :data="newsList" stripe style="width: 100%">
            <el-table-column prop="nid" label="新闻ID" width="70"></el-table-column>
            <el-table-column prop="title" label="标题" width="360"></el-table-column>
            <el-table-column prop="author" label="作者名称" width="120"></el-table-column>
			<el-table-column prop="authorUID" label="作者id" width="80"></el-table-column>
            <el-table-column prop="date" label="创建时间" width="230"></el-table-column>
            <el-table-column prop="summary" label="简介" width="380"></el-table-column>
            <el-table-column label="操作" fixed="right" width="200">
                <template slot-scope="scope">
                    <!-- 通过 -->
                    <el-tooltip effect="dark" content="通过" placement="top" :enterable="false">
                        <el-button type="success" icon="el-icon-check" size="mini" @click="Pass(scope.row)" ></el-button>
                    </el-tooltip>
                    <!-- 不通过 -->
                    <el-tooltip effect="dark" content="不通过" placement="top" :enterable="false">
                        <el-button type="danger" icon="el-icon-close" size="mini" @click="reject(scope.row)"></el-button>
                    </el-tooltip>
                    <!-- 查看 -->
                    <el-tooltip effect="dark" content="查看详细" placement="top" :enterable="false">
                        <el-button type="warning" icon="el-icon-zoom-in" size="mini" @click="deleteuser(scope.row)"></el-button>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>
        </template>
    </el-card>
	</div>
</template>

<script>
<<<<<<< HEAD
export default {
    data() {
        return {
            newsList: [],
			inputUID: ''
        };
    },
    mounted() {
        this.getNewsList();
    },
    methods: {
        async getNewsList() {
            // Make an API call to get the news list
            // Replace 'getNewsList' with the actual API endpoint
			const params = new URLSearchParams()
			params.append('token', window.sessionStorage.getItem('token'))
			params.append('uid', this.inputUID)
            const res = await this.$http.post('getNeedReviewList',params)
            if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
            console.log(res.data.data)
            this.newsList = res.data.data
        },
        goToNews(news) {
            // Redirect to the news page
            // Replace 'newsPage' with the actual news page route
            this.$router.push({ name: 'newsPage', params: { id: news.id } });
        },
		async Pass (newsinfo) {
			const params = new URLSearchParams()
			params.append('token', window.sessionStorage.getItem('token'))
			params.append('nid', newsinfo.nid)
			params.append('status', 0)
			const res = await this.$http.post('reviewNews', params)
			console.log(res.data.statusCode)
			if (res.data.statusCode !== 200) {
				return this.$message.error(res.msg)
			}
			this.$message.success('保存成功！')
			this.getNewsList()
		},
		async reject (newsinfo) {
			const params = new URLSearchParams()
			params.append('token', window.sessionStorage.getItem('token'))
			params.append('nid', newsinfo.nid)
			params.append('status', 1)
			const res = await this.$http.post('reviewNews', params)
			console.log(res.data.statusCode)
			if (res.data.statusCode !== 200) {
				return this.$message.error(res.msg)
			}
			this.$message.success('保存成功！')
			this.getNewsList()
		},
    }
};
=======
	export default{
		data() {
			return{
				
			}
		},
		created() {
			
		},
		methods:{
			
		}
	}
>>>>>>> 91e96ecc7dc73674c72c839bb24e7abce79e9611
</script>

<style>
</style>