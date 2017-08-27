<template>
  <div>
    <div class="sql-log-frame mdc-elevation--z3" v-show="state.show">
      <ul>
        <li v-for="log in logs">
          <pre>{{log}}</pre>
        </li>
      </ul>
    </div>
    <icon-toggle-button label="Show SQL Log"
                        default-icon="visibility_off"
                        toggle-on='{"label": "Show SQL log", "content": "visibility_off"}'
                        toggle-off='{"label": "Hide SQL log", "content": "visibility"}'
                        @change="onVisibilityChange"
                        v-tooltip.right="'SQL log window visibility'"
                        v-model="state.show"></icon-toggle-button>
  </div>
</template>

<script>
  import Stomp from 'stompjs/lib/stomp.js'
  import IconToggleButton from '@/components/parts/IconToggleButton'

  export default {
    name: 'sql-log-frame',
    components: {
      IconToggleButton
    },
    data () {
      return {
        logs: [],
        state: {
          show: false
        }
      }
    },
    created () {
      this.connect()
    },
    methods: {
      append (msg) {
        msg.split(`\n`).forEach(m => this.logs.push(m))
        setTimeout(() => (this.$el.scrollTop = this.$el.scrollHeight), 200)
      },
      connect () {
        let vm = this
        let host = process.env.HOST || location.host
        let protocol = location.protocol === 'http:' ? 'ws:' : 'wss:'

        let stompClient = Stomp.Stomp.over(new WebSocket(protocol + '//' + host + '/endpoint'))
        stompClient.connect({}, () => {
          stompClient.subscribe('/topic/sqllog', (msg) => {
            vm.append(msg.body)
          })
        }, () => {
          console.info('retry after 10s...')
          setTimeout(() => this.connect(), 10000)
        })
      },
      onVisibilityChange (visibility) {
        console.log(visibility)
        this.state.show = visibility
      }
    }
  }
</script>

<style scoped>
  .sql-log-frame {
    border: 1px solid darkgrey;
    width: 95%;
    height: 200px;
    overflow-y: scroll;
    margin: 50px 10px 0 10px;
  }

  ul {
    list-style-type: none;
    margin: 0;
    padding: 10px;
  }

  li {
    font-size: 12px;
    line-height: 0.5em;
  }

  .mdc-icon-toggle {
    color: #BDBDBD;
    font-size: 36px;
  }
</style>
