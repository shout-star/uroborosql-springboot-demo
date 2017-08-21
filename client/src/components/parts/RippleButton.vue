<template>
  <button :class="'mdc-button mdc-button--raised ' + mdcClass"
    v-on:click="onClick">
    {{ label }}
  </button>
</template>

<script>
import {ripple} from 'material-components-web'

export default {
  name: 'ripple-button',
  props: ['mdcClass', 'label'],
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
button {
  min-width: 120px;
}
</style>
