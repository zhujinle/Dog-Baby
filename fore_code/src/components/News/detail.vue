<template>
	<div>
        <div class="news-header">
            <div class="content text-center" style="background-image: url(https://s2.loli.net/2024/01/04/4ZM5H2JSQ8leKNf.png);">
                <div class="introduce animated fadeInUp">
                    <div class="title">PeterPig's Blog</div>
                    <div class="mate">PeterPig的后花园</div>
                </div>
            </div>
        </div>
		<!-- 面包屑导航区 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item>首页</el-breadcrumb-item>
			<el-breadcrumb-item>新闻</el-breadcrumb-item>
			<el-breadcrumb-item>全部新闻</el-breadcrumb-item>
		</el-breadcrumb>
		<div>
  
        <!--渲染位置-->
            <markdown :source=markdown style="margin: 90px;"></markdown>

        </div>
	</div>
</template>

<script>
    import Markdown from "vue-markdown";
    import axios from 'axios';
    import 'markdown-it-vue/dist/markdown-it-vue.css'
	export default{
        // 引入组件
        components: { Markdown },
		data() {
			return{
				markdown: "## 正在加载中，如果长时间未响应请刷新。。。"
			}
		},
		created() {
			this.getNews()
		},
		methods:{
			async getNews(){
                
				// const params = new URLSearchParams()
				// params.append('nid', this.inputNewId)
				
				
				// const{data:res} = await this.$http.get('getNews')
				// if (res.data.statusCode !== 200) return this.$message.error(res.data.msg)
				// console.log(res)
				// this.NewsList = res.data.news
                let id = this.$route.params.id
                const res = await this.$http.get('getNews?nid='+id)
                console.log(res.data)
                if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
                console.log(res.data.data)
                axios.get(res.data.news.content).then((response) => {
                    this.markdown = response.data;
                });
			}
		}
	}
	
</script>

<style lang="less" scoped>
	.content {
    position: relative;
    height: 300px;
    background-position: center center;
    background-size: cover;
    width: 100%;
}

.introduce {
    position: absolute;
    top: 37%;
    z-index: 10;
    width: 100%;
    color: #fff;
    text-shadow: 2px 2px 2px #666;
    cursor: default;
    font-weight: 350;
    font-size:40px;
    font-family: -apple-system,BlinkMacSystemFont,opensans,Optima,"Microsoft Yahei",sans-serif;
    line-height: 1.8;
    overflow-x: hidden;
    text-align: center!important;
}

.mate{
    font-size:15px;
}
</style>