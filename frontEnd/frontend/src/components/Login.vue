<template>
  <div class="login_container">
    <div class="login_box">
      <!-- logo -->
      <div class="avator_box">
        <img src="../assets/logo.png" alt="" />
      </div>

      <!-- 登录表单 -->
      <div>
        <el-form :model="loginForm" class="login_form">
          <!-- 用户名 -->
          <el-form-item>
            <el-input
              v-model="loginForm.username"
              prefix-icon="el-icon-user-solid"
            ></el-input>
          </el-form-item>
          <!-- 密码 -->
          <el-form-item>
            <el-input
              v-model="loginForm.password"
              prefix-icon="el-icon-lock"
              type="password"
            ></el-input>
          </el-form-item>
          <!-- 按钮区域 -->
          <el-form-item class="btns">
            <el-button type="primary">登录</el-button>
            <el-button type="info">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
      },
    };
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
