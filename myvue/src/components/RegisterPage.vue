<template>
    <div class="register">
        <h2>註冊</h2>
        <form @submit.prevent="register">
            <div>
                <label for="username">帳號:</label>&nbsp;&nbsp;
                <input type="text" v-model="username" id="username" required>
            </div>
            <br><br>
            <div>
                <label for="password">密碼:</label>&nbsp;&nbsp;
                <input type="text" v-model="password" id="password" required>
            </div>
            <br><br>
            <div>
                <label for="name">名稱:</label>&nbsp;&nbsp;
                <input type="text" v-model="name" id="name" required>
            </div>
            <br><br>
            <div>
                <label for="email">Email:</label>&nbsp;&nbsp;
                <input type="text" v-model="email" id="email" required>
            </div>
            <br><br>
            <div>
                <label for="account">銀行帳號:</label>&nbsp;&nbsp;
                <input type="text" v-model="account" id="account" required>
            </div>
            <br><br>
            <button type="submit">註冊</button>
        </form>
        <br><br>
        <router-link to="/login">登入</router-link>
        <br><br>
        <div v-if="success" class="success">{{ success }}</div>
        <div v-else class="error">{{ error }}</div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    data() {
        return {
            username: '',
            password: '',
            name:'',
            email:'',
            account:'',
            error: '',
            success:''
        };
    },
    methods: {
        async register() {
            try {
                const response =await axios.post('http://localhost:8080/api/users/register', {
                        username: this.username,
                        password: this.password,
                        name: this.name,
                        email: this.email,
                        account:this.account
                    }
                );
                console.log('register successful', response.data);
                this.success='註冊成功';
                this.error='';
                setTimeout(() => {
                    this.$router.push('/login');
                },3000);
            }
            catch(error) {
                if(error.response && error.response.status === 409) {
                    this.error = 'Username or Password already exists';
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

.error {
    color:red;
    margin-top: 1em;
}

.success {
    color:green;
    margin-top: 1em;
}

</style>