import {
    getCurrentId
} from './randomRestaurant.js';

const chooseutton = document.getElementById("choose-btn");
chooseutton.addEventListener("click", chooseRestaurant);

function chooseRestaurant(){
    const id = getCurrentId();

    fetch(`/choose/${id}`, {
        method: 'PATCH'
    })
    .then(response => {
        if (response.ok) {
            window.location.href = "/dinnerHome";
            alert("選擇成功！");
        } else {
            alert("請先點【抽！】選餐廳");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("發生錯誤，請稍後再試！");
    });
}