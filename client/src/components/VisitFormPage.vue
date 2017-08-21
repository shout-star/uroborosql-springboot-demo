<template>
  <div>
    <div class="mdc-typography--display2">New Visit</div>

    <div class="mdc-typography--display1">Pet</div>
    <div class="divTable table pet">
      <div class="divTableHeading">
        <div class="divTableRow">
          <div class="divTableHead">Name</div>
          <div class="divTableHead">Birth Date</div>
          <div class="divTableHead">Type</div>
          <div class="divTableHead">Owner</div>
        </div>
      </div>
      <div class="divTableBody">
        <div class="divTableRow">
          <div class="divTableCell">{{pet.name}}</div>
          <div class="divTableCell">{{pet.birthDate}}</div>
          <div class="divTableCell">{{pet.typeName}}</div>
          <div class="divTableCell">{{pet.owner.firstName + ' ' + pet.owner.lastName}}</div>
        </div>
      </div>
    </div>

    <div class="new-visit">
      <date-picker id="visit_date" format="yyyy-MM-dd" label="Visit Date"
                   v-model="model.visitDate" validate="required"></date-picker>
      <text-field id="name" fullwidth="true" label="Description"
                  v-model="model.description" validate="required|max:255"></text-field>
      <ripple-button label="Cancel" mdcClass="mdc-button--accent" @click="onCancelClick"></ripple-button>
      <ripple-button label="Save" mdcClass="mdc-button--primary" @click="onOkClick"></ripple-button>
    </div>

    <div class="mdc-typography--display1">Previous Visits</div>

    <div class="divTable table pets-visits">
      <div class="divTableHeading">
        <div class="divTableRow">
          <div class="divTableHead">Date</div>
          <div class="divTableHead">Description</div>
        </div>
      </div>
      <div class="divTableBody">
        <div class="divTableRow" v-for="visit in pet.visits">
          <div class="divTableCell">{{visit.visitDate}}</div>
          <div class="divTableCell">{{visit.description}}</div>
        </div>
      </div>
    </div>
    <save-dialog ref="saveDialog" @ok="onSave"></save-dialog>
  </div>
</template>

<script>
  import axios from 'axios'
  import TextField from '@/components/parts/TextField'
  import DatePicker from '@/components/parts/DatePicker'
  import RippleButton from '@/components/parts/RippleButton'
  import SaveDialog from '@/components/parts/SaveDialog'

  export default {
    name: 'visit-form',
    components: {
      TextField,
      DatePicker,
      RippleButton,
      SaveDialog
    },
    data () {
      return {
        model: {
          visitDate: null,
          description: '',
          petId: null
        },
        pet: {
          name: '',
          birthDate: null,
          typeId: null,
          typeName: '',
          owner: {},
          visits: []
        }
      }
    },
    mounted () {
      let vm = this

      if (!isFinite(vm.$route.params.id)) {
        vm.$router.push({name: 'error', params: {msg: 'Owner not found.'}})
        return
      }
      if (!isFinite(vm.$route.params.petId)) {
        vm.$router.push({name: 'error', params: {msg: 'Pet not found.'}})
        return
      }

      vm.model.petId = parseInt(vm.$route.params.petId)
      axios.get('/api/owners/' + vm.$route.params.id).then((response) => {
        vm.pet.owner = response.data
        Object.assign(vm.pet, response.data.pets.find(p => (p.id === vm.model.petId)))
      }).catch((err) => {
        vm.$router.push({name: 'error', params: {msg: err.message}})
      })
    },
    methods: {
      onOkClick () {
        this.$refs.saveDialog.show()
      },
      onCancelClick () {
        this.$router.go(-1)
      },
      onSave () {
        let vm = this
        window.preLoader.show()

        axios.post('/api/visits/new', vm.model).then((response) => {
          vm.$router.push({name: 'owner-show', params: {id: vm.$route.params.id}})
        }).catch((err) => {
          vm.$router.push({name: 'error', msg: err.message})
        })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  div.table {
    margin-top: 20px;
    margin-bottom: 20px;
  }

  .divTableCell:nth-of-type(1) {
    width: 30%;
  }

  .divTableCell:nth-of-type(2) {
    width: 20%;
  }

  .divTableCell:nth-of-type(3) {
    width: 20%;
  }

  .divTableCell:nth-of-type(4) {
    width: 30%;
  }

  .new-visit {
    margin-bottom: 30px;
  }

  dt {
    font-weight: bold;
  }

  .pets-visits .divTableCell {
    vertical-align: top;
  }
</style>
