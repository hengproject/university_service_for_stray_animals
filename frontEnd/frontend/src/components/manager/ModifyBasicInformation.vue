<template>
  <div>
    <!--  面包屑导航区-->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>管理员菜单</el-breadcrumb-item>
      <el-breadcrumb-item>修改基础信息</el-breadcrumb-item>
    </el-breadcrumb>
    <!--    卡片视图区域-->
    <el-card>
      <el-table
        :data="campusForm"
        style="width: 100%"
        row-key="campusId"
        border
        lazy
        :load="loadArea"
        :tree-props="{ children: 'son', hasChildren: 'hasSon' }"
      >
        <el-table-column label="校区ID" prop="campusId"></el-table-column>
        <el-table-column label="校区名称" prop="campusName"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-tooltip
              class="item"
              effect="dark"
              content="修改校区信息"
              placement="top"
              :enterable="false"
            >
              <el-button
                type="primary"
                icon="el-icon-edit"
                size="mini"
                @click="showEditCampusDialog(scope.row)"
              ></el-button
            ></el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--    添加campus-->
    <el-dialog
      title="修改校区信息"
      :visible.sync="editCampusDialogVisible"
      width="30%"
    >
      <el-form>
        <el-form-item label="校区ID">
          <br />
          {{ campusEdit.campusId }}
        </el-form-item>
        <el-form-item label="校区名">
          <el-input v-model="campusEdit.campusName" placeholder="请输入校区名">
          </el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editCampusDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmEditCampus">确 定</el-button>
      </span>
    </el-dialog>
    <!--    添加Area-->
  </div>
</template>

<script>
import respFilter from "@/components/utils/respFilter";

export default {
  name: "ModifyBasicInformation",
  data() {
    return {
      campusForm: [],
      editCampusDialogVisible: true,
      campusEdit: {
        campusId: "",
        campusName: "",
        hasSon: true,
      },
    };
  },
  created() {
    this.getFormList();
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    async getFormList() {
      let { data: resp } = await this.$http.post(
        "/manager_get_basic_info_list"
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
      }
      this.campusForm = resp.data.campusList;
    },
    // eslint-disable-next-line no-unused-vars
    async loadArea(tree, treeNode, resolve) {
      let { data: resp } = await this.$http.post(
        "/manager_get_basic_info_list_lazy_get_area",
        tree.campusId
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      resolve(resp.data);
    },
    showEditCampusDialog(row) {
      this.campusEdit.campusId = row.campusId;
      this.campusEdit.campusName = row.campusName;
      this.editCampusDialogVisible = true;
    },
    async confirmEditCampus() {
       let{data:resp}= await this.$http.post(
        "/manager_edit_campus_information",
        this.campusEdit
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.$message.success("修改成功")
    },
  },
};
</script>

<style scoped></style>
