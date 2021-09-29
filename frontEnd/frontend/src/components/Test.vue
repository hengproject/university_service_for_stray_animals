<script src="utils/auth.js"></script>
<template>
  <div>
    <el-upload
      class="upload-demo"
      ref="upload"
      :action="pictureUpload"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList"
      :auto-upload="false"
      :on-change="handleChange"
      :limit="1"
    >
      <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
      <el-button
        style="margin-left: 10px"
        size="small"
        type="success"
        @click="submitUpload"
        >上传到服务器</el-button
      >
      <div slot="tip" class="el-upload__tip">
        只能上传jpg/png文件，且不超过500kb
      </div>
    </el-upload>
    <img src="http://localhost:8080/image?imgId=9969996"/>
  </div>
</template>

<script>
import config from "@/config";

export default {
  data() {
    return {
      fileList: [],
      pictureUpload: config.Http.baseUrl + "/normal_user_upload_file",
    };
  },
  methods: {
    submitUpload() {
      console.log(this.fileList);
      this.$refs.upload.submit();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleChange(file, fileList) {
      console.log(file, fileList);
    },
  },
};
</script>

<style scoped></style>
