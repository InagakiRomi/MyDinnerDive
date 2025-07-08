function getUserId(){
    const userId = document.getElementById('userId').value;
    return userId;
}

function getUsername(){
    const username =document.getElementById('username').value;
    var usernameVar = {"username": username};
    return usernameVar;
}

function getPassword(){
    const userPassword = document.getElementById('userPassword').value;
    var userPasswordVar = {"userPassword": userPassword};
    return userPasswordVar;
}

function getLastModifiedDate() {
    const lastModifiedDate = document.getElementById('lastModifiedDate').value;
    var lastModifiedDateVar = {"lastModifiedDate": lastModifiedDate};
    return lastModifiedDateVar;
}

function getHeaders(){
    // 設定格式
    let headers = {
    "Content-Type": "application/json",   // 告訴後端：這是 JSON 格式
    "Accept": "application/json",         // 告訴後端：我希望回傳也是 JSON
    }

    return headers;
}

// 統一導出
export {
    getUserId,
    getUsername,
    getPassword,
    getLastModifiedDate,
    getHeaders
};