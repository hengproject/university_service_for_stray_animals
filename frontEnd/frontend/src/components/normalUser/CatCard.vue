<template>
  <div>
    <main>
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
      <div v-if="selectedCat">
        <div class="headImg">
          <img :src="getEditPhotoAddr(selectedCat.catPhotoId)" alt="" />
        </div>
        <div class="catName">
          <h1>{{ selectedCat.catName }}</h1>
        </div>
        <div class="moreInfo">
          <div class="span">
            <div class="chapter">
              <p class="title">性别</p>
              <p class="value">{{ selectedCat.catSex == 0 ? "公" : "母" }}</p>
            </div>
            <div class="chapter">
              <p class="title">猫猫状况</p>
              <p class="value">{{ selectedCat.catStatus }}</p>
            </div>
            <div class="chapter">
              <p class="title">所属校区</p>
              <p class="value">{{ selectedCat.campusName }}</p>
            </div>
            <div class="chapter">
              <p class="title">首次发现时间</p>
              <p class="value">{{ LocalTime(selectedCat.createTime) }}</p>
            </div>
          </div>
          <div class="span">
            <div class="chapter">
              <p class="title">年龄</p>
              <p class="value">{{ selectedCat.catAge }}</p>
            </div>
            <div class="chapter">
              <p class="title">记录上次出现时间</p>
              <p class="value">{{ LocalTime(selectedCat.lastFindTime) }}</p>
            </div>
            <div class="chapter">
              <p class="title">活动范围</p>
              <p class="value">{{ selectedCat.areaName }}</p>
            </div>
          </div>
          <div class="max-width description">
            <div class="chapter">
              <p class="title">备注</p>
              <p class="value">
                {{ selectedCat.catRemark }}
              </p>
            </div>
          </div>
          <div class="question">
            <div>
              <el-button type="info" round @click="showQuestionDialog"
                >信息有问题?点我纠正</el-button
              >
            </div>
          </div>
        </div>
      </div>
      <div v-else>
        <h1>请选择你想查看的猫猫名称</h1>
      </div>
    </main>
    <el-dialog title="提交问题" :visible.sync="visibility.question">
      <el-form>
        <el-form-item label="问题分类" required>
          <el-select v-model="form.questionForm.questionCategory">
            <el-option label="其他" value="0"></el-option>
            <el-option label="猫咪信息有误" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="问题描述" required>
          <el-input
            type="textarea"
            :rows="5"
            placeholder="请输入内容"
            v-model="form.questionForm.questionDescription"
            maxlength="200"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="问题备注" required>
          <el-input
            type="textarea"
            :rows="1"
            placeholder="请输入内容"
            v-model="form.questionForm.questionRemark"
            maxlength="150"
            show-word-limit
            disabled
          />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="visibility.question = false">取 消</el-button>
        <el-button type="primary" @click="submitQuestion">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import respFilter from "@/components/utils/respFilter";
import config from "@/config";
import getLocalTime from "@/components/utils/getLocalTime";

export default {
  name: "CatCard",
  data() {
    return {
      catInfoList: null,
      selectedCatId: "",
      selectedCat: null,
      visibility: {
        question: false,
      },
      form: {
        questionForm: {
          questionCategory: null,
          questionDescription: null,
          questionRemark: null,
        },
      },
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
      console.log(this.catInfoList);
    },
    selectedCatOnchange() {
      console.log(this.selectedCat);
      let ret = null;
      if (this.selectedCatId) {
        this.catInfoList.forEach((item) => {
          if (item.catId == this.selectedCatId) {
            let url =
              `${config.Http.baseUrl}` + "image?imgId=" + `${item.catPhotoId}`;
            ret = item;
            ret.imgUrl = url;
            this.selectedCat = ret;
            return;
          }
        });
        this.selectedCat = ret;
        return;
      }
    },
    LocalTime: function (ns) {
      return getLocalTime(ns);
    },
    getEditPhotoAddr(catPhotoId) {
      if (!catPhotoId) catPhotoId = "0";

      return `${config.Http.baseUrl}` + "image?imgId=" + `${catPhotoId}`;
    },
    showQuestionDialog() {
      this.visibility.question = true;
      this.form.questionForm.questionDescription = null;
      this.form.questionForm.questionCategory = null;
      this.form.questionForm.questionRemark = `catName : ${this.selectedCat.catName},catId : ${this.selectedCat.catId}`;
    },
    async submitQuestion() {
      //  todo: 上传
      let{data:resp} = await this.$http.post("/question_upload",this.form.questionForm);
      if (!respFilter(resp)) {
        this.$message.error(resp.msg);
        this.logout();
        return;
      }
      this.visibility.question = false;
      this.$message.success("提交成功");
    },
  },
};
</script>

<style scoped lang="less">
.headImg {
  margin: 3vh 0;
  box-sizing: border-box;
  height: 27vh;
  img {
    height: 27vh;
  }
}
.moreInfo {
  margin-top: 2vh;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  .chapter {
    box-sizing: border-box;
    border-left: #aaa solid 3px;
    margin: 2vh 0 5vh 0;
    p {
      margin-left: 10px;
    }
    .title {
      font-size: small;
      color: #aaaaaa;
    }
    .value {
      font-weight: bold;
    }
  }
  .span {
    margin-left: 5%;
    width: 45%;
  }
  .max-width {
    width: 100%;
  }
  .description {
    box-sizing: border-box;
    margin-top: 2vh;
    margin-left: 5%;
  }
  .question {
    width: 100%;
    display: flex;
    justify-content: center;
  }
}
</style>
