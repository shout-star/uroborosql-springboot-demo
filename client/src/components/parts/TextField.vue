<template>
  <div>
    <div :class="'mdc-textfield' + (fullwidth ? ' fullwidth' : '')">
      <input :type="type ? type : 'text'"
             class="mdc-textfield__input"
             ref="input"
             :id="id" :name="id"
             :required="required ? true : false"
             :autocomplete="autocomplete ? false : 'off'"
             :minlength="minlength ? minlength : false"
             :maxlength="maxlength ? maxlength : false"
             :readonly="readonly ? readonly : false"
             :disabled="disabled ? disabled : false"
             v-validate="validate ? validate : false"
             v-on:input="updateValue($event.target.value)">
      <label ref="label" class="mdc-textfield__label" :for="id">{{ label }}</label>
    </div>
    <span v-show="errors.has(id)" class="help is-danger">{{ errors.first(id) }}</span>
  </div>
</template>

<script>
  import bus from '@/bus'
  import { textfield } from 'material-components-web'

  export default {
    name: 'text-field',
    props: ['id', 'type', 'label', 'required', 'value', 'autocomplete', 'minlength', 'maxlength', 'fullwidth', 'validate', 'disabled', 'readonly'],
    mounted () {
      textfield.MDCTextfield.attachTo(this.$el.querySelector('.mdc-textfield'))
      this.$nextTick(() => {
        bus.$emit('child-validator-added', this)
      })
    },
    watch: {
      value (value) {
        if (value) {
          if (!this.$refs.label.classList.contains('mdc-textfield__label--float-above')) {
            this.$refs.label.classList.add('mdc-textfield__label--float-above')
          }
        }
        this.$refs.input.value = value
      }
    },
    methods: {
      updateValue (val) {
        this.$emit('input', val)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .fullwidth {
    width: 100%;
  }

  .help.is-danger {
    color: #ff3860;
    font-size: smaller;
  }
</style>
