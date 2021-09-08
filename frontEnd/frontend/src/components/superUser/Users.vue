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
        <el-table-column label="#" type="index" align="center">
        </el-table-column>

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
          <el-table-column label="在线状态">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.online"
                @change="userStageChange(scope.row)"
              ></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="">
              <el-button-group>
                <el-tooltip
                  class="item"
                  effect="dark"
                  content="修改用户信息"
                  placement="top"
                  :enterable="false"
                >
                  <el-button
                    type="primary"
                    icon="el-icon-edit"
                    size="mini"
                  ></el-button
                ></el-tooltip>
                <el-tooltip
                  class="item"
                  effect="dark"
                  content="删除该用户(包括员工信息)"
                  placement="top"
                  :enterable="false"
                >
                  <el-button
                    type="danger"
                    icon="el-icon-delete"
                    size="mini"
                  ></el-button
                ></el-tooltip>
              </el-button-group>
            </template>
          </el-table-column>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum + 1"
        :page-sizes="[2, 3, 5, 10]"
        :page-size="queryInfo.pageSize"
        :total="total"
        layout="total,sizes, prev, pager, next, jumper"
      >
      </el-pagination>
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
        this.userList = resp.data.u;
        this.total = resp.data.v.userNum;
      }
    },
    //监听pagesize改变
    handleSizeChange(newPageSize) {
      this.queryInfo.pageSize = newPageSize;
      this.getUserList();
    },
    handleCurrentChange(newPageNum) {
      this.queryInfo.pageNum = newPageNum - 1;
      this.getUserList();
    },
    //监听switch状态开关状态的改变
    async userStageChange(userInfo) {
      let { data: resp } = await this.$http.post("/superuser_login_or_logout", {
        userDto: userInfo.userDto,
        online: userInfo.online,
      });
      if (resp.code === 403 || resp.code === 401) {
        this.$message.error(resp.data.msg);
        return this.logout();
      }
      if (resp.code === 200) {
        this.$message.success("修改成功");
      }
    },
  },
};
</script>

<style scoped>
.el-button-group {
  display: flex;
  justify-content: space-between;
}
</style>
