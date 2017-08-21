<template>
  <div>
    <div :id="id" :class="'mdc-select' + (fullwidth ? ' fullwidth' : '')" role="listbox" tabindex="0">
      <span class="mdc-select__selected-text">{{chooseText}}</span>
      <div class="mdc-simple-menu mdc-select__menu">
        <ul class="mdc-list mdc-simple-menu__items">
          <li class="mdc-list-item" role="option" aria-disabled="true">
            {{chooseText}}
          </li>
          <li class="mdc-list-item" role="option" v-for="option in options" :id="option.value"
              :aria-selected="option.selected ? option.selected : false">
            {{option.label}}
          </li>
        </ul>
      </div>
    </div>
    <label class="label" :for="id">{{label}}</label>
    <span v-show="errors.has(id)" class="help is-danger">{{ errors.first(id) }}</span>
  </div>
</template>

<script>
  import { select } from 'material-components-web'
  let mdcSelect

  export default {
    name: 'select-menu',
    props: ['id', 'label', 'options', 'required', 'value', 'fullwidth', 'validate', 'disabled'],
    data () {
      return {
        state: {
          value: null
        }
      }
    },
    mounted () {
      mdcSelect = select.MDCSelect.attachTo(this.$el.querySelector('.mdc-select'))
      mdcSelect.listen('MDCSelect:change', () => {
        this.state.value = mdcSelect.value
        this.updateValue(mdcSelect.value)
      })
    },
    watch: {
      value (value) {
        if (this.state.value !== value) {
          this.state.value = value
          mdcSelect.selectedIndex = this.options.findIndex(o => (o.value === value)) + 1
        }
      }
    },
    computed: {
      chooseText () {
        return 'Choose ' + this.label
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

  .label {
    color: var(--mdc-theme-text-hint-on-light, rgba(0, 0, 0, 0.38));
    display: block;
    transform-origin: left top;
    transform: translateY(-250%) scale(0.75, 0.75);
  }

  .mdc-select {
    height: 48px;
    margin-top: 25px;
  }

  .help.is-danger {
    color: #ff3860;
    font-size: smaller;
  }
</style>
