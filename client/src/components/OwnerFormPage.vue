<template>
  <div class="owners-form">
    <div class="mdc-typography--display2">Owner</div>
    <text-field id="first_name" fullwidth="true" label="First name"
                v-model="model.firstName" validate="required|max:30"></text-field>
    <text-field id="last_name" fullwidth="true" label="Last name"
                v-model="model.lastName" validate="required|max:30"></text-field>
    <text-field id="address" fullwidth="true" label="Address"
                v-model="model.address" validate="required|max:255"></text-field>
    <text-field id="city" fullwidth="true" label="City"
                v-model="model.city" validate="required|max:80"></text-field>
    <text-field id="telephone" fullwidth="true" label="Telephone"
                v-model="model.telephone" validate="required|digits:10"
                maxlength="10"></text-field>
    <div class="save-owner">
      <ripple-button label="Cancel" mdcClass="mdc-button--accent" @click="onCancelClick"></ripple-button>
      <ripple-button label="Save" mdcClass="mdc-button--primary" @click="onOkClick"></ripple-button>
    </div>
    <save-dialog ref="saveDialog" @ok="onSave"></save-dialog>
  </div>
</template>

<script>
  import axios from 'axios'
  import BasePage from '@/components/BasePage'
  import TextField from '@/components/parts/TextField'
  import RippleButton from '@/components/parts/RippleButton'
  import SaveDialog from '@/components/parts/SaveDialog'

  export default {
    name: 'owner-form',
    mixins: [
      BasePage
    ],
    components: {
      TextField,
      RippleButton,
      SaveDialog
    },
    data () {
      return {
        model: {
          firstName: '',
          lastName: '',
          address: '',
          city: '',
          telephone: ''
        }
      }
    },
    mounted () {
      let vm = this

      if (isFinite(this.$route.params.id)) {
        axios.get('/api/owners/' + this.$route.params.id).then((response) => {
          Object.assign(vm.model, response.data)
        }).catch((err) => {
          vm.handleError(err)
        })
      }
    },
    methods: {
      onOkClick () {
        if (!this.validate().any()) {
          this.$refs.saveDialog.show()
        }
      },
      onCancelClick () {
        this.$router.go(-1)
      },
      onSave () {
        let vm = this
        this.lockScreen()

        if (isFinite(vm.$route.params.id)) {
          axios.patch('/api/owners/' + vm.$route.params.id, vm.model).then((response) => {
            vm.$router.push({name: 'owner-show', params: {id: vm.$route.params.id}})
          }).catch((err) => {
            vm.handleError(err)
          })
        } else {
          axios.post('/api/owners/new', vm.model).then((response) => {
            vm.$router.push({name: 'owner-show', params: {id: response.data.id}})
          }).catch((err) => {
            vm.handleError(err)
          })
        }
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .save-owner {
    margin-top: 30px;
    float: right;
  }
</style>
