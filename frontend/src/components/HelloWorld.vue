<template>
  <div>
    <el-table border :data="boardList">
      <el-table-column prop="bno" label="글번호"></el-table-column>
      <el-table-column prop="title" label="제목"></el-table-column>
      <el-table-column prop="content" label="내용"></el-table-column>
      <el-table-column prop="writer" label="작성자"> </el-table-column>
      <el-table-column prop="regDate" label="등록일" :formatter="DateFormat"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from 'axios';
import moment from 'moment';

export default {
  name: 'HelloWorld',
  data() {
    return {
      boardList: [],
    };
  },
  props: {
    msg: String,
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
  },
};
</script>

<style scoped></style>
