<template>
	<div>
        <div class="news-header">
            <div class="content text-center" style="background-image: url(https://s2.loli.net/2024/01/04/4ZM5H2JSQ8leKNf.png);">
                <div class="introduce animated fadeInUp">
                    <div class="title">Dog-Baby新闻</div>
                    <div class="mate">好好学习，天天向上</div>
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
        <!-- 评论 -->
		<el-card class="box-card">
      <!-- 评论列表 -->
			<template>
				<el-table :data="commetList" stripe style="width: 100%">
					<el-table-column prop="cid" label="评论ID" width="70"></el-table-column>
					<el-table-column prop="tonewid" label="新闻ID" width="70"></el-table-column>
					<el-table-column prop="fromuser" label="评论人" width="360"></el-table-column>
					<el-table-column prop="commit" label="评论内容" ></el-table-column>
					<el-table-column prop="time" label="评论时间" width="230"></el-table-column>
				</el-table>
			</template>
			<span>添加评论</span>
			<el-form ref="AddFormRef" :model="AddForm">
					<el-form-item label="用户名" prop="fromuser">
						<el-input v-model="AddForm.fromuser" type="fromuser" prefix-icon="el-icon-edit"></el-input>
					</el-form-item>
					<el-form-item label="内容" prop="content">
						<el-input v-model="AddForm.commit" type="commit" prefix-icon="el-icon-link"></el-input>
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
					<el-form-item >
						<el-button type="info" @click="ResetAddForm">重置</el-button>
						<el-button type="primary" @click="AddCommits">确 定</el-button>
					</el-form-item>
			</el-form>
		</el-card>
        <el-footer>
            <div class="text-center">Dog-Baby ©2024 Created by PeterPig&zzx&zcy with ❤</div>
        </el-footer>
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
                commetList:[],
				markdown: "## 正在加载中，如果长时间未响应请刷新。。。",
                AddForm: {
					fromuser: '',
					content: '',
					date1: '',
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
                this.getCommonList()
			},
            async getCommonList() {
				// Make an API call to get the news list
				// Replace 'getNewsList' with the actual API endpoint
                let id = this.$route.params.id
				const res = await this.$http.get('listCommits?tonewsid='+id)
				if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
				console.log(res.data.data)
				this.commetList = res.data.data
      		},
            async AddCommits () {
                const params = new URLSearchParams()
                let id = this.$route.params.id
                params.append('tonewid', id)
                params.append('fromuser', this.AddForm.fromuser)
                params.append('commit', this.AddForm.commit)
                params.append('time', this.AddForm.date1)
                const res = await this.$http.post('addCommit', params)
                if (res.data.statusCode !== 200) return this.$message.error('添加失败')
                this.$message.success('添加成功！')
                this.getCommonList()
            },
            ResetAddForm () {
                this.$refs.AddFormRef.resetFields()
            },

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
.box-card{
    margin-top: 20px;
    margin: 90px;
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
.text-center{
    text-align: center!important;
}
</style>