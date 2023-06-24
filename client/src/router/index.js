import VueRouter from "vue-router";
import Vue from "vue";
import Register from '../components/Register.vue'
import Login from '../components/Login.vue'
import Forget from '../components/Forget.vue'
import Mainpage from '../components/Mainpage.vue'

Vue.use(VueRouter)

const router = new VueRouter({
    routes:[
        {path: '/', redirect: "/login"},
        {path: '/register', component: Register}, 
        {path: '/login', component: Login}, 
        {path: '/forget', component: Forget}, 
        {path: '/mainpage', component: Mainpage}, 
    ]
})

export default router