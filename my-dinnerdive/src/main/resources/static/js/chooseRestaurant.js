import {
    getCurrentId
} from './randomRestaurant.js';

const chooseutton = document.getElementById("choose-btn");
// 當使用者點擊按鈕時，執行 chooseRestaurant 函式
chooseutton.addEventListener("click", chooseRestaurant);

/** 使用者點選「我就吃這間」後執行的邏輯 */
function chooseRestaurant(){
    // 取得目前抽中的餐廳 ID
    const id = getCurrentId();

    // 發送 PATCH 請求到後端，通知選擇這家餐廳
    fetch(`/choose/${id}`, {
        method: 'PATCH'
    })
    .then(response => {
        if (response.ok) {
            window.location.href = "/dinnerHome/randomRestaurant";
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