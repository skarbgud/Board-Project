<template>
  <div class="hello">
    {{ boardList }}
  </div>
</template>

<script>
import axios from 'axios';

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
    getBoardList() {
      axios
        .get('http://localhost:8080/board/get-board-list.do')
        .then((response) => {
          if (response.data.success) {
            console.log(response.data.result);
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
