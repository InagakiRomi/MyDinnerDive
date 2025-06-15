const updateButton = document.getElementById("update-btn");
updateButton.addEventListener("click", updateRestaurant);

function updateRestaurant(){
    const restaurantId = document.getElementById('restaurantId').value;

        const data = {
            restaurantName: document.getElementById('restaurantName').value,
            category: document.getElementById('category').value,
            visitedCount: parseInt(document.getElementById('visitedCount').value),
            lastEat: document.getElementById('LastEat').value,
            note: document.getElementById('Note').value,
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
                alert("修改成功！");
            } else {
                alert("修改失敗！請確認資料是否正確");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("發生錯誤，請稍後再試！");
        });
}

document.addEventListener("DOMContentLoaded", () => {
    const categorySelect = document.getElementById("category");
    const selectedValue = categorySelect.getAttribute("value");
    if (selectedValue) {
        categorySelect.value = selectedValue;
    }
});