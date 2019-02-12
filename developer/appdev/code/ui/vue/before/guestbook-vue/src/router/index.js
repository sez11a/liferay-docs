import Vue from 'vue'
import Router from 'vue-router'
import GBAddEntry from '../components/GBAddEntry.vue'
import GBTable from '../components/GBTable.vue'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: "/",
            redirect: {
                name: "GBTable"
            }
        },
        {
            path: '/GBTable',
            name: 'GBTable',
            component: GBTable
        },
        {
            path: '/GBAddEntry',
            name: 'GBAddEntry',
            component: GBAddEntry
        }
    ]
})
