<template>
  <div class="login-form">
    <div class="mdc-typography--display2">Login</div>
    <text-field id="user" fullwidth="true" label="User"
                v-model="model.user" validate="required|max:30"></text-field>
    <text-field id="password" type="password" fullwidth="true" label="Password"
                v-model="model.password" validate="required|max:30"></text-field>
    <div class="button-area">
      <ripple-button label="Login" mdcClass="mdc-button--primary" @click="onLoginClick"></ripple-button>
    </div>
    <div>{{msg}}</div>
  </div>
</template>

<script>
  import axios from 'axios'
  import auth from '@/auth'
  import BasePage from '@/components/BasePage'

  export default {
    name: 'login',
    mixins: [
      BasePage
    ],
    data () {
      return {
        model: {
          user: '',
          password: ''
        },
        msg: ''
      }
    },
    methods: {
      onLoginClick () {
        let vm = this

        axios.post('/api/login', vm.model).then((response) => {
          auth.setAuthToken(response.data.token)
          vm.$router.push({name: 'home'})
        }).catch((err) => {
          this.msg = err.response.data.message
        })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .login-form {
    width: 80%;
    margin-left: auto;
    margin-right: auto;
  }
</style>
