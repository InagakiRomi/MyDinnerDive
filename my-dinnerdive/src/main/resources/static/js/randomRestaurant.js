let categoryDisplayNameMap = {};

fetch('/categories')
    .then(res => res.json())
    .then(data => {
        data.forEach(category => {
            categoryDisplayNameMap[category.name] = category.displayName;
        });

        getRandomRestaurant();
    })
    .catch(err => console.error('載入分類失敗：', err));

function getRandomRestaurant() {
    fetch('/random')
        .then(res => res.json())
        .then(data => {
            document.getElementById('updateImg').src = data.imageUrl;
            document.getElementById('updateName').innerText = data.restaurantName;

            const displayCategory = categoryDisplayNameMap[data.category] || data.category;
            document.getElementById('updateCategory').innerText = displayCategory;

            document.getElementById('updateVisitedCount').innerText = data.visitedCount;
            document.getElementById('updateLastEat').innerText = data.lastEat;
            document.getElementById('updateLastVisitedAt').innerText = data.lastVisitedAt;
            document.getElementById('updateNote').innerText = data.note;
        })
}