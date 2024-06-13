<template>
    <div>
      <nav>
        <div></div>
      </nav>
      <h2>喜好商品清單</h2>
      <table>
        <thead>
          <tr>
            <th>商品名稱</th>
            <th>價格</th>
            <th>數量</th>
            <th>手續費率</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in products" :key="product.no">
            <td>{{ product.productname }}</td>
            <td>{{ product.price }}</td>
            <td>{{ product.num }}</td>
            <td>{{ product.feerate }}</td>
            <td>
              <button @click="deleteproduct(product.no)">刪除</button>
              <button @click="editproduct(product.no)">編輯</button>
            </td>
          </tr>
        </tbody>
      </table>
      <br>
      <router-link to="/addproduct">新增商品</router-link>
      <br><br>
  
      <h2>願望清單總攬</h2>
      <table v-if="wishlist">
        <thead>
          <tr>
            <th>扣款帳號</th>
            <th>總數量</th>
            <th>總金額</th>
            <th>總手續費</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{ wishlist.account }}</td>
            <td>{{ wishlist.ordernum }}</td>
            <td>{{ Number(wishlist.totalAmount).toFixed(2) }}</td>
            <td>{{ Number(wishlist.totalFee).toFixed(2) }}</td>
          </tr>
        </tbody>
      </table>
      <div v-else>
        <p>加載清單中...</p>
      </div>
    </div>
  </template>
  

<script>
import axios from 'axios';
import { useRouter, useRoute } from 'vue-router';
export default {
    data() {
        return {
            products:[],
            userid:null,
            wishlist: null
        };
    },
    setup() {
      const router = useRouter();
      const route = useRoute();
      return { router, route };
    },
    mounted()
    {
        this.viewuserid();
        this.viewproduct();
        this.viewwishlist();
    },
    methods: {
        async viewproduct() {
            try {
                const response =await axios.get('http://localhost:8080/api/products',{
                    params:{
                        userid:this.userid
                    }
                });
                this.products=response.data;
            }
            catch(error) {
                console.error("Error viewing products:",error);
            }
        },

        async viewuserid() {
            const user =JSON.parse(sessionStorage.getItem('user'));
            if(user){
                this.userid=user.id;
            }
        },

        async viewwishlist() {
            try {
                const response = await axios.get(`http://localhost:8080/api/wishlists/${this.userid}`)
                this.wishlist=response.data;
                this.wishlist.ordernum=this.calculateNum();
                this.wishlist.totalFee=this.calculateTotalfee();
                this.wishlist.totalAmount=this.calculateTotalamout();
                this.updateWishlist();
            }
            catch(error)
            {
                console.error("Error viewing wishlist:",error);
            }
        },

        async deleteproduct(no) {
            try {
                await axios.delete(`http://localhost:8080/api/products/${no}`);
                this.products=this.products.filter(product => product.no !== no);
                console.log('Product deleted successfully');
                this.viewwishlist();
            }
            catch(error) {
                console.error("Error deleting product:",error);
            }
        },

        editproduct(no) {
            const encodedNo = encodeURIComponent(no);
            this.$router.push({ name: 'editproduct', params: { no: encodedNo}});
        },

        async updateWishlist(){
            try{
                const response= await axios.put(`http://localhost:8080/api/wishlists/${this.userid}`,this.wishlist);
                this.wishlist=response.data;
            }
            catch(error)
            {
                console.error("Error updating wishlist:", error);
            }
        },

        calculateNum(){
            let total=0;
            for(let product of this.products)
            {
                total+=product.num;
            }
            return total;
        },

        calculateTotalfee(){
            let total=0.0;
            for(let product of this.products)
            {
                total+=product.feerate;
            }
            return total;
        },

        calculateTotalamout(){
            let total=0.0;
            for(let product of this.products)
            {
                total+=product.num*product.price;
            }
            return total;
        }


    }
};
</script>

<style scoped>
table {
  width: 80%;
  margin: 0 auto;
  border-collapse: collapse;
  text-align: center;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
}

th {
  background-color: #f2f2f2;
}

tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

tbody tr:hover {
  background-color: #ddd;
}

h2 {
  text-align: center;
}
</style>
