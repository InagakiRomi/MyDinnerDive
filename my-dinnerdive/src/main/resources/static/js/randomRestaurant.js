function getRandomRestaurant() {
    fetch('/random')
        .then(res => res.json())
        .then(data => {
            document.getElementById('updateImg').src = data.imageUrl;
            document.getElementById('updateName').innerText = data.restaurantName;
            document.getElementById('updateCategory').innerText = data.category;
            document.getElementById('updateVisitedCount').innerText = data.visitedCount;
            document.getElementById('updateLastEat').innerText = data.lastEat;
            document.getElementById('updateLastVisitedAt').innerText = data.lastVisitedAt;
            document.getElementById('updateNote').innerText = data.note;
        });
}