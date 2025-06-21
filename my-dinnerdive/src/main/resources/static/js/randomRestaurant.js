let currentRestaurantId;

const randomButton = document.getElementById("random-btn");
randomButton.addEventListener("click", randomRestaurant);

function randomRestaurant() {
    fetch('/random')
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

export function getCurrentId(){
    return currentRestaurantId;
}