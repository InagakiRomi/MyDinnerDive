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
                fetch(`/restaurants/${id}`, {
                        method: "DELETE"
                    })
                .then(response => {
                    this.closest("tr").remove();
                    })
            });
        });
    });