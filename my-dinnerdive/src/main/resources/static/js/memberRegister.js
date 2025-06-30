import {
    getAccount,
    getPassword,
    getHeaders
} from './modules/memberDataBuilder.js';

document.addEventListener("DOMContentLoaded", function (){
    const preventForm = document.getElementById("loginForm");
    preventForm.addEventListener("submit", preventFormSubmit);

    function preventFormSubmit(event) {
        event.preventDefault();
        memberRegister();
    }
})

function memberRegister(){
    //轉成json格式
    var memberJson = {
        ...getAccount(),
        ...getPassword()
    }

    //傳資料給後端
    fetch('/users/register', {
        method: "POST",
        headers: getHeaders(),
        body: JSON.stringify(memberJson)
    })
    .then((response) => {
        if (response.ok) {
            alert("註冊成功！");
            window.location.href = "/dinnerHome";
        } else {
            alert("該帳號已經被註冊");
        }
    })
    .catch((error) => {
        alert("系統發生錯誤！");
    })
}