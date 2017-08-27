<template>
  <i :class="'mdc-icon-toggle material-icons' + (mdcClass ? ' ' + mdcClass : '')"
     role="button" aria-pressed="false"
     :aria-label="label" tabindex="0"
     :data-toggle-on='toggleOn'
     :data-toggle-off='toggleOff'>
   {{defaultIcon}}
  </i>
</template>

<script>
  import { iconToggle } from 'material-components-web'

  let mdcIconToggle

  export default {
    name: 'icon-toggle-button',
    props: ['mdcClass', 'label', 'value', 'toggle-on', 'toggle-off', 'default-icon'],
    mounted () {
      mdcIconToggle = iconToggle.MDCIconToggle.attachTo(this.$el)
      console.log(mdcIconToggle)
      this.$el.addEventListener('MDCIconToggle:change', ({detail}) => {
        console.log(detail)
        this.$emit('change', detail.isOn)
      })
    },
    data () {
      return {
        state: {
          value: false
        }
      }
    },
    watch: {
      value (value) {
        mdcIconToggle.on = value
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
