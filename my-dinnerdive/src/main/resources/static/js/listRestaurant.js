document.addEventListener("DOMContentLoaded", function (){
    listRestaurant();
    const preventForm = document.getElementById("listForm");
    preventForm.addEventListener("submit", preventFormSubmit);

    function preventFormSubmit(event) {
        event.preventDefault();
        listRestaurant();
    }
})

async function listRestaurant(){

    const category = document.getElementById("category").value;
    const search = document.getElementById("search").value;
    const orderBy = document.getElementById("orderBy").value;
    const sort = document.getElementById("sort").value;

    const params = new URLSearchParams();
    if (category){
        params.append("category", category);
    } 
    if (search){
        params.append("search", search);
    }
    if (orderBy){
        params.append("orderBy", orderBy);
    }
    if (sort){
        params.append("sort", sort);
    }

    const url = `/restaurants?${params.toString()}`;

    const response = await fetch(url);
    const result = await response.json();
    const data = result.results;

    // 撈取餐廳資訊並顯示
    const tbody = document.getElementById('tableBody');
    tbody.innerHTML = '';

    data.forEach(restaurant => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${restaurant.restaurantId}</td>
            <td>${restaurant.restaurantName}</td>
            <td>
                <img class="listImage"
                        src="${restaurant.imageUrl}"
                        alt="餐廳圖片"
                        width="100"
                        onerror="this.onerror=null;this.src='/images/defaultRestaurant.jpg';"/>
            </td>
            <td>${restaurant.category}</td>
            <td>${restaurant.visitedCount}</td>
            <td>${restaurant.lastEat ?? '-'}</td>
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

const tableBody = document.getElementById('tableBody')
tableBody.addEventListener('click', deleteRestaurant);
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