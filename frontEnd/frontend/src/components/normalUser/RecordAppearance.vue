<script src="../utils/auth.js"></script>
<template>
  <div>
    <div>
      <!--  面包屑导航区-->
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>普通用户</el-breadcrumb-item>
        <el-breadcrumb-item>记录猫猫出现</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <el-card>
      <el-row type="flex" justify="space-between" class="marginTop">
        <el-col :span="6"></el-col>
        <el-col :span="6">
          <el-select
            v-model="selectedCatId"
            filterable
            placeholder="请选择"
            @change="selectedCatOnchange"
          >
            <el-option
              v-for="cat in catInfoList"
              :key="cat.value"
              :label="cat.catName"
              :value="cat.catId"
            >
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="6"></el-col>
      </el-row>
      <el-row type="flex" justify="space-between" class="marginTop">
        <el-col :span="6"></el-col>
        <el-col :span="6">
          <img
            v-if="selectedCat"
            :src="selectedCat.imgurl"
            alt="暂无图片"
            class="avoid-shake-max-width"
          />
          <h1 v-else>选择猫猫以进一步操作</h1>
        </el-col>
        <el-col :span="6"></el-col>
      </el-row>
      <el-row v-if="selectedCat" type="flex" justify="space-between" class="marginTop">
        <el-col :span="6"
          >上次记录出现时间: {{ getLocalTime() }}</el-col
        >
        <el-col :span="6"
        >

            点击上传这次的发现 :
          <el-button v-if="uploadActive" type="primary" icon="el-icon-top" circle @click="uploadFind"></el-button>
          <el-button v-else type="success" icon="el-icon-check" circle disabled></el-button>

        </el-col
        >
        <el-col :span="6"></el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import respFilter from "@/components/utils/respFilter";
import config from "@/config";
import localTime from "@/components/utils/getLocalTime";

export default {
  name: "RecordAppearance",
  data() {
    return {
      catInfoList: [],
      selectedCatId: undefined,
      selectedCat: undefined,
      uploadActive:true,
    };
  },
  created() {
    this.init();
  },
  methods: {
    async init() {
      let { data: resp } = await this.$http.post("get_cat_appearance_list");
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
      }
      this.catInfoList = resp.data;
    },
    selectedCatOnchange() {
      this.uploadActive=true;
      let ret = null;
      if (this.selectedCatId) {
        this.catInfoList.forEach((item) => {
          if (item.catId == this.selectedCatId) {
            let url =
              `${config.Http.baseUrl}` + "image?imgId=" + `${item.catPhotoId}`;
            ret = { imgurl: url, lastFoundTime: item.lastFindTime };
            this.selectedCat = ret;
            return;
          }
        });
        this.selectedCat = ret;
        return;
      }
    },
    getLocalTime(){
      return localTime(this.selectedCat.lastFoundTime)
    },
    async uploadFind(){
      this.uploadActive=false;
      const { data: resp } = await this.$http.post("/find_cat",this.selectedCatId);
      console.log(resp);
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.$message.success("登记成功");
      await this.init();
      this.selectedCatOnchange();
      this.uploadActive=false;
      this.$forceUpdate();
      console.log(this);
    }
  },
};
</script>

<style scoped>
.marginTop {
  margin-top: 5vh !important;
}
.avoid-shake-max-width {
  width: 99%;
}
</style>
