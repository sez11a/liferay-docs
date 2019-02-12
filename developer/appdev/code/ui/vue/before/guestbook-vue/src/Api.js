// import axios from 'axios';

export default class Api {
    static getEntries() {
        // This is a mock web service call
        // return axios.get(`https://localhost:8080/o/api/p/gb/entry`);

        // This returns mock data
        return [
          { entryId: '001', userName: 'Joe Bloggs', message: 'I am so happy you invited me!' },
          { entryId: '002', userName: 'Jane Bloggs', message: 'Who does your decorating?' }
        ]

        // Here's a service to try too
        // return axios.get(`https://jsonplaceholder.typicode.com/users`);
    }

    static addEntry(entry) {
        return fetch('http://localhost:8080/o/api/p/gb/entry', {
            method: 'POST',
            body: JSON.stringify(entry),
            headers: {
              "Content-type": "application/json; charset=UTF-8"
            }
          })
          .then(response => response.json())
          .then(json => this.console.log(json))
    }
}
