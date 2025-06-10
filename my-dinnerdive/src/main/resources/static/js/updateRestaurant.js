document.getElementById('updateForm').addEventListener('submit', function (event) {
        event.preventDefault();

        const restaurantId = document.getElementById('restaurantId').value;

        const data = {
            restaurantName: document.getElementById('restaurantName').value,
            category: document.getElementById('category').value,
            visitedCount: parseInt(document.getElementById('updateVisitedCount').value),
            lastEat: document.getElementById('updateLastEat').value,
            note: document.getElementById('updateNote').value,
            imageUrl: document.getElementById('imageUrl').value
        };

        fetch(`/restaurants/${restaurantId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (response.ok) {
                window.location.href = "/listRestaurant";
            } else {
                alert("更新失敗！請確認資料是否正確");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("發生錯誤，請稍後再試！");
        });
    });