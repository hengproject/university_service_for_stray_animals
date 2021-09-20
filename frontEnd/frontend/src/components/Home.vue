<template>
  <el-container class="home-container">
    <!--      头部区域-->
    <el-header>
      <div><img src="../assets/logo.png" class="logo" /></div>
      <p class="header-text">流浪动物管理系统</p>
      <el-button type="info" @click="this.$logout()">退出</el-button></el-header
    >

    <el-container>
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <!--        折叠展开-->
        <div class="toggle-button" @click="toggleCollapse">|||</div>

        <!--        侧边栏菜单区-->
        <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          unique-opened
          :collapse="isCollapse"
          :collapse-transition="false"
          router
        >
          <!--  一级菜单-->
          <el-submenu
            :index="item._id"
            v-for="item in menuList"
            :key="item._id"
          >
            <!--  一级菜单模板区-->
            <template slot="title">
              <!-- 图标-->
              <i class="el-icon-user-solid"></i>
              <!--文本-->
              <span>{{ item._authName }}</span>
            </template>
            <!--     二级菜单       -->
            <el-menu-item
              v-for="subItem in item._children"
              :key="subItem._id"
              :index="subItem._path"
            >
              <!-- 图标-->
              <i class="el-icon-right"></i>
              <!--文本-->
              <span>{{ subItem._authName }}</span>
            </el-menu-item>
          </el-submenu>
        </el-menu></el-aside
      >
      <el-main><router-view></router-view></el-main>
    </el-container>
  </el-container>
</template>

<script>
import * as auth from "./utils/auth";

export default {
  data() {
    return {
      //  菜单数据
      menuList: [],
      isCollapse: false,
    };
  },
  created() {
    this.getMenuList();
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    async getMenuList() {
      const { data: resp } = await this.$http.get("/menus");
      if (resp.code === 401) {
        this.$message.error(resp.msg);
        this.logout();
      }
      if (resp.code !== 200) return; //this.$message.error(resp.msg)
      let menu = auth.default.getMenuByAuth(resp.data);
      this.menuList = menu;
    },
    toggleCollapse() {
      this.isCollapse = !this.isCollapse;
    },
  },
};
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  .header-text {
    margin-left: 20px;
    font-size: 20px;
  }
  div {
    border-radius: 50%;
    overflow: hidden;
    .logo {
      width: 60px;
      height: 60px;
    }
  }
}
.el-aside {
  background-color: #545c64;
  .el-menu {
    border-right: 0px;
  }
  .toggle-button {
    background-color: #4a5064;
    font-size: 10px;
    line-height: 24px;
    color: #fff;
    text-align: center;
    letter-spacing: 0.2em;
    cursor: pointer;
  }
}
.el-main {
  background-color: #eaedf1;
}
</style>
