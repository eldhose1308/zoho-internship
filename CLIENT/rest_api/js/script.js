

let userDetails = [];
let filteredUsers = [];

const apiUrl = "http://localhost:4000/";
const apiEndpoints = {
    users: "users/"
};


const searchValue = document.getElementById("search_value");
const createForm = document.getElementById("create-form");
const submitBtn = document.getElementById("submit_btn");
const alertMsg = document.getElementById("alert-msg");

const updateForm = document.getElementById("update-form");
const updateBtn = document.getElementById("update_btn");

const usersList = document.getElementById("users-list");

const progressBar = document.getElementById("progress");


const entriesValue = document.getElementById("entries_value");
const pagination = document.getElementById("pagination");
const paginationInfo = document.getElementById("pagination-info");


let currentPage = 1;
let perPage = entriesValue.value;
let totalEntries = 0;
let totalPages = 0;

let startIndex = 0, endIndex = perPage;

document.addEventListener("DOMContentLoaded", function () {
    listUser();
});






pagination.addEventListener('click', function (e) {
    e.preventDefault();
    if (e.target.tagName == 'UL')
        return;

    pagination.querySelectorAll("a[class='active']").forEach(element => element.classList.remove('active'));

    e.target.classList.add('active');

    const paginationItem = e.target.parentElement;
    const selectedPage = paginationItem.dataset.id;

    currentPage = selectedPage;


    startIndex = Number(currentPage - 1) * perPage;
    endIndex = (Number(startIndex)) + Number(perPage);


    // console.log(startIndex, endIndex);
    filteredUsers = userDetails.slice(startIndex, endIndex);

    searchValueInTable();
    renderUsers();

});





entriesValue.addEventListener('keyup', function (e) {
    perPage = entriesValue.value;
    totalPages = Math.ceil(totalEntries / perPage);
    startIndex = 0;
    endIndex = (Number(startIndex)) + Number(perPage);

    filteredUsers = userDetails.slice(startIndex, endIndex);
    searchValueInTable();

    renderUsers();

});


searchValue.addEventListener('keyup', function (e) {

    searchValueInTable();

    // let userRowText = '';
    // const usersRows = usersList.querySelectorAll('tr');

    // if (keyword.length == 0) {
    //     usersRows.forEach(userRow => { userRow.style.display = 'table-row'; });
    //     return;
    // }

    // usersRows.forEach(userRow => { userRow.style.display = 'none'; });
    // usersRows.forEach(userRow => {
    //     userRowText = userRow.textContent.toLowerCase();
    //     if (userRowText.includes(keyword))
    //         userRow.style.display = 'table-row';
    // });
});



function searchValueInTable() {
    const keyword = searchValue.value.toLowerCase().trim();

    let searchedUserDetails = userDetails.filter(user =>
        (Object.values(user).join(" ").includes(keyword))
    );

    totalEntries = searchedUserDetails.length;
    filteredUsers = searchedUserDetails.slice(startIndex, endIndex);

    renderUsers();
}


submitBtn.addEventListener('click', async function (e) {
    e.preventDefault();
    const user_name = createForm.querySelector("#user_name").value;
    const user_email = createForm.querySelector("#user_email").value;
    const user_mobile = createForm.querySelector("#user_mobile").value;

    if (!validateForm(createForm))
        return false;

    let user = {
        'user_name': user_name,
        'user_email': user_email,
        'user_mobile': user_mobile
    };


    const apiResponse = await axios(apiUrl + apiEndpoints.users, 'POST', user);
    if (apiResponse)
        setMessage("User Saved", true);
    else
        setMessage("User Could not be Saved", false);


    createForm.reset();
    closeOffCanvas();
    listUser();
});



updateBtn.addEventListener('click', async function (e) {
    e.preventDefault();

    const id = Number(updateForm.querySelector("#id").value);
    const user_name = updateForm.querySelector("#user_name").value;
    const user_email = updateForm.querySelector("#user_email").value;
    const user_mobile = updateForm.querySelector("#user_mobile").value;

    if (!validateForm(updateForm))
        return false;


    let user = {
        'user_name': user_name,
        'user_email': user_email,
        'user_mobile': user_mobile
    };

    const apiResponse = await axios(apiUrl + apiEndpoints.users + id, 'PATCH', user);
    if (apiResponse)
        setMessage("User Updated", true);
    else
        setMessage("User Could not be Updated", false);

    updateForm.reset();
    closeOffCanvas();
    listUser();
});




