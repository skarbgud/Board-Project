<template>
  <div style="height:100px;">
    <!-- txt 파일 불러오기 버튼 -->
    <button @click="importTextFile">불러오기</button>
    <!-- txt 파일 저장하기 버튼 -->
    <button @click="exportTextFile">저장</button>
    <br>
    <!-- text 영역-->
    <textarea id="text-data" v-model="textData" rows="8" cols="100"></textarea>
  </div>
</template>
 
<script>
export default {
  name: 'TextFile',
  data() {
    return {
      /**
       * textarea에 보여줄 텍스트
       * @type {String}
       */
      textData: '',
    };
  },
  methods: {
    /**
     * txt파일을 불러온다
     */
    importTextFile() {
      const input = document.createElement('input');
      input.type = 'file';
      input.accept = 'text/plain'; // 확장자가 xxx, yyy 일때, ".xxx, .yyy"
      // this 접근을 위해 선언 필요
      const self = this;
      input.onchange = function () {
        const file = new FileReader();
        file.onload = () => {
          document.getElementById('text-data').textContent = file.result;
          self.$data.textData = file.result;
        };
        file.readAsText(this.files[0]);
      };
      input.click();
    },
    /**
     * textData를 txt 파일로 저장한다.
     */
    exportTextFile() {
      const text = document.getElementById('text-data').value;
      // 저장하고자하는 파일명
      const filename = '텍스트파일명';
      const element = document.createElement('a');
      element.setAttribute('href', 'data:text/plain;charset=utf-8, ' + encodeURIComponent(text));
      element.setAttribute('download', filename);
      document.body.appendChild(element);
      element.click();
    },
  },
};
</script>
<style lang="scss" scoped></style>