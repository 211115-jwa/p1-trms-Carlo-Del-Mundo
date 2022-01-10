let url = 'http://localhost:8080/';
let loggedInUser;

window.onload = () => {
    checkLogin().then(init())
}

function init() {
    let info = document.getElementById('info');
    
    if(loggedInUser){
        if(loggedInUser['role']['roleId'] > 1) {
            document.getElementById('btnRequest').style.display = 'none';
        }
        console.log(loggedInUser);
        info.style.display = 'block'
        loadOptions()
            .then(loadTable(loggedInUser['empId']))
            .then(loadUserInfo());
    } else {
        info.style.display = 'none';
    }
    
    document.getElementById('btnLogin').addEventListener('click',logIn);
    document.getElementById('btnLogOut').addEventListener('click',logOut);
    document.getElementById('btnSubmit').addEventListener('click',submitRequest);
    document.getElementById('btnApprove').addEventListener('click',approve);
    document.getElementById('btnReject').addEventListener('click',reject);
}

async function checkLogin() {
    
    let userId = localStorage.getItem('Token');
    console.log('check login - ' + userId);
    if(userId) {
        let response = await fetch(url + 'users/' + userId + '/auth');
        if(response.status === 200) {
            loggedInUser = await response.json();
            init();
        }
    }
}

async function submitRequest() {
    let request = {
        'userId':loggedInUser.empId,
        'eventDate':document.getElementById('txtEventDate').value,
        'eventTime':document.getElementById('txtEventTime').value + ':59',
        'location':document.getElementById('txtLocation').value,
        'cost':document.getElementById('txtCost').value,
        'gradingFormat':document.getElementById('drpGrade').value,
        'eventType':document.getElementById('drpEvent').value,
        'description':document.getElementById('txtDescription').value
    }

    let response = await fetch(url + 'requests/', {method:'POST',body:JSON.stringify(request)});
    if(response.status === 200) {
        checkLogin().then(init());
        console.log(response);
    } else if(response.status === 404) {
        let msg = await response.text();
        alert(msg);
    }
    location.reload()
}

async function approve() {
    let id = document.getElementById('hdnRequestID').value;
    let request = {
        'requestId':id,
    }

    let response = await fetch(url + 'requests/approve', {method:'POST',body:JSON.stringify(request)});
    if(response.status === 200) {
        checkLogin().then(init());
        console.log(response);
    } else if(response.status === 404) {
        let msg = await response.text();
        alert(msg);
    }
    location.reload()
}

async function reject() {
    let requestId = document.getElementById('hdnRequestID').value;
    let comment = document.getElementById('txtComment').value;
    let request = {
        'id':loggedInUser['empId'],
        'requestId':requestId,
        'comment':comment
    }

    let response = await fetch(url + 'requests/reject', {method:'POST',body:JSON.stringify(request)});
    if(response.status === 200) {
        checkLogin().then(init());
        console.log(response);
    } else if(response.status === 404) {
        let msg = await response.text();
        alert(msg);
    }
    location.reload()
}

async function logIn() {
    let username = document.getElementById('txtUsername').value;
    let password = document.getElementById('txtPassword').value;

    let credentials = {
        'username':username,
        'password':password
    };
    console.log(url + 'users/auth');
    console.log(credentials);
    
    let response = await fetch(url + 'users/auth', {method:'POST',body:JSON.stringify(credentials)});
    if(response.status === 200) {
        let token = await response.text();
        localStorage.setItem('Token', token);
        checkLogin().then(init());
        console.log('After login: ' + localStorage.getItem('Token'));
    } else if(response.status === 404) {
        let msg = await response.text();
        alert(msg);
    }
}

function logOut() {
    localStorage.removeItem('Token');
    loggedInPerson=null;
    checkLogin().then(location.reload());
}

async function loadTable(id){
    let roleId = loggedInUser['role']['roleId'];
    let toggle = document.getElementById('toggleLogin');
    toggle.style.display = 'none';
    
    let requests;
    let url='http://localhost:8080/requests/requestor/' + id;

    let response = await fetch(url);
    if (response.status === 200) {
        requests = await response.json();
    }
    console.log('loadTable');
    console.log(loggedInUser['role']['roleId']);

    const reqTable = document.getElementById('requestsTable');
    console.log(reqTable);
    if(roleId == 2 || roleId == 3) {        // approver's role
        const th = document.createElement('th');
        th.scope = 'col';
        document.getElementById('reqTableHead').appendChild(th);
    }


    reqTable.inn
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
        submittedAt.innerText = request['submittedAt']['year'] + request['submittedAt']['month'] + request['submittedAt']['dayOfMonth'];
        row.appendChild(submittedAt);
        
        if(roleId == 2 || roleId == 3) {
            const tdSelect = document.createElement('td');
            tdSelect.scope = 'col';
            const btnSelect = document.createElement('button');
            btnSelect.id = String(request['reqId']);
            // data-bs-target="#approvalModal"
            // data-bs-toggle="modal"
            btnSelect.setAttribute('data-bs-target', '#approvalModal');
            btnSelect.setAttribute('data-bs-toggle', 'modal')
            btnSelect.className = "btn btn-outline-primary btn-sm";
            btnSelect.innerText = 'Select';
            btnSelect.onclick = item => {
                let button = item['path'][0];
                document.getElementById('hdnRequestID').value = button.id;
                // console.log(document.getElementById('hdnRequestID').value);
            };
            tdSelect.appendChild(btnSelect);
            row.appendChild(tdSelect);
        }

        reqTable.appendChild(row);
    }

}

async function loadOptions() {
    let options;
    let url='http://localhost:8080/requests/options';

    let response = await fetch(url);
    if (response.status === 200) {
        options = await response.json();
    }

    console.log(options);
    
    if(options) {
        let formats = options['gradingFormats'];
        let events = options['eventTypes'];

        const drpGrade = document.getElementById('drpGrade');
        const drpEvent = document.getElementById('drpEvent');

        for(format of formats) {
            const option = document.createElement('option');
            option.value = format['formatId'];
            option.text = format['name'];
            drpGrade.add(option);
        }

        for(type of events) {
            const option = document.createElement('option');
            option.value = type['eventId'];
            option.text = type['percentCovered'] + '% - ' + type['name'];
            drpEvent.add(option);
        }
    }
}

function loadUserInfo(){
    const lblFullName = document.getElementById('fullName');
    const lblRole = document.getElementById('role');
    const lblSupervisor = document.getElementById('supervisor');
    const lblDepartment = document.getElementById('department');

    lblFullName.innerText = loggedInUser['firstName'] + ' ' + loggedInUser['lastName'];
    lblRole.innerText = 'Role: ' + loggedInUser['role']['name'];
    if(loggedInUser['supervisor']) {
        lblSupervisor.innerText = 'Senior: ' + loggedInUser['supervisor']['firstName'] + ' ' + loggedInUser['supervisor']['lastName'];
    } else {
        lblSupervisor.innerText = 'N/A'
    }
    lblDepartment.innerText = 'Department: ' + loggedInUser['department']['name'];
}

