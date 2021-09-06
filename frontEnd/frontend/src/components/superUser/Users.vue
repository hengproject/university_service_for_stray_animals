<template>
  <div>
    <!--  面包屑导航区-->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>超级用户菜单</el-breadcrumb-item>
      <el-breadcrumb-item>修改用户信息</el-breadcrumb-item>
    </el-breadcrumb>

    <!--    卡片视图区域-->
    <el-card>
      <el-row :gutter="20">
        <!--        搜索与添加区域-->
        <el-col :span="7">
          <el-input placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary">添加用户</el-button>
        </el-col>
      </el-row>
      <el-table :data="userList" border stripe>
        <el-table-column label="#" type="index" align="center"> </el-table-column>

        <el-table-column label="用户管理" align="center">
          <el-table-column
            label="用户ID"
            prop="userLogin.userId"
          ></el-table-column>
          <el-table-column
            label="用户名称"
            prop="userLogin.username"
          ></el-table-column>
          <el-table-column
            label="用户密码"
            prop="userLogin.password"
          ></el-table-column>
          <el-table-column
            label="用户组"
            prop="userDto.userGroupEnum"
          ></el-table-column>
        </el-table-column>

        <el-table-column label="操作员管理" align="center">
          <el-table-column
            label="操作员ID"
            prop="staffDto.staffId"
          ></el-table-column>
          <el-table-column
            label="操作员ID"
            prop="staffDto.staffId"
          ></el-table-column>
          <el-table-column
            label="操作员姓名"
            prop="staffDto.staffName"
          ></el-table-column>
          <el-table-column
            label="操作员类别"
            prop="staffDto.staffIdentityEnum"
          ></el-table-column>
        </el-table-column>
        <el-table-column label="具体操作" align="center">
          <el-table-column label="状态"></el-table-column>
          <el-table-column label="操作"></el-table-column>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //  获取客户列表参数对象
      queryInfo: {
        pageNum: 0,
        pageSize: 2,
      },
      userList: [],
      total: 0,
    };
  },
  created() {
    this.getUserList();
  },
  methods: {
    async getUserList() {
      let { data: resp } = await this.$http.post(
        "/superuser_getUserList",
        this.queryInfo
      );
      if (resp.code === 403 || resp.code === 401)
        return this.$message.error(resp.data.msg);
      if (resp.code === 200) {
        console.log(resp.data);
        this.userList = resp.data;
        this.total = resp.data.length;
      }
    },
  },
};
</script>

<style scoped></style>
