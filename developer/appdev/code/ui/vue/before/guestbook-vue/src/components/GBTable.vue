<template>
  <div class="app">
    <h1>Guestbook</h1>
    <table id="example-1" style="width:100%">
        <tr>
            <th>
                Message 
            </th>
            <th>
                Name 
            </th>
        </tr>
        <tr v-for="entry in entries" :key="entry.entryId">
            <td>
              {{ entry.message }}
            </td>
            <td>
              {{ entry.userName }}
            </td>
        </tr>
    </table>
    <div class="button-footer">
      <router-link to="/GBAddEntry" tag="button">Add Entry</router-link>
    </div>
  </div>
</template>

<script>
import api from '../Api';

export default {
  name: 'GBTable',
  data () {
    return {
        entries: [],
        loading: false
    }
  },
  created() {
      // api.getEntries().then(response => {
      //   this.loading = false;
      //   this.entries = response.data;
      // }, error => {
      //   this.loading = false;
      //   this.console.error(error);
      // });

      // TODO For mock data, replace method body with the line above. 
      this.entries = api.getEntries();
  },
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #000;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

.button-footer {
  display: flex;
  margin-top: 1.5rem;
}

.button-footer > button {
  margin-right: 1rem;
  cursor: pointer;
  display: inline-block;
  border-radius: 0.25rem;
}
</style>
