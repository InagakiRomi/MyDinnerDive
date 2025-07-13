# 🍽️ Dinner Dive - 個人後端實戰專案

## 🧠 專案介紹

Dinner Dive 是我為練習後端整合開發而實作的 Web 系統，透過「解決選擇障礙」這個日常場景，實踐了資料庫設計、API 開發、資料查詢最佳化與使用者體驗思考。

本系統支援使用者新增、修改、刪除、查詢餐廳資訊，並透過抽選演算法提供餐廳建議。

---

## 🔍 技術亮點

- 使用 **Spring Boot + RESTful API** 架構開發後端，將功能模組化為 Controller / Service / DAO，方便後續維護與單元測試。
- 以 **JDBC Template 搭配 SQL 動態拼接** 實作彈性查詢，支援多條件搜尋、分頁與自訂排序，提升資料查詢效能與靈活度。
- 結合 **List 狀態控制** 與重置機制，確保每次抽選結果不重複。
- 整合 **Thymeleaf + 靜態 JS**，讓前端能動態渲染資料庫內容，並與後端 API 串接。

---

## 🔧 已實作功能

- 餐廳資料管理（新增 / 查詢 / 修改 / 刪除）
- 支援多條件查詢（分類、關鍵字、排序、分頁）
- 隨機餐廳抽選邏輯（含抽過排除與分類篩選）
- 餐廳選擇記錄（會更新最後選擇時間與次數統計）
- 登入、註冊帳號

---
<!-- 
## 📽️ 展示動畫

![錄製_2025_06_22_12_21_56_8](https://github.com/user-attachments/assets/02b07827-a52c-44f5-bd58-d85f3794cc69)

---
-->

## 🖼️ 介面展示

![image](https://github.com/user-attachments/assets/c9f2722f-5799-4e0d-ab92-53e7654ccf63)
![image](https://github.com/user-attachments/assets/fb9954c8-6219-4eb1-b5dd-c93fee45ccb4)
![image](https://github.com/user-attachments/assets/9fb1b528-a3a8-45b1-b145-cc70ae078a69)
![image](https://github.com/user-attachments/assets/16f19ef1-a0a8-4eaa-ad84-58b629a16695)
![image](https://github.com/user-attachments/assets/fa7925cb-cb09-44be-953b-b493ac767d4c)
![image](https://github.com/user-attachments/assets/3e4cd6c7-8972-40fa-9619-9df700e34717)
![image](https://github.com/user-attachments/assets/0dba0b21-7245-4e9f-99cf-8c0c79e2daaa)
![image](https://github.com/user-attachments/assets/e03d7576-fc75-4686-908c-69f736a0a298)

---

## 📃 單元測試

![image](https://github.com/user-attachments/assets/a523eda0-f34f-440d-baa3-e62c92c25412)

---

## 📃 資料庫清單

![image](https://github.com/user-attachments/assets/37cd08c7-3ae1-4460-9512-45cbe160dd85)
![image](https://github.com/user-attachments/assets/2d721c74-ee59-4236-86b6-48183530654f)
![image](https://github.com/user-attachments/assets/6c387b35-d383-4e4d-9dcf-d9f6081c082a)
![image](https://github.com/user-attachments/assets/daea70c9-5ffe-4e17-b81c-7e515cf2331f)
![image](https://github.com/user-attachments/assets/000d1838-5e44-4600-ae68-adcd17e391e1)
