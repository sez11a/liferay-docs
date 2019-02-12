<template>
    <div class="app">
        <h1>Guestbook</h1>
        <form>
            <fieldset>
                <label>Name:
                    <input v-model="entry.userName"/>
                </label><br/>
                <label>Message:
                    <input v-model="entry.message"/>
                </label>
            </fieldset>
            <div class="button-footer">
                <button class="main-btn" v-on:click="save(entry)">Add</button>
                <button class="plain-btn" v-on:click="cancel()">Cancel</button>
            </div>
        </form>
    </div>
</template>

<script>
    import api from '../Api';
    import router from '../router'

    export default {
      name: 'GBAddEntry',
      data () {
        return {
            entry : {},

            // Mock user
            username : 'Joe Bloggs'
        }
      },
      methods: {
        save(entry) {
            api.addEntry(entry);
            router.go(-1);
        }, 
        cancel() {
            router.go(-1);
        }
    },
      props: {
        msg: String
      }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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

input {
  display: block
}
</style>
