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
    // const url = `/random?category=輕食`;

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

            const lastEat = document.getElementById('lastEat');
            lastEat.innerText = data.lastEat;

            const lastVisitedAt = document.getElementById('lastVisitedAt');
            lastVisitedAt.innerText = data.lastVisitedAt;

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