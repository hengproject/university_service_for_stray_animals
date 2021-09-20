<template>
  <div>
    <el-upload drag :action="pictureUpload" >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">
        只能上传jpg/png文件，且不超过500kb
      </div>
    </el-upload>
  </div>
</template>

<script>
import config from "@/config";

export default {
  name: "CatDocuments",
  data() {
    return {
      pictureUpload: config.Http.baseUrl + "/normal_user_upload_file",
    };
  },
};
</script>

<style scoped></style>
