import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "./plugins/element";
import "./assets/css/global.css";
import axios from "axios";

Vue.prototype.$http=axios;
axios.defaults.baseURL = "http://localhost:8080";
axios.defaults.crossDomain=true;
Vue.config.productionTip = false;

new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");
