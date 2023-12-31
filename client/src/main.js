import Vue from 'vue'
import App from './App.vue'
import router from './router/index.js'
import ElementUI  from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from "axios";

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$http = axios

new Vue({
  render: h => h(App),
  router: router
}).$mount('#app')
