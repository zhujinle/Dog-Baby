<template>
	<div>
		<!-- 面包屑导航区 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
		  <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
		  <el-breadcrumb-item>新闻</el-breadcrumb-item>
		  <el-breadcrumb-item>全部新闻</el-breadcrumb-item>
		</el-breadcrumb>
		
		<!-- 卡片视图区域 -->
		<el-card class="box-card">
		  <div v-for="o in 4" :key="o" class="text item">
		    {{'列表内容 ' + o }}
		  </div>
		</el-card>
	</div>
</template>

<script>
	export default{
		
	}
</script>

<style lang="less" scoped>
	
</style>