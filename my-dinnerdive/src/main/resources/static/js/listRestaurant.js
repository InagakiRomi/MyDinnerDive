let offset;
let limit;
let total;

document.addEventListener("DOMContentLoaded", function (){
    listRestaurant();
    const preventForm = document.getElementById("listForm");
    preventForm.addEventListener("submit", preventFormSubmit);

    function preventFormSubmit(event) {
        event.preventDefault();
        listRestaurant();
    }

    // 自動刷新：分類、排序條件、排序方向
    const categorySelect = document.getElementById("category");
    categorySelect.addEventListener("change", function () {
        offset = 0;
        listRestaurant();
    });

    const orderBySelect = document.getElementById("orderBy");
    orderBySelect.addEventListener("change", function () {
        offset = 0;
        listRestaurant();
    });

    const sortSelect = document.getElementById("sort");
    sortSelect.addEventListener("change", function () {
        offset = 0;
        listRestaurant();
    });
})

const prevButton = document.getElementById("prevPage")
prevButton.addEventListener("click", function () {
    if(offset >= limit){
        offset = offset - limit;
        listRestaurant();
    }
});

const nextButton = document.getElementById("nextPage")
nextButton.addEventListener("click", function () {
    if(offset < total-limit){
        offset = offset + limit;
        listRestaurant();
    }
});

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

    params.append("offset", offset || 0);
    const url = `/restaurants?${params.toString()}`;

    const response = await fetch(url);
    const result = await response.json();

    offset = result.offset
    limit = result.limit;
    total = result.total;

    const data = result.results;

    // 撈取餐廳資訊並顯示
    const tbody = document.getElementById('tableBody');
    tbody.innerHTML = '';

    data.forEach(restaurant => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${restaurant.restaurantId}</td>
            <td class="noteCell">${restaurant.restaurantName}</td>
            <td>
                <img src="${restaurant.imageUrl}"
                     alt="餐廳圖片"
                     width="100"
                     onerror="this.onerror=null;this.src='/images/defaultRestaurant.jpg';"/>
            </td>
            <td>${restaurant.category}</td>
            <td>${restaurant.visitedCount}</td>
            <td>${restaurant.lastEat ?? '-'}</td>
            <td>${restaurant.lastVisitedAt}</td>
            <td class="noteCell">${restaurant.note}</td>
            <td>
                <button onclick="location.href='/dinnerHome/restaurants/${restaurant.restaurantId}/edit'">修改</button>
                <button class="delete-btn" data-id="${restaurant.restaurantId}" style="margin-left: 5px;">刪除</button>
            </td>
        `;
        tbody.appendChild(tr);
    });

    // 更新頁數顯示
    const pageInfo = document.querySelector(".switchPageBtn div");
    const currentPage = Math.floor(offset / limit) + 1;
    const totalPages = Math.ceil(total / limit);
    pageInfo.textContent = `第${currentPage}頁 / 共${totalPages}頁`;

    // 控制上一頁與下一頁按鈕狀態
    prevButton.disabled = currentPage === 1;
    nextButton.disabled = currentPage === totalPages;
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