// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
// export const CONSOLE_WARN = console.warn
import 'material-design-icons/iconfont/material-icons.css'
import 'material-components-web/dist/material-components-web.css'
import './style.css'
import Vue from 'vue'
import VeeValidate from 'vee-validate'
import VTooltip from 'v-tooltip'
import App from './App'
import router from './router'
import axios from 'axios'

axios.defaults.baseURL = process.env.BASE_URL
if (window.localStorage.token) {
  axios.defaults.headers.common['Authorization'] = 'Bearer ' + window.localStorage.token
}

Vue.config.productionTip = false
Vue.use(VeeValidate)
Vue.use(VTooltip)

// router hook
router.beforeEach((to, from, next) => {
  if (window.preLoader) {
    window.preLoader.show()
  }
  next()
})
router.afterEach((to, from) => {
  window.scrollTo(0, 0)
  if (window.preLoader) {
    window.preLoader.hide()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {App}
})
