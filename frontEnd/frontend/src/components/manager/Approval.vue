<template>
  <div>
    <!--  面包屑导航区-->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>管理员菜单</el-breadcrumb-item>
      <el-breadcrumb-item>审批</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-table :data="list.questionList" stripe style="width: 100%">
        <el-table-column  label="ID"><template #default="scope">{{abbr(scope.row.questionId,4)}}</template></el-table-column>
        <el-table-column  label="问题描述"><template #default="scope">{{abbr(scope.row.questionDescription,5)}}</template></el-table-column>
        <el-table-column  label="问题类型"><template #default="scope">{{getCategory(scope.row.questionCategory)}}</template></el-table-column>
        <el-table-column  label="提出时间"><template #default="scope">{{localTime(scope.row.startTime)}}</template></el-table-column>
        <el-table-column  label="查看问题"><template #default="scope"><el-button type="primary" icon="el-icon-view" @click="showEditDialog(scope.row)"></el-button></template></el-table-column>
      </el-table>
    </el-card>

    <el-dialog title="信息查看" :visible.sync="visibility.questionDialogVisibility" width="70%">
      <el-descriptions class="margin-top" title="带边框列表" :column="1"  border>
        <template slot="extra">
          <el-button type="success" size="small" @click="solved"><i class="el-icon-check"></i> 已解决</el-button>
        </template>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-paperclip"></i>
            问题ID
          </template>
          {{form.questionShownForm.questionId}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-paperclip"></i>
            问题分类
          </template>
          {{getCategory(form.questionShownForm.questionCategory)}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-watch"></i>
            提交时间
          </template>
          {{localTime(form.questionShownForm.startTime)}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-question"></i>
            问题描述
          </template>
          {{form.questionShownForm.questionDescription}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-info"></i>
            问题备注
          </template>
          {{form.questionShownForm.questionDescription}}
        </el-descriptions-item>
      </el-descriptions>

    </el-dialog>
  </div>
</template>

<script>
import respFilter from "@/components/utils/respFilter";
import getLocalTime from "@/components/utils/getLocalTime";

export default {
  name: "Approval",
  data() {
    return {
      queryInfo: {
        query: "",
        pageNum: 0,
        pageSize: 2,
      },
      list: {
        questionList: [],
      },
      form:{
        questionShownForm:{},
      },
      visibility:{
        questionDialogVisibility: false,
      }
    };
  },
  created() {
    this.init();
  },
  methods: {
    async init() {
      let { data: resp } = await this.$http.post(
        "/manager_get_question_unresolved_info",
        this.queryInfo
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
      }
      this.list.questionList = resp.data;
    },
    abbr(string,len){
      if(string.length<=2*len) return string+len;
      return string = string.substr(0,len+2)+"..."+string.substr(-(len-3),len-3)
    },
    getCategory(enumCategory){
      switch (enumCategory){
        case 1:
          return "猫咪信息更正";
        default:
          return "其他";
      }
},
    localTime: function (ns){
      return getLocalTime(ns);
    },
    async showEditDialog(info){
      let { data: resp } =await this.$http.post("/manager_set_question_seen",info.questionId);
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.visibility.questionDialogVisibility = true;
      this.form.questionShownForm = info;
    },
    async solved(){
      let { data: resp } =await this.$http.post("/manager_set_question_resolved",this.form.questionShownForm.questionId)
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.$message.success("处理成功");
      this.visibility.questionDialogVisibility = false;
      this.init();
    }
  },
};
</script>

<style scoped></style>
