import Vue from 'vue'
import axios from 'axios'

const auth = new Vue({
  name: 'auth',
  data () {
    return {
      state: {
        loggedin: false
      }
    }
  },
  methods: {
    init () {
      this.setAuthToken(window.localStorage.token)
    },
    setAuthToken (token) {
      window.localStorage.token = token
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + window.localStorage.token
      this.state.loggedin = !!token
    },
    removeAuthToken () {
      window.localStorage.token = ''
      axios.defaults.headers.common['Authorization'] = ''
      this.state.loggedin = false
    },
    loggedin () {
      return this.state.loggedin
    }
  }
})
export default auth
