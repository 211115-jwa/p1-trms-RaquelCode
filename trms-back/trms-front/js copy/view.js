
checkLogin();

setTimeout(getRequests,3000);
async function getRequests() {
    //checkLogin().then(setupNav);

   let eid = loggedInPerson.empId;
   console.log(loggedInPerson);
    

   let tokenHeader = {"Token":loggedInPerson.id};
    let response = await fetch(reqAppUrl + 'requests/requestor/' + eid, { headers:tokenHeader});// fix logic

    if (response.status === 200){// || response == '') {
        let requests = await response.json();
        
        console.log(requests);
        showRequests(requests);

    }else if(response == ''){
        alert('response is empty');// fix later
    
    }else{
    alert('404 Not Found: no Request by ID exists');
    }
}




function showRequests(requests) {
    let tdElements = document.getElementsByTagName('td');
    for(let td of tdElements){
        td.innerText = ' ';
    }

    let requestsTable = document.getElementById('allRequests');//all
       
       let i = 0;
   //for each request
    for (let req of requests) 
    {
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
       // console.log(employees);
        let rowForRequests = document.createElement('tr');
        
      
        for (let field in req) {
           
           if (field == 'requestor'){
            let column = document.createElement('td');
            column.innerText = employees.empId;
            rowForRequests.appendChild(column);
            let column1 = document.createElement('td');
            column1.innerText = (employees.firstName +" " + employees.lastName);
            rowForRequests.appendChild(column1);

             }else if(field == 'eventDate'){ 
                let column = document.createElement('td');
                column.innerText = (eDate);
                rowForRequests.appendChild(column);
            }else if(field == 'eventTime'){ 
                let column = document.createElement('td');
                column.innerText = (eTime);
                rowForRequests.appendChild(column);
            }else if(field == 'gradingFormat'){ 
                let column = document.createElement('td');
                column.innerText = (gFormat);
                rowForRequests.appendChild(column);
            }else if(field == 'eventType'){ 
                let column = document.createElement('td');
                column.innerText = (eType);
                rowForRequests.appendChild(column);
            }else if(field == 'status'){ 
                let column = document.createElement('td');
                column.innerText = (stat);
                rowForRequests.appendChild(column);
            }else if(field == 'submittedAt'){ 
                let column = document.createElement('td');
                column.innerText = (sub);
                rowForRequests.appendChild(column);
            }else{
                let column = document.createElement('td');
                column.innerText = req[field];
                //column.innerText = req[field];
                rowForRequests.appendChild(column);

             
             }
           
        }
        i++;
            requestsTable.appendChild(rowForRequests);
         
    }
  
}
