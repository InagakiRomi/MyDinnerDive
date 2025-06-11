function getRandomRestaurant() {
    fetch('/random')
        .then(res => res.json())
        .then(data => {
            document.getElementById('updateImg').src = data.imageUrl;
            document.getElementById('updateName').innerText = data.restaurantName;

            const displayCategory = categoryDisplayNameMap[data.category] || `[未知分類: ${data.category}]`;
            document.getElementById('updateCategory').innerText = displayCategory;

            document.getElementById('updateVisitedCount').innerText = data.visitedCount;
            document.getElementById('updateLastEat').innerText = data.lastEat;
            document.getElementById('updateLastVisitedAt').innerText = data.lastVisitedAt;
            document.getElementById('updateNote').innerText = data.note;
        })
        .catch(err => console.error("取得隨機餐廳失敗：", err));
}