import Vue from 'vue';
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/en'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css'
import App from './App.vue';
import VueRouter from "vue-router";
import router from "./router";
import axios from "axios";
Vue.prototype.$axios=axios;

Vue.use(ElementUI,{ locale });
Vue.use(VueRouter);

new Vue({
  router,
  el: '#app',
  render: h => h(App)
});