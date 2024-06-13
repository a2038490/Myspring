<template>
    <div>
      <h2>新增偏好商品頁面</h2>
      <form @submit.prevent="addproduct">
        <div>
          <label for="productname">商品名稱:</label>
          <input type="text" v-model="productname" required>
        </div>
        <div>
          <label for="price">價格:</label>
          <input type="number" v-model="price" required>
        </div>
        <div>
          <label for="num">數量:</label>
          <input type="number" v-model="num" required>
        </div>
        <div>
          <label for="feerate"> 手續費:</label>
          <input type="number" v-model="feerate" required>
        </div>
        <button type="submit">新增</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import {useRouter} from 'vue-router';
  export default {
    data() {
      return {
        productname: '',
        price: 0,
        num: 0,
        feerate: 0,
        userid:null
      };
    },
    setup() {
        const router=useRouter();
        return {router};
    },
    mounted() {
        this.viewuserid();
    },
    methods: {  
      async addproduct() {
        try {
          const response = await axios.post('http://localhost:8080/api/products', {
            productname: this.productname,
            price: this.price,
            num: this.num,
            feerate: this.feerate,
            userid:this.userid
          });
          console.log('Product added successfully', response.data);
          this.router.push('/');
        } catch (error) {
          console.error('Error adding Product:', error);
        }
      },
      viewuserid() {
            const user =JSON.parse(sessionStorage.getItem('user'));
            if(user){
                this.userid=user.id;
            }
        },
    }
  };
  </script>
  
  <style scoped>

  </style>
  