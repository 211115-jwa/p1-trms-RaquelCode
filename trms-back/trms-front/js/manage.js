
checkLogin();

setTimeout(getRequests,1000);

async function getRequests() {
    
   let id = loggedInPerson.empId;
   let tokenHeader = {"Token":loggedInPerson.id};
   let response = await fetch(reqAppUrl + 'requests/pending/' + id, { headers:tokenHeader});
    

   if (response.status === 200) {
    let requests = await response.json();
    console.log(requests);
    showRequests(requests);
}

}

function showRequests(requests) {
    let i = 0;
  
        
    

    let requestsTable = document.getElementById('allRequests');//all
   


     
      for (let req of requests) {
        let rowForRequests = document.createElement('tr');
        let submitted = requests[i].submittedAt;
        for(let i = 0;i<6;i++){
                if(submitted[i] < 10){
                    submitted[i] = ('0' + submitted[i]);
                }
        }
       
        sub = (submitted[0] + '/' + submitted[1] + '/' + submitted[2] + ' ' + submitted[3] + ':' + submitted[4] + ':' + submitted[5] );
        let status = (requests[i].status);
        stat = (status.statusId + ': ' + status.name);
        let eventType = (requests[i].eventType);
        eType = (eventType.eventId + ': ' + eventType.name);
        let gradingFormat = (requests[i].gradingFormat);
        gFormat = (gradingFormat.formatId + ': ' + gradingFormat.name);
        let eventTime = (requests[i].eventTime);
        eTime = (eventTime[0] + ':' + eventTime[1]);
        let eventDate = (requests[i].eventDate);
        eDate = (eventDate[1] + '/' + eventDate[2] + '/' + eventDate[0]);
        let employees = (requests[i].requestor);
        let cost = (requests[i].cost);
       // console.log(employees);
       let formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
       });

       for (let field in req) {
        let column = document.createElement('td');
            if (field == 'reqId'){
                column = document.createElement('td');
                column.innerText = req[field];
                rowForRequests.appendChild(column);
            }else if (field == 'requestor'){
            let column1 = document.createElement('td');
            column1.innerText = (employees.firstName +" " + employees.lastName);
            rowForRequests.appendChild(column1);
            column = document.createElement('td');
            column.innerText = employees.empId;
            rowForRequests.appendChild(column);
            }else if(field == 'eventDate'){ 
                column = document.createElement('td');
                column.innerText = (eDate);
                rowForRequests.appendChild(column);
            }else if(field == 'eventTime'){ 
                column = document.createElement('td');
                column.innerText = (eTime);
                rowForRequests.appendChild(column);
            }else if(field == 'location'){ 
                column = document.createElement('td');
                column.innerText = req[field];
                rowForRequests.appendChild(column);
            }else if(field == 'description'){ 
                    column = document.createElement('td');
                    column.innerText = req[field];
                    rowForRequests.appendChild(column);
           }else if(field == 'cost'){ 
                column = document.createElement('td');
                column.innerText = (formatter.format(cost));
                rowForRequests.appendChild(column);
            }else if(field == 'gradingFormat'){ 
                column = document.createElement('td');
                column.innerText = (gFormat);
                rowForRequests.appendChild(column);
            }else if(field == 'eventType'){ 
                column = document.createElement('td');
                column.innerText = (eType);
                rowForRequests.appendChild(column);
            }else if(field == 'status'){ 
                column = document.createElement('td');
                column.innerText = (stat);
                rowForRequests.appendChild(column);
            }else if(field == 'submittedAt'){ 
                column = document.createElement('td');
                column.innerText = (sub);
                rowForRequests.appendChild(column);
            }else{
               
            //     column = document.createElement('td');
            //      column.innerHTML=`<button id="view${employees.empId}">View</button>`;// open the view and update page
            //  rowForRequests.appendChild(column1);        
             }
             
        }
        i++;
        requestsTable.appendChild(rowForRequests);
         
    }

 
    document.getElementById(`accbutton`).onclick = acceptRequests;
    document.getElementById(`rejbutton`).onclick = rejectRequests;

}


async function acceptRequests(){// sends an accept

    let userInput = document.getElementById('input').value;
    let tokenHeader = {"Token":loggedInPerson.id};
    let response = await fetch(reqAppUrl + 'requests/approve/' + userInput, { headers:tokenHeader});


    if (response.status === 200){// || response == '') {
        alert('Approved Request: ' + userInput + " \r\nand Posted a Comment.");
        location.reload();

    }else if(response == ''){
        alert('response is empty');// fix later
    
    }else{
    alert('404 Not Found: no Request by ID exists');
    }
}

async function rejectRequests(){// sends a reject
    let userInput = document.getElementById('input').value;

    let tokenHeader = {"Token":loggedInPerson.id};
    let response = await fetch(reqAppUrl + 'requests/reject/' + userInput, { headers:tokenHeader});

    
    if (response.status === 200){// || response == '') {
        alert('Rejected Request: '+ userInput + "\r\n and Posted comment");
    location.reload();

    }else if(response == ''){
        alert('response is empty');//
    
    }else{
    alert('404 Not Found: no Request by ID exists');
    }
}