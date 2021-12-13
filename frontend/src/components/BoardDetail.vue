<template>
  <div>
    <!-- 글 상세 보기 내용 -->
    <el-dialog title="글 상세 보기" :visible.sync="openDetail" width="30%" center>
      글 번호 : {{ boardDetail.bno }} <br>
      제목 : {{ boardDetail.title }} <br>
      등록일 : {{ boardDetail.regDate }} <br>
      내용 : {{ boardDetail.content }} <br>
      작성자 : {{ boardDetail.writer }} <br>
      <el-row type="flex" justify="end" style="margin-top:30px;">
        <el-button type="primary" @click="clcikEditButton()">수정</el-button>
        <el-button type="danger" @click="clcikDeleteButton()">삭제</el-button>
      </el-row>
    </el-dialog>
    <regist-board @reload="reload" ref="registPopup"></regist-board>
  </div>
</template>

<script>
import axios from 'axios';
import RegistBoard from './RegistBoard.vue';

export default {
  name: 'BoardDetail',
  components: { RegistBoard },
  data() {
    return {
      openDetail: false,
      boardDetail: [],
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
    getBoardDetail(row) {
      const params = {
        bno: row.bno,
      };
      axios
        .post('http://localhost:8080/board/get-board-detail.do', params)
        .then((response) => {
          if (response.data.success) {
            this.boardDetail = response.data.result;
            this.openDetail = true;
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    clcikEditButton() {
      this.openDetail = false;
      this.$refs.registPopup.bno = this.boardDetail.bno;
      this.$refs.registPopup.title = this.boardDetail.title;
      this.$refs.registPopup.content = this.boardDetail.content;
      this.$refs.registPopup.editMode = true;
      this.$refs.registPopup.openPopup = true;
    },
    clcikDeleteButton () {
      const params = {
        bno: this.boardDetail.bno,
      };
      axios
        .post('http://localhost:8080/board/delete-board.do', params)
        .then((response) => {
          if (response.data.success) {
            this.openDetail = false;
            this.reload();
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    reload() {
      // 목록을 재로딩을 위해 이벤트 emit
      this.$emit('reload');
    }
  },
};
</script>

<style scoped></style>
