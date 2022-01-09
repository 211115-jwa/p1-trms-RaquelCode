"use strict";


let reqAppUrl = 'http://localhost:8080/';
let loggedInEmploeey;


checkLogin().then(form);


async function checkLogin() {
    let personId = localStorage.getItem('Token');
    if (personId) {
        let response = await fetch(reqAppUrl + 'users/' + personId + '/auth');
        if (response.status === 200) {
            loggedInPerson = await response.json();
        }
    }
}


