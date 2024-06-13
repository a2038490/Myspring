<template>
    <div class="login">
        <h2>歡迎登入</h2>
        <form @submit.prevent="login">
            <div>
                <label for="username">帳號</label>&nbsp;&nbsp;
                <input type="text" v-model="username" id="username" required>
            </div>
            <br><br>
            <div>
                <label for="password">密碼</label>&nbsp;&nbsp;
                <input type="text" v-model="password" id="password" required>
            </div>
            <br><br>
            <button type="submit">登入</button>
        </form>
        <br><br>
        <router-link to="/register" class="button">註冊</router-link>
        <br><br>
        <div v-if="success" class="success">{{ success }}</div>
        <div v-else class="error">{{ error }}</div>
    </div>
</template>

<script>
import axios from 'axios'; 
import {useRouter} from 'vue-router';
export default {
    data() {
        return {
            username: '',
            password: '',
            error: '',
            success:''
        };
    },
    setup() {
        const router = useRouter();
        return {router};
    },
    methods: {
        async login() {
            try {
                const response =await axios.post('http://localhost:8080/api/users/login', {
                        username: this.username,
                        password: this.password
                    }
                );
                console.log('Login successful', response.data);
                this.success='登入成功';
                this.error='';
                sessionStorage.setItem('user',JSON.stringify(response.data));
                this.router.push('/');
            }
            catch(error) {
                if(error.response && error.response.status === 401) {
                    this.error = 'Invalid username or password';
                    this.success='';
                }
                else {
                    this.error = 'An error occured';
                    this.success='';
                }
            }
        },
    }
};
</script>

<style scoped>
.login {
    max-width: 300px;
    margin: auto;
    padding: 1em;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.error{
    color:red;
    margin-top: 1em;
}

.success {
    color:green;
    margin-top: 1em;
}

.botton{
    display: inline-block;
    margin-top: 1em;
    padding: 0.5em 1em;
    background-color: #007bff;
    color: white;
    text-align: center;
    text-decoration: none;
    border-radius: 4px;
}

</style>