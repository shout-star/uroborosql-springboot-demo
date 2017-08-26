<template>
  <div>
    <div class="datepicker-container mdc-textfield">
      <datepicker input-class="mdc-textfield__input"
                  ref="input"
                  :id="id" :name="id"
                  :required="required ? true : false"
                  :format="format"
                  :value="state.value"
                  :clear-button="true"
                  clear-button-icon="material-icons"
                  v-validate="validate ? validate : false"
                  v-on:selected="onDateSelected">
      </datepicker>
      <label ref="label" class="mdc-textfield__label" for="my-textfield">{{ label }}</label>
    </div>
    <span v-show="errors.has(id)" class="help is-danger">{{ errors.first(id) }}</span>
  </div>
</template>

<script>
  import { textfield } from 'material-components-web'
  import bus from '@/bus'
  import Datepicker from 'vuejs-datepicker'

  export default {
    name: 'date-picker',
    components: {
      Datepicker
    },
    props: ['id', 'format', 'label', 'required', 'value', 'fullwidth', 'validate'],
    mounted () {
      textfield.MDCTextfield.attachTo(this.$el.querySelector('.mdc-textfield'))
      let input = this.$el.querySelector('.mdc-textfield__input')
      input.addEventListener('focus', (evt) => {
        setTimeout(() => {
          if (!this.$refs.input.isOpen) {
            this.$refs.input.showCalendar()
          }
        }, 200)
      })
      this.$nextTick(() => {
        bus.$emit('child-validator-added', this)
      })
    },
    data () {
      return {
        state: {
          value: null
        }
      }
    },
    watch: {
      value (value) {
        if (value) {
          if (!this.$refs.label.classList.contains('mdc-textfield__label--float-above')) {
            this.$refs.label.classList.add('mdc-textfield__label--float-above')
          }
        }
        this.state.value = value
      }
    },
    methods: {
      onDateSelected (val) {
        this.state.value = val
        this.updateValue(val)
      },
      updateValue (val) {
        this.$emit('input', val)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .datepicker-container {
    z-index: 1;
  }

  .help.is-danger {
    color: #ff3860;
    display: block;
    font-size: smaller;
  }
</style>
<style>
  .vdp-datepicker input.mdc-textfield__input {
    width: 110px;
  }
</style>
