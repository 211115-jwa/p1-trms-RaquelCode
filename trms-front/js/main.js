

getEmployees();

async function getEmployees() {
    let response = await fetch(reqAppUrl + '/employee/');  //requestor/4');
   
    if (response.status === 200) {
        let employees = await response.json();
        console.log(employees);
        showEmployees(employees);
    }
}



function showEmployees(employees) {
    let employeesTable = document.getElementById('employee');//all

    
    for (let req of employees) {
        let rowForEmployees = document.createElement('tr');

       
        for (let field in employees) {
            let column = document.createElement('td');
           
            rowForEmployees.appendChild(column);
        }
        employeesTable.appendChild(rowForEmployees);
     
    }
}