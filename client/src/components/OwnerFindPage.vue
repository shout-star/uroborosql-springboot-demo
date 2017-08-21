<template>
  <div>
    <div class="mdc-typography--display2">Find Owners</div>
    <div class="add-owner">
      <ripple-button label="Add Owner" @click="onAddClick"></ripple-button>
    </div>
    <text-field id="lastname" label="Last name" v-model="model.lastName"></text-field>
    <div>
      <ripple-button label="Find Owner" @click="onFindClick"></ripple-button>
    </div>

    <div class="divTable table" v-if="owners.length > 0">
      <div class="divTableHeading">
        <div class="divTableRow">
          <div class="divTableHead">Name</div>
          <div class="divTableHead">Address</div>
          <div class="divTableHead">City</div>
          <div class="divTableHead">Telephone</div>
          <div class="divTableHead">Pets</div>
        </div>
      </div>
      <div class="divTableBody">
        <div class="divTableRow" v-for="o in owners">
          <div class="divTableCell">
            <router-link :to="'/owners/' + o.id">{{o.firstName + ' ' + o.lastName}}</router-link>
          </div>
          <div class="divTableCell">{{o.address}}</div>
          <div class="divTableCell">{{o.city}}</div>
          <div class="divTableCell">{{o.telephone}}</div>
          <div class="divTableCell">{{o.petsName}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import TextField from '@/components/parts/TextField'
  import RippleButton from '@/components/parts/RippleButton'

  export default {
    name: 'owner-find',
    components: {
      TextField,
      RippleButton
    },
    data () {
      return {
        model: {
          lastName: ''
        },
        owners: []
      }
    },
    mounted () {
      this.onFindClick()
    },
    methods: {
      onAddClick () {
        this.$router.push({name: 'owner-new'})
      },
      onFindClick () {
        let vm = this

        axios.get('/api/owners', {
          params: {
            lastName: vm.model.lastName
          }
        }).then((response) => {
          vm.owners = response.data
        }).catch((err) => {
          vm.$router.push({name: 'error', params: {msg: err.message}})
        })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .add-owner {
    float: right;
    margin-top: -40px;
  }

  div.table {
    margin-top: 30px;
  }

  .divTableHead:nth-of-type(1) {
    width: 20%;
  }

  .divTableHead:nth-of-type(2) {
    width: 20%;
  }

  .divTableHead:nth-of-type(3) {
    width: 15%;
  }

  .divTableHead:nth-of-type(4) {
    width: 15%;
  }

  .divTableHead:nth-of-type(5) {
    width: 30%;
  }
</style>
