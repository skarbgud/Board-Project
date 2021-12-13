<template>
  <div id="app">
    <!-- 글쓰기 버튼 -->
    <el-button type="danger" @click="openRegistPopup()">글쓰기</el-button>
    <regist-board @reload="reload" ref="registPopup"></regist-board>
    <board-list ref="list"></board-list>
  </div>
</template>

<script>
import RegistBoard from './components/RegistBoard.vue';
import BoardList from './components/BoardList.vue';

export default {
  name: 'App',
  components: {
    RegistBoard,
    BoardList,
  },
  methods: {
    reload() {
      this.$refs.list.getBoardList();
    },
    openRegistPopup() {
      this.$refs.registPopup.openPopup = true;
    }
  },
};
</script>

<style></style>
