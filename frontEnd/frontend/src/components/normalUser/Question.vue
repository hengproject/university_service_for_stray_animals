<template>
  <div>
    <!--  面包屑导航区-->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>普通用户菜单</el-breadcrumb-item>
      <el-breadcrumb-item>申请流程查看</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <div
        class="item"
        v-for="question in questionList"
        :key="question.questionId"
      >
        <div
          class="abbr"
        >
          <i class="el-icon-arrow-right" ref="question.questionId"></i>
          <p class="description">{{ question.questionDescription }}</p>
          <p class="status">
            {{ question.questionStatus == 3 ? "已解决" : "正在解决流程中" }}
          </p>
        </div>
        <div>
          <div class="specific">
            <el-steps
              :space="200"
              :active="question.questionStatus + 1"
              finish-status="success"
            >
              <el-step
                :title="`已提交${getLocalTime(question.startTime)}`"
              ></el-step>
              <el-step title="等待审核中"></el-step>
              <el-step title="审核中"></el-step>
              <el-step title="已处理" v-if="question.questionStatus != 3"></el-step>
              <el-step :title="`已处理${getLocalTime(question.endTime)}`" v-else></el-step>
            </el-steps>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import getLocalTime from "@/components/utils/getLocalTime";
import respFilter from "@/components/utils/respFilter";
export default {
  name: "Question",
  data() {
    return {
      questionList: [],
    };
  },
  created() {
    this.init();
  },
  methods: {
    async init() {
      let { data: resp } = await this.$http.post("/find_question_by_user_id");
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.questionList = resp.data;
    },
    getLocalTime: (ns) => getLocalTime(ns),
  },
};
</script>

<style scoped lang="less">
.item {
  .abbr {
    margin: 0 1vw;
    display: flex;
    justify-content: space-between;
    height: 3vh;
    box-sizing: border-box;
    padding: 0 0 15vh 0;
    i {
      line-height: 3vh;
      font-size: 100%;
    }
    p {
      margin: 0;
      padding: 0;
      height: 3vh;
      line-height: 3vh;
      font-size: 100%;
    }
  }
  .specific {
    display: none;
  }
}

.item:hover {
  .specific {
    display: block;
    margin: 5vh 0 0 50vw;
    width: 100%;
    transform: translateX(-30%);
  }
}

* {
  transition: ease-in-out;
}
</style>
