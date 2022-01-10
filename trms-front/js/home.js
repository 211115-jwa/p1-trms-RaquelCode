checkLogin();

setTimeout(getWelcome,3000);

let mainDiv = document.getElementById('info');

function getWelcome(){
    if (!loggedInPerson) {
        
    openLogin();

} else if (loggedInPerson.role.roleId < 11) {// manager welcome
   console.log(loggedInPerson);
   mainDiv.innerHTML = `<h3>Welcome, ${loggedInPerson.firstName + " " + loggedInPerson.lastName + "  "}!</h3>
   <p>This is the TRMS Admin Site. Here's a guide to what you can do here: </p>
   <ul>
    <li>Looking to Search for a reimbusement request? Try "View Requests by Id".</li>
       <li>Looking to submit a reimbusement request? Try "Submit Requests".</li>
       <li>want to check Reimbursements Pending your Approval? Try Check Pending Requests</li>
       <li>Need to check on your submited reimbursements?Try "View Your Requests".</li>
   </ul>`;
 }else{
    mainDiv.innerHTML = `<h3>Welcome, ${loggedInPerson.firstName + " " + loggedInPerson.lastName + "  "}!</h3>
    <p>This is the TRMS. Here's a guide to what you can do here: </p>
    <ul>
        <li>Looking to submit a reimbusement request? Try "Submit Requests".</li>
        <li></li>
        <li>Need to check on your submited reimbursements?Try "View Your Requests".</li>
    </ul>`;
}



}
