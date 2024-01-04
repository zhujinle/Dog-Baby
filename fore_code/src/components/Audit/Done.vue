<template>
	<div>
		<!-- 面包屑导航区 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
		  <el-breadcrumb-item>首页</el-breadcrumb-item>
		  <el-breadcrumb-item>审核</el-breadcrumb-item>
		  <el-breadcrumb-item>已审核</el-breadcrumb-item>
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
				<el-table-column prop="type" label="状态（3=未通过|4=通过）" width="190"></el-table-column>
				<el-table-column prop="updateDate" label="最后一次编辑" width="200"></el-table-column>
				<el-table-column prop="summary" label="简介" width="380"></el-table-column>
			</el-table>
			</template>
		</el-card>
	</div>
</template>

<script>
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
            const res = await this.$http.post('getReviewedList',params)
            if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
            console.log(res.data.data)
            this.newsList = res.data.data
        }
    }
};
</script>

<style>
</style>