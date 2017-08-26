<template>
  <div class="top">
    <div class="mdc-typography--display2">Welcome to uroboroSQL Pet Clinic</div>
    <img src="../assets/top.jpg">
  </div>
</template>

<script>
  import BasePage from '@/components/BasePage'

  export default {
    name: 'home',
    mixins: [
      BasePage
    ],
    data () {
      return {}
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .top img {
    width: 100%;
  }
</style>
