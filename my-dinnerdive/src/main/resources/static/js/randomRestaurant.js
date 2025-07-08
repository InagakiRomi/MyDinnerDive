let currentRestaurantId;

const randomButton = document.getElementById("random-btn");
randomButton.addEventListener("click", randomRestaurant);

function randomRestaurant() {
    const category = document.getElementById("categoryLabel").value;
    const params = new URLSearchParams();
    if (category){
        params.append("category", category);
    } 
    const url = `/random?${params.toString()}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            currentRestaurantId = data.restaurantId;
            
            const imageUrl = document.getElementById('imageUrl');
            imageUrl.src = data.imageUrl || '/images/defaultRestaurant.jpg';

            const restaurantName =document.getElementById('restaurantName');
            restaurantName.innerText = data.restaurantName;

            const category = document.getElementById('category');
            category.innerText = data.category;

            const visitedCount = document.getElementById('visitedCount');
            visitedCount.innerText = data.visitedCount;

            const lastSelectedAt = document.getElementById('lastSelectedAt');
            lastSelectedAt.innerText = data.lastSelectedAt;

            const updatedAt = document.getElementById('updatedAt');
            updatedAt.innerText = data.updatedAt;

            const note = document.getElementById('note');
            note.innerText = data.note;
        });
}


const resetButton = document.getElementById("categoryLabel");
resetButton.addEventListener("change", resetRandom);

function resetRandom() {
    fetch('/clearRandom', {
        method: 'POST'
    })
    .then(response => {
        if (response.ok) {
            alert("抽籤紀錄已清除，開始新的抽選！");
        } else {
            alert("重抽失敗！");
        }
    });
}

export function getCurrentId(){
    return currentRestaurantId;
}