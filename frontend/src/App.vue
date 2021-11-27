<template>
  <div id="app">
    <regist-board @reload="reload"></regist-board>
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
  },
};
</script>

<style></style>
