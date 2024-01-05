<template>
	<div>
		<!-- 面包屑导航区 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
		  <el-breadcrumb-item>首页</el-breadcrumb-item>
		  <el-breadcrumb-item>新闻</el-breadcrumb-item>
		  <el-breadcrumb-item>新闻管理</el-breadcrumb-item>
		</el-breadcrumb>
		<!-- 修改新闻对话框 -->
		<el-dialog title="新闻信息修改" :visible.sync="EditdialogVisible" width="50%">
			<span>新闻信息修改</span>
			<el-form :model="EditForm">
					<el-form-item label="标题" prop="title">
						<el-input v-model="EditForm.title" type="title" prefix-icon="el-icon-edit"></el-input>
					</el-form-item>
					<el-form-item label="内容URL（做成MarkDown放到云上，这里填URL）" prop="content">
						<el-input v-model="EditForm.content" type="content" prefix-icon="el-icon-link"></el-input>
					</el-form-item>
					<el-form-item label="创建时间" prop="date">
						<el-date-picker
							v-model="EditForm.date1"
							align="right"
							type="date"
							placeholder="选择日期"
							value-format="yyyy-MM-dd HH:mm:ss"
							:picker-options="pickerOptions">
						</el-date-picker>
					</el-form-item>
					<el-form-item label="头图URL（放在CDN。这里填URL）" prop="img">
						<el-input v-model="EditForm.img" type="img" prefix-icon="el-icon-link"></el-input>
					</el-form-item>
					<el-form-item label="简介" prop="summary">
						<el-input v-model="EditForm.summary" type="summary" prefix-icon="el-icon-link"></el-input>
					</el-form-item>
			</el-form>
			<!-- 页脚 -->
			<span slot="footer" class="dialog-footer">
				<el-button @click="EditdialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="pushEditDialog">确 定</el-button>
			</span>
    	</el-dialog>
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

			<!-- 新闻列表 -->
			<template>
				<el-table :data="newsList" stripe style="width: 100%">
					<el-table-column prop="nid" label="新闻ID" width="70"></el-table-column>
					<el-table-column prop="title" label="标题" width="360"></el-table-column>
					<el-table-column prop="author" label="作者名称" width="120"></el-table-column>
					<el-table-column prop="authorUID" label="作者id" width="80"></el-table-column>
					<el-table-column prop="date" label="创建时间" width="230"></el-table-column>
					<el-table-column prop="summary" label="简介" width="380"></el-table-column>
					<el-table-column label="操作" fixed="right" width="240">
						<template slot-scope="scope">
							<!-- 查看 -->
							<el-tooltip effect="dark" content="查看详细" placement="top" :enterable="false">
								<el-button type="warning" icon="el-icon-zoom-in" size="mini" @click="info(scope.row)"></el-button>
							</el-tooltip>
							<el-tooltip effect="dark" content="编辑新闻" placement="top" :enterable="false">
								<el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row)"></el-button>
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
				EditdialogVisible: false,
				newsList:[],
				inputUID: 0,
				EditForm: {
					nid:'',
					title: '',
					content: '',
					date1: '',
					img: '',
					summary: ''
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
			ResetEditForm () {
				this.$refs.EditFormRef.resetFields()
			},
			async edit(newsinfo){
				const res = await this.$http.get('getNews?nid='+newsinfo.nid)
				if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
				this.$message.success('信息获取成功！')
				this.EditForm.nid = res.data.news.nid
				this.EditForm.title = res.data.news.title
				this.EditForm.content = res.data.news.content
				this.EditForm.date1 = res.data.news.date
				this.EditForm.img = res.data.news.img
				this.EditForm.summary = res.data.news.summary
				this.EditdialogVisible = true
			},
			async pushEditDialog () {
				const params = new URLSearchParams()
				params.append('token', window.sessionStorage.getItem('token'))
				params.append('nid', this.EditForm.nid)
				params.append('title', this.EditForm.title)
				params.append('content', this.EditForm.content)
				params.append('date', this.EditForm.date1)
				params.append('img', this.EditForm.img)
				params.append('summary', this.EditForm.summary)
				const res = await this.$http.post('modifyNews', params)
				if (res.data.statusCode !== 200) return this.$message.error('修改失败')
				this.$message.success('修改成功！')
				this.EditdialogVisible = false
				this.getNewsList()
			},
		}
	}
</script>

<style>
</style>