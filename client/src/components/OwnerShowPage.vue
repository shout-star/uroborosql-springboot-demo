<template>
  <div>
    <div class="mdc-typography--display2">Owner Information</div>

    <div class="divTable table owner">
      <div class="divTableBody">
        <div class="divTableRow">
          <div class="divTableCell">Name</div>
          <div class="divTableCell">{{model.firstName + ' ' + model.lastName}}</div>
        </div>
        <div class="divTableRow">
          <div class="divTableCell">Address</div>
          <div class="divTableCell">{{model.address}}</div>
        </div>
        <div class="divTableRow">
          <div class="divTableCell">City</div>
          <div class="divTableCell">{{model.city}}</div>
        </div>
        <div class="divTableRow">
          <div class="divTableCell">Telephone</div>
          <div class="divTableCell">{{model.telephone}}</div>
        </div>
      </div>
    </div>

    <div class="owner-buttons">
      <ripple-button label="Back" mdcClass="mdc-button--accent" @click="onBackClick"></ripple-button>
      <ripple-button label="Edit Owner" @click="onEditOwnerClick"></ripple-button>
      <ripple-button label="Add Pet" @click="onAddPetClick"></ripple-button>
    </div>

    <div class="mdc-typography--display1">Pets and Visits</div>

    <div class="divTable table pets-visits">
      <div class="divTableHeading">
        <div class="divTableRow">
          <div class="divTableHead">
            <i class="material-icons">pets</i>Pets
          </div>
          <div class="divTableHead">
            <i class="material-icons">local_hospital</i>Visits
          </div>
        </div>
      </div>
      <div class="divTableBody">
        <div class="divTableRow" v-for="pet in model.pets">
          <div class="divTableCell">
            <ripple-fab icon="edit" label="Edit Pet" mdcClass="mdc-fab--mini right"
                        @click="onEditPetClick(pet)"></ripple-fab>
            <dl>
              <dt>Name</dt>
              <dd>{{pet.name}}</dd>
              <dt>BirthDate</dt>
              <dd>{{pet.birthDate}}</dd>
              <dt>Type</dt>
              <dd>{{pet.typeName}}</dd>
            </dl>
          </div>
          <div class="divTableCell">
            <ripple-fab icon="add" label="Add Visit" mdcClass="mdc-fab--mini right"
                        @click="onAddVisitClick(pet)"></ripple-fab>
            <ul class="mdc-list mdc-list--two-line mdc-list--dense mdc-list--avatar-list">
              <li class="mdc-list-item" v-for="visit in pet.visits">
                <span class="mdc-list-item__start-detail grey-bg" role="presentation">
                  <i class="material-icons" aria-hidden="true">local_hospital</i>
                </span>
                <span class="mdc-list-item__text">
                  {{visit.description}}
                  <span class="mdc-list-item__text__secondary">{{visit.visitDate}}</span>
                </span>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>


  </div>
</template>

<script>
  import axios from 'axios'
  import BasePage from '@/components/BasePage'
  import TextField from '@/components/parts/TextField'
  import RippleButton from '@/components/parts/RippleButton'
  import RippleFab from '@/components/parts/RippleFab'

  export default {
    name: 'owner-show',
    mixins: [
      BasePage
    ],
    components: {
      TextField,
      RippleButton,
      RippleFab
    },
    data () {
      return {
        model: {}
      }
    },
    mounted () {
      let vm = this

      if (!isFinite(vm.$route.params.id)) {
        vm.$router.push({name: 'error', params: {msg: 'Owner not found.'}})
        return
      }

      axios.get('/api/owners/' + vm.$route.params.id).then((response) => {
        vm.model = response.data
      }).catch((err) => {
        vm.handleError(err)
      })
    },
    methods: {
      onBackClick () {
        this.$router.push({name: 'owner-find'})
      },
      onEditOwnerClick () {
        this.$router.push({name: 'owner-edit', params: {id: this.$route.params.id}})
      },
      onAddPetClick () {
        this.$router.push({name: 'pet-new', params: {id: this.$route.params.id}})
      },
      onEditPetClick (pet) {
        this.$router.push({name: 'pet-edit', params: {id: this.$route.params.id, petId: pet.id}})
      },
      onAddVisitClick (pet) {
        this.$router.push({name: 'visit-new', params: {id: this.$route.params.id, petId: pet.id}})
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
    width: 70%;
  }

  .owner-buttons {
    margin-bottom: 30px;
  }

  dt {
    font-weight: bold;
  }

  .mdc-list-item {
    border-bottom: 1px solid darkgray;
  }

  .mdc-list-item .material-icons {
    font-size: 40px;
    color: #E57373;
  }

  .pets-visits .divTableCell {
    vertical-align: top;
  }
</style>
