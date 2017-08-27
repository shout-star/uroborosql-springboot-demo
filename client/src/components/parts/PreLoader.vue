<template>
  <div v-if="shown">
    <div class="overlay"></div>
    <div class="preloader">
      <circle8></circle8>
    </div>
  </div>
</template>

<script>
  import Circle8 from 'vue-loading-spinner/src/components/Circle8.vue'

  export default {
    name: 'pre-loader',
    components: {
      Circle8
    },
    data () {
      return {
        shown: false
      }
    },
    beforeCreate () {
      window.preLoader = this
    },
    methods: {
      show () {
        this.shown = true
      },
      hide () {
        setTimeout(() => (this.shown = false), 200)
      },
      toggle () {
        this.shown = !this.shown
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    opacity: 0.9;
    background-color: #fff;
    z-index: 1001;
    transition: all 0.5s;
  }

  .preloader {
    z-index: 1002;
    top: 40%;
    left: 46%;
    position: fixed;
  }
</style>
<style>
  .preloader .spinner-inner {
    transform: scale(2.5) !important;
  }
</style>
