import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "./plugins/element";
import "./assets/css/global.css";
import axios from "axios";
import config from "./config"

Vue.prototype.$http = axios;
axios.defaults.baseURL = config.Http.baseUrl;
axios.defaults.crossDomain = true;
axios.interceptors.request.use((config) => {
  config.headers.Authorization = window.sessionStorage.getItem("token");
  return config;
});
Vue.config.productionTip = false;

new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");

