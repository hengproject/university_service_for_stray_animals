<script src="../../plugins/element.js"></script>
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
      <!--      添加区-->
      <el-row :gutter="20">
        <el-col :span="7">
          <el-button type="primary" @click="addCampusDialogVisible = true"
          >添加校区</el-button
          >
        </el-col>

      </el-row>
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
            <el-tooltip
              class="item"
              effect="dark"
              content="删除该校区"
              placement="top"
              :enterable="false"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                size="mini"
                @click="showDeleteCampus(scope.row)"
              ></el-button
            ></el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!--    添加campus-->
    <el-dialog
      title="添加校区信息"
      :visible.sync="addCampusDialogVisible"
      width="30%"
    >
      <p>校区名</p>
      <el-input v-model="newCampusName"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addCampusDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmAddCampus()">确 定</el-button>
      </span>
    </el-dialog>
    <!--    修改campus-->
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
    <!--    删除campus确定-->
    <el-drawer title="删除确认" :visible.sync="deleteCampusDrawer" size="63%">
      <h3 style="text-align: center">确认删除此校区嘛？</h3>
      <h4 style="text-align: center">以下区域将会被同步删除</h4>
      <el-table
        :data="deleteCampusDrawerInfoList"
        style="width: 60%; transform: translateX(50%)"
        row-key="campusId"
      >
        <el-table-column label="区域ID" prop="campusId"></el-table-column>
        <el-table-column label="区域名称" prop="campusName"></el-table-column>
      </el-table>
      <div class="button-center">
        <el-button type="danger" @click="confirmDeleteCampus()"
          >确认删除</el-button
        >
        <el-button type="info" @click="deleteCampusDrawer = false"
          >取消</el-button
        >
      </div>
    </el-drawer>
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
      editCampusDialogVisible: false,
      campusEdit: {
        campusId: "",
        campusName: "",
        hasSon: true,
      },
      deleteCampusDrawer: false,
      deleteCampusDrawerInfoList: [],
      deleteCampusDrawerId: "",
      newCampusName:"",
      addCampusDialogVisible:false,
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
      let { data: resp } = await this.$http.post(
        "/manager_edit_campus_information",
        this.campusEdit
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.$message.success("修改成功");
    },
    async showDeleteCampus(rowInfo) {
      this.deleteCampusDrawer = true;
      let { data: resp } = await this.$http.post(
        "/manager_get_basic_info_list_lazy_get_area",
        rowInfo.campusId
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.deleteCampusDrawerInfoList = resp.data;
      this.deleteCampusDrawerId = rowInfo.campusId;
    },
    async confirmDeleteCampus() {
      let { data: resp } = await this.$http.post(
        "/manager_delete_campus_information",
        this.deleteCampusDrawerId
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.$message.success("成功删除");
      this.deleteCampusDrawer = false;
      this.getFormList();
    },
    async confirmAddCampus(){
      let { data: resp } = await this.$http.post("/manager_add_campus_information",this.newCampusName);
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.$message.success("添加成功");
      this.addCampusDialogVisible=false;
      this.getFormList();
    },
  },


};
</script>

<style scoped>
.button-center {
  margin: 20px 0;
  transform: translateX(37%);
}
</style>
