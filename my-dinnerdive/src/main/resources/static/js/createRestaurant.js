const createButton = document.getElementById("create-btn");
createButton.addEventListener("click", createRestaurant);

function createRestaurant(){
    //抓html田的資訊
    //轉成json格式
    //傳給後端
    alert("觸發！");
}
/*
document.getElementById('restaurantForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 阻止預設提交

    const data = {
        restaurantName: document.getElementById('restaurantName').value,
        category: document.getElementById('category').value,
        note: document.getElementById('note').value,
        imageUrl: document.getElementById('imageUrl').value
    };

    fetch('/restaurants?visitedCount=0', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            alert("餐廳新增成功！");
            window.location.href = "/dinnerHome";
        } else {
            alert("新增失敗，請再試一次");
        }
    })
    .catch(error => {
        console.error('錯誤:', error);
        alert("系統發生錯誤！");
    });
});*/