import {
    getRestaurantId,
    getVisitedCount,
    getLastEat,
    getHeaders
} from './modules/restaurantDataBuilder.js';

document.addEventListener("DOMContentLoaded", function (){
    const chooseBtn  = document.getElementById("choose-btn");
    chooseBtn.addEventListener("click", preventFormSubmit);

    function preventFormSubmit(event) {
        event.preventDefault();
        alert("！");
        chooseRestaurant();
    }
})

function chooseRestaurant(){

    var restaurantJson = {
    ...getVisitedCount(),
    ...getLastEat()
    }

    fetch(`/choose/${getRestaurantId()}`, {
        method: 'PUT',
        headers: getHeaders(),
        body: JSON.stringify(restaurantJson)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = "/dinnerHome";
            alert("選擇成功！");
        } else {
            alert("選擇失敗！請確認資料是否正確");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("發生錯誤，請稍後再試！");
    });
}