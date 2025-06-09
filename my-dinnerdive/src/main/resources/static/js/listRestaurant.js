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
                    <a href="/restaurants/${restaurant.restaurantId}">修改</a> |
                    <button class="delete-btn" data-id="${restaurant.restaurantId}">刪除</button>
                </td>
            `;
            tbody.appendChild(row);
        });

        //刪除資料
        document.querySelectorAll(".delete-btn").forEach(button => {
            button.addEventListener("click", function () {
                const id = this.getAttribute("data-id");
                if (confirm("確定要刪除這筆資料嗎？")) {
                    fetch(`/restaurants/${id}`, {
                        method: "DELETE"
                    })
                    .then(response => {
                        if (response.ok) {
                            this.closest("tr").remove();
                        } else {
                            alert("刪除失敗！");
                        }
                    })
                    .catch(error => {
                        console.error("刪除失敗:", error);
                    });
                }
            });
        });
    })
    .catch(error => {
        console.error("資料載入失敗:", error);
    });