async function listUser() {
    userDetails = await axios(apiUrl + apiEndpoints.users);
    totalEntries = userDetails.length;
    filteredUsers = userDetails.slice(startIndex, endIndex);
    renderUsers();

}


async function editUser($this) {
    const userID = Number($this.dataset.id);

    const id = updateForm.querySelector("#id");
    const user_name = updateForm.querySelector("#user_name");
    const user_email = updateForm.querySelector("#user_email");
    const user_mobile = updateForm.querySelector("#user_mobile");

    let user = 0;
    const apiResponse = await axios(apiUrl + apiEndpoints.users + userID, 'GET');
    if (!apiResponse) {
        setMessage('Unable to Access the User', false);
        return;
    }
    user = apiResponse;

    id.value = user.id;
    user_name.value = user.user_name;
    user_email.value = user.user_email;
    user_mobile.value = user.user_mobile;

    openOffCanvas('edit-offcanvas');
    scrollToTop();

}

async function deleteUser($this) {
    if (!confirm('Are you sure that you want to delete this record ?'))
        return;

    const userID = Number($this.dataset.id);

    const apiResponse = await axios(apiUrl + apiEndpoints.users + userID, 'DELETE');
    if (apiResponse)
        setMessage("User Deleted", true);
    else
        setMessage("User Could not be Deleted", false);
    listUser();
}




function renderUsers() {

    // console.log(startIndex,endIndex);
    // searchValueInTable();

    usersList.innerHTML = "";
    let usersHtml = "";
    let actionBtns = "";
    usersHtml += filteredUsers.map(user => {
        actionBtns = "";
        actionBtns += `<button class="action-btn" onclick=editUser(this) data-id="${user.id}">Edit <i class="fa fa-pencil"></i></button>`;
        actionBtns += `<button class="action-btn" onclick=deleteUser(this) data-id="${user.id}">Delete <i class="fa fa-trash"></i></button>`;
        return `<tr>
            <td>${user.id}</td>
            <td>${user.user_name}</td>
            <td>${user.user_email}</td>
            <td>${user.user_mobile}</td>
            <td>${actionBtns}</td>
        </tr>
        `;
    }).join("");

    if (filteredUsers.length == 0)
        usersHtml = "No entries found";
    usersList.innerHTML = usersHtml;

    generatePagination();
}


function generatePagination(newTotalEntries = 0) {
    totalEntries = newTotalEntries ? newTotalEntries : totalEntries;

    totalPages = Math.ceil(totalEntries / perPage);

    pagination.innerHTML = '';

    let paginationHtml = "";
    if (currentPage > 1)
        paginationHtml += `<li class="pagination-item" data-id="${currentPage - 1}"><a href="#">Previous</a></li>`;

    for (let i = 1; i <= totalPages; i++)
        paginationHtml += `<li class="pagination-item" data-id="${i}"><a href="#" class="${currentPage == i ? 'active' : ''}" >${i}</a></li>`;

    if (currentPage < totalPages)
        paginationHtml += `<li class="pagination-item" data-id="${Number(currentPage) + 1}"><a href="#">Next</a></li>`;

    pagination.innerHTML = paginationHtml;

    let endingCount = totalEntries < endIndex ? totalEntries : endIndex;
    let startingCount = totalEntries == 0 ? 0 : startIndex + 1;
    paginationInfo.textContent = `Showing ${startingCount} to ${endingCount} of ${totalEntries} entries`;
}


/**
 *  Common Helper Functions
 * */


async function axios(url, method = 'GET', data = {}) {
    return new Promise(function (resolve, reject) {
        let xhr = new XMLHttpRequest();

        xhr.open(method, url);
        xhr.setRequestHeader('Content-Type', 'application/json');

        xhr.send(JSON.stringify(data));

        xhr.onload = function () {
            if (!(xhr.status >= 200 && xhr.status < 300)) {
                setMessage(`Error ${xhr.status}: ${xhr.statusText}`);
                reject(`Error ${xhr.status}: ${xhr.statusText}`);
                return false;
            }

            const fetch_response = JSON.parse(xhr.response);
            resolve(fetch_response);

        };

        xhr.onerror = function () {
            setMessage('Something went wrong,Please try again');
            reject('Something went wrong,Please try again');
            return false;
        };

        xhr.onprogress = function (event) {
            if (event.lengthComputable) {
                const percentComplete = (event.loaded / event.total) * 100;
                progressBar.style.display = "block";
                progressBar.style.width = percentComplete + "%";
            }

            progressBar.style.display = "none";
            progressBar.style.width = 0 + "%";

        };


        xhr.upload.onprogress = function (event) {
            const percentComplete = (event.loaded / event.total) * 100;
            progressBar.style.display = "block";
            progressBar.style.width = percentComplete + "%";
            alert(`Uploaded ${event.loaded} of ${event.total} bytes`);
        };

        xhr.upload.onload = function () {
            alert(`Upload finished successfully.`);
            progressBar.style.display = "none";
            progressBar.style.width = 0 + "%";
        };


    });


}



