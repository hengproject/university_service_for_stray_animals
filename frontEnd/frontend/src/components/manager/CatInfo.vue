<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>管理员菜单</el-breadcrumb-item>
      <el-breadcrumb-item>猫猫档案</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-row :gutter="20">
        <el-col :span="7">
          <el-button type="primary" @click="showAddDialog"
            >添加猫咪信息</el-button
          >
        </el-col>
      </el-row>
      <el-table :data="catList" border stripe>
        <el-table-column label="主要信息" align="center">
          <el-table-column label="照片">
            <template #default="scope" :config="config">
              <img
                :src="
                  `${config.Http.baseUrl}` +
                  'image?imgId=' +
                  `${scope.row.catPhotoId}`
                "
                style="width: 100%"
                v-if="scope.row.catPhotoId"
              />
            </template>
          </el-table-column>
          <el-table-column label="ID" prop="catId"></el-table-column>
          <el-table-column label="猫名" prop="catName"></el-table-column>
          <el-table-column label="猫性别">
            <template #default="scope">
              {{ scope.row.catSex == 0 ? "雄" : "雌" }}
            </template>
          </el-table-column>
          <el-table-column label="猫状态" prop="catStatus"></el-table-column>
          <el-table-column label="备注" prop="catRemark"></el-table-column>
        </el-table-column>

        <el-table-column label="记录" align="center">
          <el-table-column label="校区" prop="campusName"></el-table-column>
          <el-table-column label="区域" prop="areaName"></el-table-column>
        </el-table-column>
        <el-table-column label="时间" align="center">
          <el-table-column label="最后发现时间">
            <template #default="scope" :getLocalTime="this.getLocalTime">
              {{ getLocalTime(scope.row.lastFindTime) }}
            </template>
          </el-table-column>
          <el-table-column label="修改时间">
            <template #default="scope" :getLocalTime="this.getLocalTime">
              {{ getLocalTime(scope.row.updateTime) }}
            </template>
          </el-table-column>
          <el-table-column label="创建时间">
            <template #default="scope" :getLocalTime="this.getLocalTime">
              {{ getLocalTime(scope.row.createTime) }}
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button-group>
                <el-tooltip
                  class="item"
                  effect="dark"
                  content="修改猫咪信息"
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
                    @click="deleteCat(scope.row)"
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

    <!--    修改动物信息对话框-->
    <el-drawer title="修改信息" :visible.sync="editDialogVisible" size="67%">
      <!--      内容主题区-->

      <el-form :model="editForm" ref="editFormRef" class="drawer-form">
        <el-form-item label="ID">
          <el-input v-model="editForm.catId" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="照片">
          <br />
          <el-upload
            class="form-upload"
            ref="uploadCatPictureRef"
            :action="pictureUploadAddr"
            :auto-upload="false"
            :data="{ catId: editForm.catId }"
            :on-change="onChange"
            :show-file-list="false"
          >
            <img
              v-if="editForm.catPhotoSrc || editForm.catPhotoId"
              :src="this.editForm.photoAddr"
              style="width: 100%"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="名字" prop="catName">
          <el-input
            v-model="editForm.catName"
            placeholder="请输入名称"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="catSex">
          <br />
          <el-select v-model="editForm.catSex">
            <el-option label="雄性" :value="0"></el-option>
            <el-option label="雌性" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="catAge">
          <el-input
            v-model="editForm.catAge"
            placeholder="请输入年龄"
          ></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="catRemark">
          <el-input
            v-model="editForm.catRemark"
            placeholder="请输入备注"
          ></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="catStatus">
          <el-input
            v-model="editForm.catStatus"
            placeholder="请输入当前状态"
          ></el-input>
        </el-form-item>
        <el-form-item label="校区">
          <br />
          <el-select @change="editCampusChange" v-model="editForm.campusId">
            <el-option
              :key="campus.campusId"
              :label="campus.campusName"
              :value="campus.campusId"
              v-for="campus in campusForm"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="活动区域">
          <br />
          <el-select v-model="editForm.areaId" :placeholder="editForm.areaName">
            <el-option
              :key="area.campusId"
              :label="area.campusName"
              :value="area.campusId"
              v-for="area in areaForm"
              @change="editAreaChange"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <span class="drawer-button-group">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitEditForm">确 定</el-button>
      </span>
    </el-drawer>

    <!--    添加动物信息对话框-->
    <el-drawer title="增加信息" :visible.sync="addCatDialogVisible" size="67%">
      <!--      内容主题区-->

      <el-form :model="editForm" ref="addFormRef" class="drawer-form">
        <el-form-item label="ID">
          <el-input v-model="editForm.catId" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="照片">
          <br />
          <el-upload
            class="form-upload"
            ref="uploadCatPictureRef"
            :action="pictureUploadAddr"
            :auto-upload="false"
            :data="{ catId: this.temp.catId4Img}"
            :on-change="onChange"
            :show-file-list="false"
          >
            <img
              v-if="editForm.catPhotoSrc || editForm.catPhotoId"
              :src="this.editForm.photoAddr"
              style="width: 100%"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="名字" prop="catName">
          <el-input
            v-model="editForm.catName"
            placeholder="请输入名称"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="catSex">
          <br />
          <el-select v-model="editForm.catSex">
            <el-option label="雄性" :value="0"></el-option>
            <el-option label="雌性" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="catAge">
          <el-input
            v-model="editForm.catAge"
            placeholder="请输入年龄"
          ></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="catRemark">
          <el-input
            v-model="editForm.catRemark"
            placeholder="请输入备注"
          ></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="catStatus">
          <el-input
            v-model="editForm.catStatus"
            placeholder="请输入当前状态"
          ></el-input>
        </el-form-item>
        <el-form-item label="校区">
          <br />
          <el-select @change="editCampusChange" v-model="editForm.campusId">
            <el-option
              :key="campus.campusId"
              :label="campus.campusName"
              :value="campus.campusId"
              v-for="campus in campusForm"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="活动区域">
          <br />
          <el-select v-model="editForm.areaId" :placeholder="editForm.areaName">
            <el-option
              :key="area.campusId"
              :label="area.campusName"
              :value="area.campusId"
              v-for="area in areaForm"
              @change="editAreaChange"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <span class="drawer-button-group">
        <el-button @click="addCatDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitAddForm">确 定</el-button>
      </span>
    </el-drawer>
  </div>
