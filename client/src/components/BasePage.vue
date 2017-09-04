<template>
</template>

<script>
  import bus from '@/bus'
  import auth from '@/auth'
  import TextField from '@/components/parts/TextField'
  import RippleButton from '@/components/parts/RippleButton'

  export default {
    data () {
      return {
        childValidators: []
      }
    },
    components: {
      TextField,
      RippleButton
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
        } else if (err.response) {
          if (err.response.status === 403) {
            auth.removeAuthToken()
            vm.$router.push({name: 'login'})
          } else {
            vm.$router.push({name: 'error', params: {msg: err.response.data.message}})
          }
        } else {
          vm.$router.push({name: 'error', params: {msg: err.message}})
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
