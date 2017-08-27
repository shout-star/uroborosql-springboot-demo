<template>
  <div>
    <div class="mdc-typography--display2">Veterinarians</div>

    <div class="divTable table" v-if="vets.length > 0">
      <div class="divTableHeading">
        <div class="divTableRow">
          <div class="divTableHead">Name</div>
          <div class="divTableHead">Specialties</div>
        </div>
      </div>
      <div class="divTableBody">
        <div class="divTableRow" v-for="v in vets">
          <div class="divTableCell">{{v.firstName + ' ' + v.lastName}}</div>
          <div class="divTableCell">{{v.specialtiesName}}</div>
        </div>
      </div>
    </div>

    <div class="view">
      <a :href="host + '/vets.xml'" target="_blank">View as XML</a>
      <a :href="host + '/vets.json'" target="_blank">View as JSON</a>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import BasePage from '@/components/BasePage'

  export default {
    name: 'vet-find',
    mixins: [
      BasePage
    ],
    components: {},
    data () {
      return {
        vets: [],
        host: location.protocol + '//' + (process.env.HOST || location.host)
      }
    },
    mounted () {
      let vm = this

      axios.get('/api/vets').then((response) => {
        vm.vets = response.data
      }).catch((err) => {
        vm.handleError(err)
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  div.table {
    margin-top: 30px;
  }

  .divTableHead:nth-of-type(1) {
    width: 40%;
  }

  .divTableHead:nth-of-type(2) {
    width: 60%;
  }

  div.view {
    margin-top: 20px;
  }
</style>
