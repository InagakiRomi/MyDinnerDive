const restaurantId = document.body.getAttribute('dataRestaurantId');
const container = document.getElementById('dishContainer');

// 取得餐點資料
fetch(`/restaurants/${restaurantId}/dishes`)
    .then(response => response.json())
    .then(dishes => {

        // 如果資料是空的，就顯示提示文字
        if (dishes.length === 0) {
            const emptyMessage = document.createElement('div');
            emptyMessage.textContent = '請新增餐點';
            emptyMessage.className = 'noDishMessage';
            container.appendChild(emptyMessage);
            return;
        }

        dishes.forEach(dish => {
            // 建立一個新的 div 元素來顯示菜色
            const dishDiv = document.createElement('div');
            dishDiv.className = 'dish';

            dishDiv.innerHTML = `
                <div class="dishRow">
                    <div class="dishName">${dish.dishName}</div>
                    <div class="dishPrice">$${dish.price}</div>
                    <!-- <button class="updateBtn">修改</button> -->
                    <button class="deleteBtn">刪除</button>
                </div>
            `;

            // 把 div 加到網頁上的 container 中
            container.appendChild(dishDiv);
        });
    })
    .catch(error => {
        // 如果有錯誤，就在 console 顯示錯誤訊息
        console.error('取得菜單時出錯:', error);
    });

// 取得餐廳資訊並更新標題
fetch(`/restaurants/${restaurantId}`)
    .then(response => response.json())
    .then(restaurant => {
        const title = document.getElementById('restaurantTitle');
        title.textContent = `${restaurant.restaurantName}`;
    })
    .catch(error => {
        console.error('取得餐廳資訊時出錯:', error);
    });