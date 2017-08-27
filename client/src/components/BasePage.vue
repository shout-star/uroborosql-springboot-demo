<template>
</template>

<script>
  import bus from '@/bus'

  export default {
    data () {
      return {
        childValidators: []
      }
    },
    mounted () {
      bus.$on('child-validator-added', component => {
        this.childValidators.push(component)
      })
      bus.$on('validate', () => {
        this.errors.clear()
        this.$validator.validateAll()
        this.childValidators.forEach(component => {
          if (!component.$validator) {
            return
          }
          component.$validator.validateAll()
          let errors = component.$validator.errors
          errors.all().forEach(error => {
            this.errors.add(component.$validator.fields.items[0].name, error)
          })
        })
      })
    },
    methods: {
      lockScreen (show) {
        if (show) {
          window.preLoader.show()
        } else {
          window.preLoader.hide()
        }
      },
      handleError (err) {
        let vm = this

        if (typeof err === String) {
          vm.$router.push({name: 'error', params: {msg: err}})
        } else {
          vm.$router.push({name: 'error', params: {msg: err.response.data.message}})
        }
      },
      validate () {
        bus.$emit('validate')
        return this.errors
      }
    }
  }
</script>

<style scoped>
</style>
