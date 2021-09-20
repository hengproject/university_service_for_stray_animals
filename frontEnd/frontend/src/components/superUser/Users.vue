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
      <!--        搜索与添加区域-->
      <el-row :gutter="20">
        <el-col :span="7">
          <el-input placeholder="请输入内容" v-model="queryString" clearable>
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getUserListLike"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true"
            >添加用户</el-button
          >
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
            <template slot-scope="scope">
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
                    @click="showEditDialog(scope.row)"
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
                    @click="deleteUser(scope.row)"
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

    <!--    添加用户对话框-->
    <el-dialog
      title="添加用户"
      :visible.sync="addDialogVisible"
      width="30%"
      :before-close="handleDialogClose"
    >
      <!--      内容主题区-->

      <el-form :model="addForm" ref="addFormRef" :rules="addFormRules">
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="addForm.username"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item label="用户密码" prop="password">
          <el-input
            v-model="addForm.password"
            show-password
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="用户组" prop="userGroup">
          <el-select v-model="addForm.userGroup">
            <el-option label="普通用户" value="NORMAL_USER"></el-option>
            <el-option label="超级用户" value="SUPER_USER"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="操作员姓名" prop="staffName">
          <el-input
            v-model="addForm.staffName"
            placeholder="请输入操作员姓名"
          ></el-input>
        </el-form-item>
        <el-form-item label="操作员类别" prop="userGroup">
          <el-select v-model="addForm.staffIdentity">
            <el-option label="普通用户" value="NORMAL_USER"></el-option>
            <el-option label="管理员" value="MANAGER"></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="closeAddForm">取 消</el-button>
        <el-button type="primary" @click="addFormSubmit">确 定</el-button>
      </span>
    </el-dialog>
    <!--    修改用户对话框-->
    <el-dialog title="修改用户" :visible.sync="editDialogVisible" width="30%">
      <!--      内容主题区-->

      <el-form :model="editForm" ref="editFormRef" :rules="editFormRules">
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="editForm.userName"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item label="用户密码" prop="password">
          <el-input
            v-model="editForm.password"
            show-password
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="用户组" prop="userGroup">
          <el-select v-model="editForm.userGroup">
            <el-option label="普通用户" value="NORMAL_USER"></el-option>
            <el-option label="超级用户" value="SUPER_USER"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="操作员姓名" prop="staffName">
          <el-input
            v-model="editForm.staffName"
            placeholder="请输入操作员姓名"
          ></el-input>
        </el-form-item>
        <el-form-item label="操作员类别" prop="userGroup">
          <el-select v-model="editForm.staffIdentity">
            <el-option label="普通用户" value="NORMAL_USER"></el-option>
            <el-option label="管理员" value="MANAGER"></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editUserSubmit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //  获取客户列表参数对象
      queryInfo: {
        query: "",
        pageNum: 0,
        pageSize: 2,
      },
      queryString: "",
      userList: [],
      total: 0,
      addDialogVisible: false,
      // 添加用户的表单类型
      addForm: {
        username: "",
        password: "",
        userGroup: "",
        staffName: "",
        staffIdentity: "",
      },
      addFormRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 4, max: 30, message: "长度在4-30之间", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 30, message: "长度在6-30之间", trigger: "blur" },
        ],
        staffName: [
          { required: true, message: "请输入操作员姓名", trigger: "blur" },
          { min: 2, max: 30, message: "长度在6-30之间", trigger: "blur" },
        ],
      },
      editDialogVisible: false,
      editForm: {
        userId: "",
        userName: "",
        password: "",
        userGroup: "",
        staffId: "",
        staffName: "",
        staffIdentity: "",
      },
      editFormRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 4, max: 30, message: "长度在4-30之间", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 30, message: "长度在6-30之间", trigger: "blur" },
        ],
        staffName: [
          { required: true, message: "请输入操作员姓名", trigger: "blur" },
          { min: 2, max: 30, message: "长度在2-30之间", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getUserList();
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    async getUserList() {
      let { data: resp } = await this.$http.post(
        "/superuser_getUserList",
        this.queryInfo
      );
      if (resp.code === 403 || resp.code === 401) {
        this.$message.error(resp.msg);
        this.logout();
      }
      if (resp.code === 200) {
        this.userList = resp.data.u;
        this.total = resp.data.v.userNum;
      }
    },
    getUserListLike() {
      this.queryInfo.query = this.queryString;
      this.queryInfo.pageNum = 0;
      this.getUserList();
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
        this.$message.error(resp.msg);
        return this.logout();
      }
      if (resp.code === 200) {
        this.$message.success("修改成功");
      }
    },
    handleDialogClose() {
      this.addDialogVisible = false;
    },
    async addFormSubmit() {
      const { data: resp } = await this.$http.post(
        "/superuser_add_user",
        this.addForm
      );
      if (resp.code === 403 || resp.code === 401) {
        this.$message.error(resp.msg);
        return this.logout();
      }
      if (resp.code !== 200) return this.$message.error(resp.msg);
      if (resp.code === 200) {
        this.$message.success("添加成功");
        this.clearAddForm();
        this.getUserList();
      }
      this.addDialogVisible = false;
    },
    closeAddForm() {
      this.clearAddForm();
      this.addDialogVisible = false;
    },
    clearAddForm() {
      this.addForm = {
        username: "",
        password: "",
        userGroup: "",
        staffName: "",
        staffIdentity: "",
      };
    },
    async deleteUser(rowInfo) {
      const { data: resp } = await this.$http.post(
        "/superuser_delete_user",
        rowInfo
      );
      if (resp.code === 403 || resp.code === 401) {
        this.$message.error(resp.msg);
        return this.logout();
      }
      if (resp.code !== 200) return this.$message.error(resp.msg);
      if (resp.code === 200) {
        this.$message.success("删除成功");
        this.getUserList();
      }
    },
    //修改表单打开
    showEditDialog(rowInfo) {
      this.editDialogVisible = true;
      this.editForm = {
        userId: rowInfo.userLogin.userId,
        userName: rowInfo.userLogin.username,
        password: rowInfo.userLogin.password,
        userGroup: rowInfo.userDto.userGroupEnum,
        staffId: rowInfo.staffDto.staffId,
        staffName: rowInfo.staffDto.staffName,
        staffIdentity: rowInfo.staffDto.staffIdentityEnum,
      };
    },
    async editUserSubmit() {
      const { data: resp } = await this.$http.post(
        "/superuser_modify_user",
        this.editForm
      );
      if (resp.code === 403 || resp.code === 401) {
        this.$message.error(resp.msg);
        return this.logout();
      }
      if (resp.code !== 200) return this.$message.error(resp.msg);
      if (resp.code === 200) {
        this.$message.success("修改成功");
        await this.getUserList();
        this.editDialogVisible = false;
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
