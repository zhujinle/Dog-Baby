<template>
	<div class="login_container">
		<div class="login_box">
			<!-- 头像区域 -->
			<div class="avatar_box">
				<img src="../assets/logo.png">
			</div>
			<!-- 表单区域 -->
			<el-form ref="LoginFormRef" :model="LoginForm" :rules="LoginFormRules" label-width="0px" class="login_form">
				<!-- 用户名 -->
				<el-form-item prop="Username">
					<el-input v-model="LoginForm.Username" prefix-icon="iconfont icon-yonghu" placeholder="用户名"></el-input>
				</el-form-item>
				<!-- 密码 -->
				<el-form-item prop="Password">
					<el-input v-model="LoginForm.Password" type="password" prefix-icon="iconfont icon-suoding" placeholder="密码"></el-input>
				</el-form-item>
				
				<!-- 按钮区域 -->
				<el-form-item class="btns">
					<el-button type="primary" @click="Login()">登录</el-button>
					<el-button type="info" @click="ResetLoginForm()">重置</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
export default {
	data(){
		return{
			// 定义登录表单的数据绑定对象
			LoginForm: {
				Username:'',
				Password:''
			},
			// 定义表单的验证规则对象
			LoginFormRules: {
				// 验证用户名是否合法
				Username:[
					{required:true, message:"请输入用户名称", trigger: "blur"},
					{min:3, max:10, message:"长度在3-10个字符", trigger: "blur"}
				],
				// 验证密码是否合法
				Password:[
					{required:true, message:"请输入密码", trigger: "blur"},
					{min:7, max:20, message:"长度在7-20个字符", trigger: "blur"}
				]
			}
		};
	},
	methods: {
		// 点击重置按钮，重置登录表单
		ResetLoginForm(){
			this.$refs.LoginFormRef.resetFields();
		},
		Login(){
			this.$refs.LoginFormRef.validate(async valid =>{
				if (!valid) return
				        const params = new URLSearchParams()
						// 根据API调整(已调，待测试)
				        params.append('username', this.LoginForm.Username)
				        params.append('password', this.LoginForm.Password)
				        const res = await this.$http.post('Login', params)
				        console.log(res)
				        if (res.data.statusCode !== 200) return this.$message.error('res.data.msg')
				        console.log(res)
						
						// 保存并上传token（根据API调整————已调，待测试）
				        window.sessionStorage.setItem('token', res.data.token)
				        this.$message.success('登录成功！')
						
						// 页面跳转
				        this.$router.push('/admin')
			})
		}
	}
}
	
</script>

<style lang="less" scoped>
	.login_container{
		background-color: #434343;
		height: 100%;
	}
	.login_box{
		width: 500px;
		height: 350px;
		background-color: #ffffff;
		border-radius: 5px;
		box-shadow: 10px 10px 10px 1px #8e8e8e;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
	.avatar_box{
		width: 110px;
		height: 110px;
		border:1px solid #eee;
		border-radius: 50%;
		padding: 7px;
		box-shadow: 0 0 10px #eee;
		background-color: #fff;
		position: absolute;
		left: 50%;
		transform: translate(-50%, -50%);
	}
	.avatar_box img{
		width: 100%;
		height: 100%;
		border-radius: 50%;
		background-color: #eee;
	}
	.login_form{
		position: absolute;
		bottom: 5%;
		width: 100%;
		height: 200px;
		padding: 0 20px;
		box-sizing: border-box;
	}
	.btns{
		display: flex;
		justify-content: flex-end;
	}
</style>
