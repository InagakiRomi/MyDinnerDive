document.addEventListener("DOMContentLoaded", listRestaurant);

async function listRestaurant(){
    const tbody = document.getElementById('tableBody');
    tbody.innerHTML = '';

    const response = await fetch('/restaurants');
    const result = await response.json();
    const data = result.results;

    data.forEach(restaurant => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${restaurant.restaurantId}</td>
            <td>${restaurant.restaurantName}</td>
            <td>
                <img class="listImage"
                        src="${restaurant.imageUrl}"
                        alt="餐廳圖片"
                        width="100"/>
            </td>
            <td>${restaurant.category}</td>
            <td>${restaurant.visitedCount}</td>
            <td>${restaurant.lastEat}</td>
            <td>${restaurant.lastVisitedAt}</td>
            <td>${restaurant.note}</td>
            <td>
                <button onclick="location.href='/restaurants/${restaurant.restaurantId}/edit'">修改</button>
                <button class="delete-btn" data-id="${restaurant.restaurantId}" style="margin-left: 5px;">刪除</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
};

const getTableBody = document.getElementById('tableBody')
getTableBody.addEventListener('click', deleteRestaurant);
async function deleteRestaurant(event) {
    const deleteButton = event.target.classList.contains('delete-btn');

    if (deleteButton) {
        const id = event.target.getAttribute('data-id');
        fetch(`/restaurants/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.ok) {
                alert("刪除成功！");
                listRestaurant();
            } else {
                alert("刪除失敗！請確認資料是否正確");
            }
        });
    }
};

/*
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
}*/