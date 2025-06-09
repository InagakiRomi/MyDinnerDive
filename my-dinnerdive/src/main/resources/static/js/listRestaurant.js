fetch("/restaurants")
    .then(response => response.json())
    .then(data => {
        const tbody = document.getElementById("restaurant-table-body");
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
                    <a href="/update/${restaurant.restaurantId}" target="_blank">修改</a> |
                    <a href="/deleteById/${restaurant.restaurantId}" target="_blank">刪除</a>
                </td>
            `;
            tbody.appendChild(row);
        });
    })
    .catch(error => {
        console.error("資料載入失敗:", error);
    });