<template>
  <div class="login_container">
    <div class="login_box">
      <!-- logo -->
      <div class="avator_box">
        <img src="../assets/logo.png" alt="" />
      </div>

      <!-- 登录表单 -->
      <div>
        <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="loginFormRules"
          class="login_form"
        >
          <!-- 用户名 -->
          <el-form-item prop="username">
            <el-input
              laceholder="请输入用户名"
              v-model="loginForm.username"
              prefix-icon="el-icon-user-solid"
            ></el-input>
          </el-form-item>
          <!-- 密码 -->
          <el-form-item prop="password">
            <el-input
              laceholder="请输入密码"
              v-model="loginForm.password"
              prefix-icon="el-icon-lock"
              type="password"
            ></el-input>
          </el-form-item>
          <!-- 按钮区域 -->
          <el-form-item class="btns">
            <el-button type="primary" @click="login">登录</el-button>
            <el-button type="info" @click="visibility.registerDialog = true"
              >注册</el-button
            >
            <el-button type="info" @click="resetLoginForm">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <el-dialog
      title="添加用户"
      :visible.sync="visibility.registerDialog"
      width="70%"
    >
      <el-form
        :model="registerForm"
        :rules="registerFormRules"
        ref="registerFormRef"
      >
        <el-form-item label="用户名" prop="userName">
          <el-input
            v-model="registerForm.userName"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="passwd">
          <el-input
            v-model="registerForm.password"
            placeholder="请输入密码"
            show-password
          ></el-input>
        </el-form-item>
        <el-button type="primary" @click="registerFormSubmit">注册</el-button>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import respFilter from "@/components/utils/respFilter";

export default {
  data() {
    return {
      loginForm: {
        username: "ysh0",
        password: "200108",
      },
      loginFormRules: {
        //验证用户名是否合法
        username: [
          { required: true, message: "请输入用户名称", trigger: "blur" },
          { min: 4, max: 30, message: "长度在4-30之间", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 30, message: "长度在6-30之间", trigger: "blur" },
        ],
      },
      visibility: {
        registerDialog: false,
      },
      registerForm: {
        userName: "",
        passwd: "",
      },
      registerFormRules: {
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 30, message: "长度在6-30之间", trigger: "blur" },
          {
            pattern: "(?=.*[0-9])(?=.*[a-zA-Z]).{8,30}",
            message: "密码需要包含数字、字母",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields();
    },
    login() {
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return;
        const { data: resp } = await this.$http.post("/login", this.loginForm);
        if (resp.code !== 200) return this.$message.error("登录失败");
        this.$message.success("登录成功");
        window.sessionStorage.setItem("token", resp.data.token);
        this.$router.push("/home");
      });
    },
    async registerFormSubmit() {
      let { data: resp } = await this.$http.post(
        "/register_normal_user",
        this.registerForm
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
      } else {
        this.$message.success("注册成功");
        this.visibility.registerDialog = false;
      }
    },
  },
};
</script>

<style lang="less" scoped>
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}

.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  .avator_box {
    box-sizing: border-box;
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -70%);
    background-color: #fff;

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }

  .btns {
    display: flex;
    justify-content: flex-end;
  }

  .login_form {
    display: block;
    position: absolute;
    bottom: 0;
    box-sizing: border-box;
    width: 100%;
    padding: 0 20px;
  }
}
</style>
