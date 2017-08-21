<template>
  <button :class="'mdc-fab material-icons ' + mdcClass" aria-label="label"
          v-on:click="onClick"
          v-tooltip="label">
    <span class="mdc-fab__icon">{{ icon }}</span>
  </button>
</template>

<script>
  import { ripple } from 'material-components-web'

  export default {
    name: 'ripple-fab',
    props: ['icon', 'mdcClass', 'label'],
    mounted () {
      ripple.MDCRipple.attachTo(this.$el)
    },
    methods: {
      onClick () {
        this.$emit('click')
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
