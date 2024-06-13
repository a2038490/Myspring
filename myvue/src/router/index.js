import { createRouter,createWebHistory} from 'vue-router'
import Login from '../components/UserLogin.vue'
import Register from '../components/RegisterPage.vue'
import Wishlist from '../components/WishlistPage.vue'
import Addproduct from '../components/AddProduct.vue'
import Editproduct from '../components/EditProduct.vue'

const routes = [
    { path: '/', component: Wishlist, meta: { requiresAuth: true } },
    { path: '/addproduct', component: Addproduct, meta: { requiresAuth: true } },
    { path: '/editproduct/ :no', name: 'editproduct', component: Editproduct, meta: { requiresAuth: true } },
    { path: '/login', component: Login },
    { path: '/register', component: Register },
  ];
  
  const router = createRouter({
    history: createWebHistory(),
    routes,
  });
  
  router.beforeEach((to, from, next) => {
    const loggedIn = JSON.parse(sessionStorage.getItem('user'));
  
    if (to.matched.some(record => record.meta.requiresAuth) && !loggedIn) {
      next('/login');
    } else {
      next();
    }
  });
  
  export default router;
  