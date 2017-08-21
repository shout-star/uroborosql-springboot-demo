<template>
  <aside id="save-dialog"
         class="mdc-dialog"
         aria-labelledby="save-dialog-label"
         aria-describedby="save-dialog-description">
    <div class="mdc-dialog__surface">
      <header class="mdc-dialog__header">
        <h2 id="save-dialog-label" class="mdc-dialog__header__title">
          {{title}}
        </h2>
      </header>
      <section id="save-dialog-description" class="mdc-dialog__body">
        {{description}}
      </section>
      <footer class="mdc-dialog__footer">
        <ripple-button
          label="Cancel"
          mdcClass="mdc-dialog__footer__button mdc-dialog__footer__button--cancel"
          @click="onCancelClick">
        </ripple-button>
        <ripple-button
          label="OK"
          mdcClass="mdc-dialog__footer__button mdc-dialog__footer__button--accept"
          @click="onOkClick">
        </ripple-button>
      </footer>
    </div>
    <div class="mdc-dialog__backdrop"></div>
  </aside>
</template>

<script>
  import { dialog } from 'material-components-web'
  import RippleButton from '@/components/parts/RippleButton'

  let saveDialog

  export default {
    name: 'save-dialog',
    components: {
      RippleButton
    },
    props: {
      title: {
        type: String,
        default: 'Confirm'
      },
      description: {
        type: String,
        default: 'May I preserve it?'
      }
    },
    data () {
      return {
      }
    },
    mounted () {
      saveDialog = new dialog.MDCDialog(this.$el)
    },
    methods: {
      show () {
        saveDialog.show()
      },
      onCancelClick () {
        this.$emit('cancel')
      },
      onOkClick () {
        this.$emit('ok')
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
