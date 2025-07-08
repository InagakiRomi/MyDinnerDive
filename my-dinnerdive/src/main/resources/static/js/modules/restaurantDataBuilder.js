function getRestaurantId(){
    const restaurantId = document.getElementById('restaurantId').value;
    //var idVar = {"restaurantId": restaurantId};
    return restaurantId;
}

function getRestaurantName(){
    const restaurantName =document.getElementById('restaurantName').value;
    var nameVar = {"restaurantName": restaurantName};
    return nameVar;
}

function getCategory(){
    const category = document.getElementById('category').value;
    var categoryVar = {"category": category};
    return categoryVar;
}

function getVisitedCount() {
    const visitedCount = parseInt(document.getElementById('visitedCount').value);
    var visitedCountVar = {"visitedCount": visitedCount};
    return visitedCountVar;
}

function getLastSelectedAt() {
    const lastSelectedAt = document.getElementById('lastSelectedAt').value;
    var lastSelectedAtVar = {"lastSelectedAt": lastSelectedAt};
    return lastSelectedAtVar;
}

function getNote() {
    const note = document.getElementById('note').value;
    var noteVar = {"note": note};
    return noteVar;
}

function getImageUrl() {
    const imageUrl = document.getElementById('imageUrl').value;
    var imageUrlVar = {"imageUrl": imageUrl};
    return imageUrlVar;
}

function getHeaders(){
    // 設定格式
    let headers = {
    "Content-Type": "application/json",   // 告訴後端：這是 JSON 格式
    "Accept": "application/json",         // 告訴後端：我希望回傳也是 JSON
    }

    return headers;
}

// 統一導出
export {
    getRestaurantId,
    getRestaurantName,
    getCategory,
    getVisitedCount,
    getLastSelectedAt,
    getNote,
    getImageUrl,
    getHeaders
};