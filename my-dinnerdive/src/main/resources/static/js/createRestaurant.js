const createButton = document.getElementById("create-btn");
createButton.addEventListener("click", createRestaurant);

function createRestaurant(){
    //抓html填的資訊
    const restaurantName = document.getElementById('restaurantName').value;
    const category = document.getElementById('category').value;
    const note = document.getElementById('note').value;
    const imageUrl = document.getElementById('imageUrl').value;

    // 設定格式
    let headers = {
    "Content-Type": "application/json",   // 告訴後端：這是 JSON 格式
    "Accept": "application/json",         // 告訴後端：我希望回傳也是 JSON
    }

    //轉成json格式
    var restaurantJson = {
        "restaurantName": restaurantName,
        "category": category,
        "note": note,
        "imageUrl": imageUrl
    }

    //傳資料給後端
    fetch('/restaurants', {
        method: "POST",
        headers: headers,
        body: JSON.stringify(restaurantJson)
    })
    .then((response) => {
        if (response.ok) {
            alert("餐廳新增成功！");
            window.location.href = "/dinnerHome";
        } else {
            alert("新增失敗，請再試一次");
        }
    })
    .catch((error) => {
        alert("系統發生錯誤！");
    })
}