</template>

<script>
import respFilter from "@/components/utils/respFilter";
import config from "@/config";
import copyProperties from "@/components/utils/copyProperties";

export default {
  name: "CatInfo",
  data() {
    return {
      pictureUploadAddr: config.Http.baseUrl + "/manager_upload_file",
      catList: [],
      queryInfo: {
        query: "",
        pageNum: 0,
        pageSize: 2,
      },
      total: 0,
      config,
      editDialogVisible: false,
      editForm: {
        catId: -1,
        catAge: -1,
        catName: "-1",
        catPhotoId: null,
        catRemark: "-1",
        catSex: -1,
        catStatus: "-1",
        campusId: "-1",
        areaId: -1,
        catPhotoSrc: null,
        photoAddr: null,
        areaIdTemp: null,
      },
      campusForm: [],
      areaForm: [],
      uploadEditForm: {
        catId: "-1",
        catAge: -1,
        catName: "-1",
        catRemark: "-1",
        catSex: -1,
        catStatus: "-1",
        campusId: "-1",
        areaId: -1,
      },
      addCatDialogVisible: false,
      temp:{
        catId4Img: null,
      }
    };
  },
  created() {
    this.getCatList();
    this.getAreaFormList();
  },
  methods: {
    getLocalTime(nS) {
      return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/, " ");
    },
    async getCatList() {
      let { data: resp } = await this.$http.post(
        "/get_cat_information",
        this.queryInfo
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.catList = resp.data.u;
      this.total = resp.data.v;
    },
    //监听pagesize改变
    handleSizeChange(newPageSize) {
      this.queryInfo.pageSize = newPageSize;
      this.getCatList();
    },
    handleCurrentChange(newPageNum) {
      this.queryInfo.pageNum = newPageNum - 1;
      this.getCatList();
    },
    async getAreaFormList() {
      let { data: resp } = await this.$http.post(
        "/manager_get_basic_info_list"
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.campusForm = resp.data.campusList;
    },
    showEditDialog(row) {
      this.editDialogVisible = true;
      this.editForm = row;
      this.editForm.campusId += "";
      this.editForm.areaIdTemp = row.areaId;
      this.editCampusChange(); //todo:提交时候别忘了如果为空
      this.getEditPhotoAddr();

      this.$forceUpdate();
    },
    async submitEditForm() {
      if (!this.editForm.areaId)
        this.editForm.areaId = this.editForm.areaIdTemp;
      copyProperties(this.editForm, this.uploadEditForm);
      this.$refs.uploadCatPictureRef.submit();
      let { data: respForm } = await this.$http.post(
        "/manager_modify_cat_info",
        this.uploadEditForm
      );
      if (!respFilter(respForm)) {
        this.$message.error(respForm.msg);
        this.logout();
        return;
      }
      this.$message.success("修改成功");
      this.editDialogVisible = false;
      this.getCatList();
    },
    onChange(file) {
      this.editForm.catPhotoSrc = URL.createObjectURL(file.raw);
      this.getEditPhotoAddr();
    },
    getEditPhotoAddr() {
      if (this.editForm.catPhotoSrc)
        this.editForm.photoAddr = this.editForm.catPhotoSrc;
      else
        this.editForm.photoAddr =
          `${config.Http.baseUrl}` +
          "image?imgId=" +
          `${this.editForm.catPhotoId}`;
      this.$forceUpdate();
    },
    async editCampusChange() {
      this.editForm.areaId = undefined;
      await this.refreshAreaForm();
    },
    async refreshAreaForm() {
      let { data: resp } = await this.$http.post(
        "/manager_get_basic_info_list_lazy_get_area",
        this.editForm.campusId
      );
      console.log(resp.data);
      this.areaForm = resp.data;
      this.areaForm.forEach((area) => {
        area.campusId = area.campusId.split(".")[1];
      });
    },
    editAreaChange() {
      this.$forceUpdate();
    },
    showAddDialog() {
      this.addCatDialogVisible = true;
      this.editForm = {
        catId: "由后台自动生成",
        catAge: null,
        catName: null,
        catPhotoId: null,
        catRemark: null,
        catSex: null,
        catStatus: null,
        campusId: null,
        areaId: null,
        catPhotoSrc: null,
        photoAddr: null,
        areaIdTemp: null,
      };
    },
    async submitAddForm() {
      if (!this.editForm.areaId)
        this.editForm.areaId = this.editForm.areaIdTemp;
      copyProperties(this.editForm, this.uploadEditForm);
      // this.uploadEditForm.areaId = null;
      let { data: respForm } = await this.$http.post(
        "/manager_add_cat_info",
        this.uploadEditForm
      );

      this.temp.catId4Img = respForm.data;
      this.$set(this.temp, "catId4Img", respForm.data);
      if (!respFilter(respForm)) {
        this.$message.error(respForm.msg);
        this.logout();
        return;
      }
      this.$nextTick(() => {
        console.log(this.temp.catId4Img);
        this.$refs.uploadCatPictureRef.submit();
      });
      this.$message.success("添加成功");
      this.addCatDialogVisible = false;
      await this.getCatList();
    },
    async deleteCat(row){
      let { data: resp } = await this.$http.post(
        "/manager_delete_cat_info",
        row.catId
      );
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.$message.success("删除成功");
      await this.getCatList();
    },
  }
};
</script>

<style scoped>
.drawer-form {
  width: 50%;
  transform: translateX(50%);
}
.form-upload {
  width: 10vw;
  height: 100%;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.drawer-button-group {
}
</style>
