<template>
  <div>
    <el-table border :data="boardList" @row-click="getBoardDetail">
      <el-table-column prop="bno" label="글번호"></el-table-column>
      <el-table-column prop="title" label="제목"></el-table-column>
      <el-table-column prop="content" label="내용"></el-table-column>
      <el-table-column prop="writer" label="작성자"> </el-table-column>
      <el-table-column prop="regDate" label="등록일" :formatter="DateFormat"></el-table-column>
    </el-table>
    <!-- 글 상세 보기 내용 -->
    <el-dialog title="글 상세 보기" :visible.sync="openDetail" width="30%" center>
      글 번호 : {{ boardDetail.bno }} <br>
      제목 : {{ boardDetail.title }} <br>
      등록일 : {{ boardDetail.regDate }} <br>
      내용 : {{ boardDetail.content }} <br>
      작성자 : {{ boardDetail.writer }} <br>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios';
import moment from 'moment';

export default {
  name: 'BoardList',
  data() {
    return {
      boardList: [],
      boardDetail: [],
      openDetail: false,
    };
  },
  created() {
    this.getBoardList();
  },
  methods: {
    DateFormat(row) {
      return moment(row.regDate).format('YYYY-MM-DD hh:MM:ss');
    },
    getBoardList() {
      axios
        .get('http://localhost:8080/board/get-board-list.do')
        .then((response) => {
          if (response.data.success) {
            this.boardList = response.data.result;
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
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
  },
};
</script>

<style scoped></style>
