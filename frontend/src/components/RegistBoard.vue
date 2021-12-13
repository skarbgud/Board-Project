<template>
  <div>
    <!-- 팝업 내용 -->
    <el-dialog :title="ModalTitle" :visible.sync="openPopup" width="30%" center :close-on-click-modal="false">
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
      bno: '',
      editMode: false,
      title: '',
      content: '',
    };
  },
  computed: {
    setParams() {
      const params = {
        bno: this.bno,
        title: this.title,
        content: this.content,
      };
      return params;
    },
    ModalTitle() {
      return this.editMode === true ? '글 수정' : '글 등록';
    },
  },
  methods: {
    registboard() {
      // 글 수정이 아니라면 글 등록 호출
      if (!this.editMode) {
        axios
          .post('http://localhost:8080/board/regist-board.do', this.setParams)
          .then((response) => {
            if (response.data.success || response.data.result) {
              // 창의 화면 변수 false로 창 닫기
              this.openPopup = false;
              // 입력되어 있는 변수들의 값을 초기화
              this.title = '';
              this.content = '';
              // 목록을 재로딩을 위해 이벤트 emit
              this.$emit('reload');
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      }
      // 글 수정을 호출
      else {
        axios
          .post('http://localhost:8080/board/edit-board.do', this.setParams)
          .then((response) => {
            if (response.data.success || response.data.result) {
              // 창의 화면 변수 false로 창 닫기
              this.openPopup = false;
              // 입력되어 있는 변수들의 값을 초기화
              this.title = '';
              this.content = '';
              // 목록을 재로딩을 위해 이벤트 emit
              this.$emit('reload');
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      }
    },
  },
};
</script>

<style scoped></style>
