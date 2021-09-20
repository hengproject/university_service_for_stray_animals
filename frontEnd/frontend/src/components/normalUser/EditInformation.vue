<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>普通用户菜单</el-breadcrumb-item>
      <el-breadcrumb-item>修改账户信息</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <!--      修改用户信息-->
      <el-row type="flex" justify="end">
        <el-col :span="6">
          <el-button
            type="primary"
            icon="el-icon-edit"
            @click="editDialogVisible = true"
            >修改用户信息</el-button
          >
        </el-col>
      </el-row>
      <!--      展现用户信息-->
      <el-row type="flex" justify="space-between">
        <el-col :span="7" class="label"
          ><div><p>姓名</p></div></el-col
        >
        <el-col :span="17"
          ><div class="content">
            <p>{{ this.editData.staffName }}</p>
          </div></el-col
        >
      </el-row>
      <el-row type="flex" justify="space-between">
        <el-col :span="7" class="label"
          ><div><p>密码</p></div></el-col
        >
        <el-col :span="17"
          ><div class="content" @click="showPassword = !showPassword">
            <p>{{ showPassword ? this.editData.password : "点击显示密码" }}</p>
          </div></el-col
        >
      </el-row>
      <el-row type="flex" justify="space-between">
        <el-col :span="7" class="label"
          ><div><p>性别</p></div></el-col
        >
        <el-col :span="17"
          ><div class="content">
            <p>{{ this.editData.staffSex == 0 ? "男" : "女" }}</p>
          </div></el-col
        >
      </el-row>
      <el-row type="flex" justify="space-between">
        <el-col :span="7" class="label"
          ><div><p>年龄</p></div></el-col
        >
        <el-col :span="17"
          ><div class="content">
            <p>{{ this.editData.staffAge }}</p>
          </div></el-col
        >
      </el-row>
      <el-row type="flex" justify="space-between">
        <el-col :span="7" class="label"
          ><div><p>电话号码</p></div></el-col
        >
        <el-col :span="17"
          ><div class="content">
            <p>{{ this.editData.phoneNum }}</p>
          </div></el-col
        >
      </el-row>
      <el-row type="flex" justify="space-between">
        <el-col :span="7" class="label"
          ><div><p>qq号码</p></div></el-col
        >
        <el-col :span="17"
          ><div class="content">
            <p>{{ this.editData.qq }}</p>
          </div></el-col
        >
      </el-row>
    </el-card>
    <el-dialog title="修改信息" :visible.sync="editDialogVisible" width="30%">
      <el-form :model="editDataForm" ref="editFormRef" :rules="editFormRules">
        <el-form-item label="姓名" prop="staffName">
          <el-input
            v-model="editDataForm.staffName"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="editDataForm.password"
            placeholder="请输入密码"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="staffSex">
          <el-select v-model="editDataForm.staffSex">
            <el-option label="男" value="0"></el-option>
            <el-option label="女" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="qq" prop="qq">
          <el-input
            v-model="editDataForm.qq"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item label="电话号" prop="phoneNum">
          <el-input
            v-model="editDataForm.phoneNum"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editDataFormCommit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import deepclone from "@/components/utils/deepclone";
import respFilter from "@/components/utils/respFilter";
export default {
  name: "EditInformation",
  data() {
    return {
      editDataForm: {},
      editData: {},
      showPassword: false,
      editDialogVisible: false,
      editFormRules: {
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
    this.getEditUserData();
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    async getEditUserData() {
      let { data: resp } = await this.$http.post("/normal_user_get_edit_data");
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
      }
      this.editData = deepclone(resp.data);
      this.editDataForm = deepclone(resp.data);
    },
    async editDataFormCommit() {
      let { data: resp } = await this.$http.post(
        "/normal_user_edit_user_data",
        this.editDataForm
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
      } else {
        this.$message.success("修改成功");
        this.editDialogVisible = false;
      }
      await this.getEditUserData();
    },
  },
};
</script>

<style scoped lang="less">
.label {
  text-align: center;
  background: rgb(250, 250, 250);
}
.content {
  background: rgb(255, 255, 255);
  text-align: center;
}
</style>
