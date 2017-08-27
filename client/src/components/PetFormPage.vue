<template>
  <div class="owners-form">
    <div class="mdc-typography--display2">Pet</div>
    <text-field id="owner_name" fullwidth="true" label="Owner" disabled="true"
                v-model="model.ownerName"></text-field>
    <text-field id="name" fullwidth="true" label="Name"
                v-model="model.name" validate="required|max:30"></text-field>
    <date-picker id="birth_date" format="yyyy-MM-dd" label="Birth Date"
                 v-model="model.birthDate" validate="required"></date-picker>
    <select-menu id="type" label="Type" :options="typeOptions"
                 v-model.number="model.typeId" validate="required"></select-menu>
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
  import DatePicker from '@/components/parts/DatePicker'
  import RippleButton from '@/components/parts/RippleButton'
  import SaveDialog from '@/components/parts/SaveDialog'
  import SelectMenu from './parts/SelectMenu.vue'

  export default {
    name: 'pet-form',
    mixins: [
      BasePage
    ],
    components: {
      SelectMenu,
      TextField,
      DatePicker,
      RippleButton,
      SaveDialog
    },
    data () {
      return {
        model: {
          ownerId: null,
          ownerName: '',
          name: '',
          birthDate: '',
          typeId: null
        },
        typeOptions: []
      }
    },
    mounted () {
      let vm = this

      if (!isFinite(vm.$route.params.id)) {
        vm.$router.push({name: 'error', params: {msg: 'Not found Owner.'}})
      }

      vm.model.ownerId = vm.$route.params.id

      axios.get('/api/types').then((response) => {
        vm.typeOptions = response.data
      }).catch((err) => {
        vm.handleError(err)
      })
      axios.get('/api/owners/' + vm.$route.params.id).then((response) => {
        vm.model.ownerName = response.data.firstName + ' ' + response.data.lastName
        if (isFinite(vm.$route.params.petId)) {
          Object.assign(vm.model, response.data.pets.find(p => (p.id === parseInt(vm.$route.params.petId))))
        }
      }).catch((err) => {
        vm.handleError(err)
      })
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

        if (isFinite(vm.$route.params.petId)) {
          axios.patch('/api/pets/' + vm.$route.params.petId, vm.model).then((response) => {
            vm.$router.push({name: 'owner-show', params: {id: vm.$route.params.id}})
          }).catch((err) => {
            vm.handleError(err)
          })
        } else {
          axios.post('/api/pets/new', vm.model).then((response) => {
            vm.$router.push({name: 'owner-show', params: {id: vm.$route.params.id}})
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
