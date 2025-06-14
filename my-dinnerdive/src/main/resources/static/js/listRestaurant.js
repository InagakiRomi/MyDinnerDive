document.getElementById("filter-form").addEventListener("submit", function (e) {
    e.preventDefault(); // 阻止表單重新載入頁面

    const category = document.getElementById("category").value;
    const search = document.getElementById("search").value;

    loadRestaurants(category, search); // 加入搜尋條件
});

// 預設載入全部餐廳
loadRestaurants("", "");

function loadRestaurants(category, search) {
    const tbody = document.getElementById("restaurant-table-body");
    tbody.innerHTML = ""; // 清空現有內容

    let url = "/restaurants";
    const params = [];

    if (category) {
        params.push("category=" + encodeURIComponent(category));
    }
    if (search) {
        params.push("search=" + encodeURIComponent(search));
    }

    if (params.length > 0) {
        url += "?" + params.join("&");
    }

    fetch(url)
        .then(response => response.json())
        .then(data => {
            data.forEach(restaurant => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${restaurant.restaurantName}</td>
                    <td>${restaurant.category}</td>
                    <td>${restaurant.visitedCount}</td>
                    <td>${restaurant.lastEat || ''}</td>
                    <td>${restaurant.lastVisitedAt || ''}</td>
                    <td>${restaurant.note || ''}</td>
                    <td>
                        <button onclick="location.href='/restaurants/${restaurant.restaurantId}/edit'">修改</button>
                        <button class="delete-btn" data-id="${restaurant.restaurantId}">刪除</button>
                    </td>
                `;
                tbody.appendChild(row);
            });

            // 刪除資料
            document.querySelectorAll(".delete-btn").forEach(button => {
                button.addEventListener("click", function () {
                    const id = this.getAttribute("data-id");
                    fetch(`/restaurants/${id}`, {
                        method: "DELETE"
                    }).then(response => {
                        this.closest("tr").remove();
                    });
                });
            });
        });
}