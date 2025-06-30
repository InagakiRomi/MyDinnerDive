import {
    getRestaurantName,
    getCategory,
    getNote,
    getImageUrl,
    getHeaders
} from './modules/restaurantDataBuilder.js';

document.addEventListener("DOMContentLoaded", function (){
    const preventForm = document.getElementById("createForm");
    preventForm.addEventListener("submit", preventFormSubmit);

    function preventFormSubmit(event) {
        event.preventDefault();
        createRestaurant();
    }
})

function createRestaurant(){
    //轉成json格式
    var restaurantJson = {
        ...getRestaurantName(),
        ...getCategory(),
        ...getNote(),
        ...getImageUrl()
    }

    //傳資料給後端
    fetch('/restaurants', {
        method: "POST",
        headers: getHeaders(),
        body: JSON.stringify(restaurantJson)
    })
    .then((response) => {
        if (response.ok) {
            alert("餐廳新增成功！");
            window.location.href = "/dinnerHome/randomRestaurant";
        } else {
            alert("新增失敗，請再試一次");
        }
    })
    .catch((error) => {
        alert("系統發生錯誤！");
    })
}