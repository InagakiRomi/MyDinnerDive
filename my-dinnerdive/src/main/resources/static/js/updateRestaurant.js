import {
    getRestaurantId,
    getRestaurantName,
    getCategory,
    getVisitedCount,
    getLastSelectedAt,
    getNote,
    getImageUrl,
    getHeaders
} from './modules/restaurantDataBuilder.js';

document.addEventListener("DOMContentLoaded", function (){
    //同步餐廳分類資料庫
    const categorySelect = document.getElementById("category");
    const selectedValue = categorySelect.getAttribute("value");
    if (selectedValue) {
        categorySelect.value = selectedValue;
    }

    const preventForm = document.getElementById("updateForm");
    preventForm.addEventListener("submit", preventFormSubmit);

    function preventFormSubmit(event) {
        event.preventDefault();
        updateRestaurant();
    }
})

function updateRestaurant(){

    var restaurantJson = {
        ...getRestaurantName(),
        ...getCategory(),
        ...getVisitedCount(),
        ...getLastSelectedAt(),
        ...getNote(),
        ...getImageUrl()
    }

    fetch(`/restaurants/${getRestaurantId()}`, {
        method: 'PUT',
        headers: getHeaders(),
        body: JSON.stringify(restaurantJson)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = "/dinnerHome/listRestaurant";
            alert("修改成功！");
        } else {
            alert("修改失敗！請確認資料是否正確");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("發生錯誤，請稍後再試！");
    });
}