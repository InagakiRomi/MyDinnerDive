function getUserId(){
    const userId = document.getElementById('userId').value;
    return userId;
}

function getAccount(){
    const account =document.getElementById('account').value;
    var accountVar = {"account": account};
    return accountVar;
}

function getPassword(){
    const memberPassword = document.getElementById('memberPassword').value;
    var memberPasswordVar = {"memberPassword": memberPassword};
    return memberPasswordVar;
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
    getAccount,
    getPassword,
    getLastModifiedDate,
    getHeaders
};