const btnLogin = document.getElementById('btnLogin');

btnLogin.onclick = () => {
    const header = document.getElementById('header');
    if(header.style.display === 'none'){
        header.style.display = 'block';
    } else {
        header.style.display = 'none';
    }
}

let requests;

getData().then(loadTable);

async function getData() {
    let url='http://localhost:8080/requests/requestor/5'
    // let personId = localStorage.getItem('Token');
    // if (personId) {
    let response = await fetch(url);
    if (response.status === 200) {
        requests = await response.json();
    }
    // }
}

function loadTable(){
    
    const reqTable = document.getElementById('requestsTable');
    console.log(requests);

    for(let request of requests) {
        console.log(request);
        let row = document.createElement('tr');

        const id = document.createElement('td');
        id.scope = 'col';
        id.innerText = request['reqId'];
        row.appendChild(id);
        
        const eventDate = document.createElement('td');
        eventDate.scope = 'col';
        eventDate.innerText = request['eventDate'];
        row.appendChild(eventDate);
        
        const eventTime = document.createElement('td');
        eventTime.scope = 'col';
        eventTime.innerText = request['eventTime'];
        row.appendChild(eventTime);
        
        const location = document.createElement('td');
        location.scope = 'col';
        location.innerText = request['location'];
        row.appendChild(location);
        
        const description = document.createElement('td');
        description.scope = 'col';
        description.innerText = request['description'];
        row.appendChild(description);
        
        const cost = document.createElement('td');
        cost.scope = 'col';
        cost.innerText = request['cost'];
        row.appendChild(cost);
        
        const gradingFormat = document.createElement('td');
        gradingFormat.scope = 'col';
        gradingFormat.innerText = request['gradingFormat']['name'];
        row.appendChild(gradingFormat);
        
        const eventType = document.createElement('td');
        eventType.scope = 'col';
        eventType.innerText = request['eventType']['name'];
        row.appendChild(eventType);
        
        const status = document.createElement('td');
        status.scope = 'col';
        status.innerText = request['status']['name'];
        row.appendChild(status);
        
        const submittedAt = document.createElement('td');
        submittedAt.scope = 'col';
        submittedAt.innerText = request['submittedAt'];
        row.appendChild(submittedAt);
        // for(let key in request) {
        //     const data = document.createElement('td');
        //     data.scope = 'col';
        //     data.innerText = request[key];
        //     row.appendChild(data);
        //     console.log(key);
        // }
        reqTable.appendChild(row);
    }

}

function loadUserInfo(id){
    const lblFullName = document.getElementById('fullName');
    const lblRole = document.getElementById('role');
    const lblSupervisor = document.getElementById('supervisor');
    const lblDepartment = document.getElementById('department');

    lblFullName.innerText = 'Carlo Del Mundo';
    lblRole.innerText = 'Role: Associate';
    lblSupervisor.innerText = 'Supervisor: Sierra Nicholes';
    lblDepartment.innerText = 'Department: Engineering Dept.'
}


