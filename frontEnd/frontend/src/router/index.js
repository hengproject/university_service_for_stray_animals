import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../components/Login.vue";
import Home from "../components/Home";
import Users from "@/components/superUser/Users";
import EditInformation from "@/components/normalUser/EditInformation";
import ModifyBasicInformation from "@/components/manager/ModifyBasicInformation";
import Test from "@/components/Test";
import CatInfo from "@/components/manager/CatInfo";
import RecordAppearance from "@/components/normalUser/RecordAppearance";
import CatCard from "@/components/normalUser/CatCard";
import Welcome from "@/components/Welcome";
import Question from "@/components/normalUser/Question";
import Approval from "@/components/manager/Approval";

Vue.use(VueRouter);

const routes = [
  { path: "/", redirect: "/login" },
  { path: "/login", component: Login },
  {
    path: "/home",
    component: Home,
    redirect: "/welcome",
    children: [
      { path: "/welcome", component: Welcome},
      { path: "/users", component: Users },
      { path: "/editInformation", component: EditInformation },
      { path: "/catCard", component: CatCard },
      { path: "/modifyBasicInformation", component: ModifyBasicInformation },
      { path: "/test", component: Test },
      { path: "/catInfo", component: CatInfo },
      { path: "/recordAppearance", component: RecordAppearance },
      { path: "/questions", component: Question },
      { path: "/approval", component: Approval },

    ],
  },
];

const router = new VueRouter({
  routes,
});
router.beforeEach((to, from, next) => {
  //  to表示将要访问的路径
  //  from表示从那个路出发
  //   next()表示放行
  if (to.path === "/login") return next();
  const tokenStr = window.sessionStorage.getItem("token");
  if (!tokenStr) return next("/login");
  next();
});

export default router;
