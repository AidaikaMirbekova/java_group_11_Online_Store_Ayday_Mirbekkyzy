const baseUrl = 'http://localhost:8888';

//Sign UP
$('#sign_up').on('submit', (e) => {
    e.preventDefault();
    let form = new FormData(e.target)
    let data = JSON.stringify(Object.fromEntries(form))
    console.log(data)
    axios.post(baseUrl + '/register', data, {
        cache: 'no-cache',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            console.log(response.status)
        })
    $('#exampleModal').modal('toggle');
    $('#sign_up').trigger('reset');
})

const userInfo = document.querySelector('.user_info')
const profile = document.querySelector(".card_profile")
// Sign IN

$('#sign_in').on('submit', e => {
    e.preventDefault();
    let form = new FormData(e.target)
    let user = {
        username: form.get('username'),
        password: form.get('password')
    }

    localStorage.setItem('user', JSON.stringify(user))
    searchUser().then(data => console.log(data))
    $('#staticBackdrop').modal('toggle');
    $('#sign_in').trigger('reset');
    createUserInfo();
})


let getAuth = function () {
    let userFormStorage = localStorage.getItem('user')
    let userJson = JSON.parse(userFormStorage)
    return userJson;
}
//show info about authentication user
let userAuth = getAuth()
let user = {
    username: userAuth.username,
    password: userAuth.password
};
let user_name = user.username

let createUserInfo = function () {
    userBlock = document.createElement("nav")
    userBlock.className = "user_block"
    userBlock.innerHTML = `<p>${user_name}</p>`
    userInfo.append(userBlock)

//create block info about authentication user in page profile
    profileBlock = document.createElement("div")
    profileBlock.className = "profile_block"
    profileBlock.innerHTML = `<h5>${user_name}</h5>`
    profile.append(profileBlock)

}
createUserInfo();


let searchUser = async function () {
    let userAuth = getAuth()

    let user = {
        username: userAuth.username,
        password: userAuth.password
    };
    let searchedUser = await fetch('http://localhost:8888/login', {
        method: 'post',
        // mode:'cors',
        headers: {
            'Authorization': 'Basic ' + window.btoa(user.username + ':' + user.password),
        }
    })
        .then(response => {
            return response.json()
        })
        .then(data => searchedUser = data)
    return searchedUser
}

//remove localstorage info about authentication user
$('#log_out').on('click', e => {
    e.preventDefault();
    localStorage.removeItem('user');
    document.querySelector('.user_block').remove();
    document.querySelector('.profileBlock').remove();
})