function openOffCanvas(target) {
    const offCanvas = document.querySelector("#" + target);
    offCanvas.style.visibility = 'visible';
    offCanvas.style.transition = 'transform 0.5s ease';
    offCanvas.style.transform = 'translateX(0%)';
}

function closeOffCanvas($this = '') {
    if ($this == '')
        document.querySelectorAll('.offcanvas').forEach(offCanvas => {
            offCanvas.style.transition = 'transform 0.5s ease';
            offCanvas.style.transform = 'translateX(100%)';
        });
    else {
        const offCanvas = $this.parentElement.parentElement;
        offCanvas.style.transition = 'transform 0.5s ease';
        offCanvas.style.transform = 'translateX(100%)';
    }
}


function scrollToTop() {
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
}


function validateForm(form) {
    let errorFlag = true;
    let errorMsgInfo = [];
    const inputElements = form.querySelectorAll('input[data-validation]');
    inputElements.forEach(formElement => {
        var errorMsg = "";
        const label = formElement.parentElement.querySelector('label').textContent;
        const validation = (formElement.dataset.validation).split("|");

        validation.map(constraint => {

            if (constraint == 'required')
                if (isEmpty(formElement.value))
                    errorMsg += "<p>" + label + " is required </p>";

            if (constraint == 'numeric')
                if (isNumeric(formElement.value))
                    errorMsg += "<p>" + label + " should be only numbers </p>";

            if (constraint == 'valid_email')
                if (isEmail(formElement.value))
                    errorMsg += "<p>" + label + " should be a valid email </p>";

            if (constraint.includes('min_length')) {
                var lengthLimit = (constraint.split("-").length > 1) ? Number(constraint.split("-")[1]) : 0;
                if (isMinLength(formElement.value, lengthLimit))
                    errorMsg += "<p>" + label + " should be of minimum " + lengthLimit + " length</p>";
            }


            if (constraint.includes('max_length')) {
                var lengthLimit = (constraint.split("-").length > 1) ? Number(constraint.split("-")[1]) : 0;
                if (isMaxLength(formElement.value, lengthLimit))
                    errorMsg += "<p>" + label + " should be of maximum " + lengthLimit + " length</p>";
            }


            if (constraint.includes('exact_length')) {
                var lengthLimit = (constraint.split("-").length > 1) ? Number(constraint.split("-")[1]) : 0;
                if (isExactLength(formElement.value, lengthLimit))
                    errorMsg += "<p>" + label + " should be exactly " + lengthLimit + " length</p>";
            }

            if (errorMsg.length > 0)
                errorFlag = false;
        });

        errorMsgInfo.push({
            "element": formElement,
            "message": errorMsg
        });
    });


    setErrorMessages(errorMsgInfo);

    return errorFlag;
}


function setErrorMessages(msgInfo) {
    const errorDetails = document.querySelectorAll('.error-details');
    errorDetails.forEach(element => element.remove());

    msgInfo.map(info => {
        const { element, message } = info;
        const parentElement = element.parentElement;

        if (message.length > 0)
            parentElement.insertAdjacentHTML('beforeend', `<span class="error-details">${message}</span>`);
    });
}

function setMessage(message = '', status = true) {
    let alertStatus = (!status) ? 'error' : 'success';
    alertMsg.classList = '';
    alertMsg.classList.add('alert');
    alertMsg.classList.add('alert-' + alertStatus);
    alertMsg.innerHTML = message + `<button onclick="closeAlert(this)" class="close-alert float-right">&times</button>`;
    alertMsg.style.display = "block";

    scrollToTop();

}



function closeAlert($this) {
    $this.parentElement.style.display = "none"
}



function isEmpty(value) {
    if (value == '' || value.length == 0)
        return true;


    return false;
}

function isEmail(value) {
    if (! /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value))
        return true;


    return false;
}

function isNumeric(value) {
    if (isNaN(value) || value.length == 0) {
        return true;
    }

    return false;
}

function isExactLength(value, length) {
    if (value.length != length)
        return true;


    return false;
}
function isMaxLength(value, length) {
    if (value.length > length)
        return true;


    return false;
}
function isMinLength(value, length) {
    if (value.length < length)
        return true;


    return false;
}