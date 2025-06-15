import {
    getRestaurantId,
    getRestaurantName,
    getCategory,
    getVisitedCount,
    getLastEat,
    getNote,
    getImageUrl,
    getHeaders
} from './modules/restaurantDataBuilder.js';

const updateButton = document.getElementById("update-btn");
updateButton.addEventListener("click", updateRestaurant);

function updateRestaurant(){

    var restaurantJson = {
        ...getRestaurantName(),
        ...getCategory(),
        ...getVisitedCount(),
        ...getLastEat(),
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
            window.location.href = "/listRestaurant";
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

document.addEventListener("DOMContentLoaded", () => {
    const categorySelect = document.getElementById("category");
    const selectedValue = categorySelect.getAttribute("value");
    if (selectedValue) {
        categorySelect.value = selectedValue;
    }
});