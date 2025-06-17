document.addEventListener("DOMContentLoaded", listRestaurant);

function listRestaurant(){
    const data = [
        {
            restaurantName: "兩藍拉麵",
            imageUrl: "https://images.unsplash.com/photo-1614563637806-1d0e645e0940?q=80&w=1973&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            category: "主食",
            visitedCount: 2,
            lastEat: "2025-06-06 17:37:50",
            lastVisitedAt: "2025-06-06 17:37:50",
            note: "兩藍拉麵好吃啦"
        },
        {
            restaurantName: "兩藍拉麵",
            imageUrl: "https://images.unsplash.com/photo-1614563637806-1d0e645e0940?q=80&w=1973&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            category: "主食",
            visitedCount: 2,
            lastEat: "2025-06-06 17:37:50",
            lastVisitedAt: "2025-06-06 17:37:50",
            note: "兩藍拉麵好吃啦"
        }
    ];

    const tbody = document.getElementById('tableBody');

    data.forEach(restaurants => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${restaurants.restaurantName}</td>
            <td>
                <img class="listImage"
                 src="${restaurants.imageUrl}"
                 alt="餐廳圖片"
                 width="100"/>
            </td>
            <td>${restaurants.category}</td>
            <td>${restaurants.visitedCount}</td>
            <td>${restaurants.lastEat}</td>
            <td>${restaurants.lastVisitedAt}</td>
            <td>${restaurants.note}</td>
            <td>
                <button>修改</button>
                <button style="margin-left: 5px;">刪除</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

/*document.getElementById("filter-form").addEventListener("submit", function (e) {
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

    const params = [];

    if (category) {
        params.push("category=" + encodeURIComponent(category));
    }
    if (search) {
        params.push("search=" + encodeURIComponent(search));
    }

    // 讀取排序欄位與方式
    const orderBy = document.getElementById("orderBy").value;
    const sort = document.getElementById("sort").value;

    if (orderBy) {
        params.push("orderBy=" + encodeURIComponent(orderBy));
    }
    if (sort) {
        params.push("sort=" + encodeURIComponent(sort));
    }

    let url = "/restaurants";
    if (params.length > 0) {
        url += "?" + params.join("&");
    }

    // 取得餐廳資料
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
}*/