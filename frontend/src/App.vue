<template>
  <div id="app">
    <regist-board></regist-board>
    <board-list></board-list>
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
};
</script>

<style></style>
