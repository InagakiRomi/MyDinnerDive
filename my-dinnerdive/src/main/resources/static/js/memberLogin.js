import {
    getUsername,
    getPassword,
    getHeaders
} from './modules/memberDataBuilder.js';

document.addEventListener("DOMContentLoaded", function (){
    const preventForm = document.getElementById("loginForm");
    preventForm.addEventListener("submit", preventFormSubmit);

    function preventFormSubmit(event) {
        event.preventDefault();
        memberLogin();
    }
})

function memberLogin(){
    //轉成json格式
    var memberJson = {
        ...getUsername(),
        ...getPassword()
    }

    //傳資料給後端
    fetch('/users/login', {
        method: "POST",
        headers: getHeaders(),
        body: JSON.stringify(memberJson)
    })
    .then((response) => {
        if (response.ok) {
            alert("登入成功！");
            window.location.href = "/dinnerHome/randomRestaurant";
        } else {
            alert("尚未註冊或密碼輸入錯誤");
        }
    })
    .catch((error) => {
        alert("系統發生錯誤！");
    })
}