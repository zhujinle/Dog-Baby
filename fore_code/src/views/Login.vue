<template>
    <div class="login-container">
        <el-form ref="loginForm" :model="loginForm" class="login-form">
            <h2 class="login-title">Login</h2>
            <el-form-item label="Username" prop="username">
                <el-input v-model="loginForm.username" placeholder="Enter your username"></el-input>
            </el-form-item>
            <el-form-item label="Password" prop="password">
                <el-input type="password" v-model="loginForm.password" placeholder="Enter your password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="login">Login</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            loginForm: {
                username: '',
                password: ''
            }
        };
    },
    methods: {
        login() {
            // Perform login request to 127.0.0.1/api/v1/login using this.loginForm.username and this.loginForm.password
            // Handle the response accordingly
        }
    }
};
</script>

<style scoped>
.login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.login-form {
    width: 400px;
    padding: 20px;
    background-color: #fff;
    border-radius: 4px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.login-title {
    text-align: center;
    margin-bottom: 20px;
}
</style>
