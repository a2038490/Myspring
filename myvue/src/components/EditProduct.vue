<template>
    <div>
      <h2>編輯商品</h2>
      <form @submit.prevent="updateproduct">
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
          <label for="feerate">手續費:</label>
          <input type="number" v-model="feerate" required>
        </div>
        <button type="submit">編輯完成</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import { useRouter, useRoute } from 'vue-router';
  
  export default {
    data() {
      return {
        productname: '',
        price: 0,
        num: 0,
        feerate: 0,
        userid: null,
      };
    },
    setup() {
      const router = useRouter();
      const route = useRoute();
      const no=route.params.no;
      return { router, route,no };
    },
    mounted() {
      this.viewuserid();
      this.viewproduct();
    },
    methods: {
      viewuserid() {
        const user = JSON.parse(sessionStorage.getItem('user'));
        if (user) {
          this.userId = user.id;
        }
      },
      async viewproduct() {
        try {
          const response = await axios.get(`http://localhost:8080/api/products/${this.no}`);
          const product = response.data;
          this.productname = product.productname;
          this.price = product.price;
          this.num = product.num;
          this.feerate = product.feerate;
        } catch (error) {
          console.error('Error viewing product:', error);
        }
      },
      async updateproduct() {
        try {
          await axios.put(`http://localhost:8080/api/products/${this.no}`, {
            productname: this.productname,
            price: this.price,
            num: this.num,
            feerate: this.feerate,
            userid: this.userid,
          });
          this.router.push('/');
        } catch (error) {
          console.error('Error updating product:', error);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  </style>
  