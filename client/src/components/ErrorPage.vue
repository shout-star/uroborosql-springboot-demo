<template>
  <div class="error">
    <img src="../assets/oups.jpg">
    <div>
      <div class="title mdc-typography--display2">Something happened...</div>
      <div class="msg mdc-typography--headline">{{msg}}</div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'error',
    data () {
      return {
        msg: ''
      }
    },
    mounted () {
      window.preLoader.hide()
      this.msg = this.$route.params.msg
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .error {
    display: flex;
    margin-top: 80px;
  }

  .error img {
    height: 180px;
    margin-right: 10px;
  }

  .error .title {
    color: #F44336;
  }

  .error .msg {
    margin-top: 30px;
  }
</style>
