<template>
  <div>
    <!-- 글쓰기 버튼 -->
    <el-button type="danger" @click="openPopup = true">글쓰기</el-button>
    <!-- 팝업 내용 -->
    <el-dialog title="글쓰기" :visible.sync="openPopup" width="30%" center>
      <!-- 본문 영역 -->
      <el-input placeholder="제목을 입력해 주세요" v-model="title"></el-input>
      <el-input
        style="margin-top:30px;"
        type="textarea"
        :rows="30"
        placeholder="내용을 입력해 주세요."
        v-model="content"
      >
      </el-input>
      <!-- dialog footer 영역 -->
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="registboard()">확인</el-button>
        <el-button @click="openPopup = false">취소</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'RegistBoard',
  data() {
    return {
      openPopup: false,
      title: '',
      content: '',
    };
  },
  computed: {
    setParams() {
      const params = {
        title: this.title,
        content: this.content,
      };
      return params;
    },
  },
  methods: {
    registboard() {
      axios
        .post('http://localhost:8080/board/regist-board.do', this.setParams)
        .then((response) => {
          if (response.data.success) {
            console.log(response);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped></style>
