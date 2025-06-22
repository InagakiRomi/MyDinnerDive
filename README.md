# 🍽️ Dinner Dive - 個人後端實戰專案

## 🧠 專案介紹

Dinner Dive 是我為練習後端整合開發而實作的 Web 系統，透過「解決選擇障礙」這個日常場景，實踐了資料庫設計、API 開發、資料查詢最佳化與使用者體驗思考。

本系統支援使用者新增、修改、刪除、查詢餐廳資訊，並透過抽選演算法提供餐廳建議。

---

## 🔍 技術亮點

- 使用 **Spring Boot + RESTful API** 架構開發後端，將功能模組化為 Controller / Service / DAO，方便後續維護與單元測試。
- 以 **JDBC Template 搭配 SQL 動態拼接** 實作彈性查詢，支援多條件搜尋、分頁與自訂排序，提升資料查詢效能與靈活度。
- 設計抽選演算法時考量 UX，加入 **避免重複抽取、抽完自動重置** 等邏輯，強化實用性。
- 整合 **Thymeleaf + 靜態 JS**，讓前端能動態渲染資料庫內容，並與後端 API 串接。

---

## 🔧 已實作功能

- 餐廳資料管理（新增 / 查詢 / 修改 / 刪除）
- 支援多條件查詢（分類、關鍵字、排序、分頁）
- 隨機餐廳抽選邏輯（含抽過排除與分類篩選）
- 餐廳選擇記錄（會更新最後選擇時間與次數統計）

---

## 🧩 預計擴充功能

- 管理員權限控管（如僅限操作自身資料、檢視所有紀錄）
- 我的最愛清單機制
- 使用者個人化抽選偏好

---

## 📽️ 展示動畫

![錄製_2025_06_22_12_21_56_8](https://github.com/user-attachments/assets/02b07827-a52c-44f5-bd58-d85f3794cc69)

---

## 🖼️ 介面展示

![image](https://github.com/user-attachments/assets/b84cb530-0d79-45cf-b0a6-08fd1418cc11)  
![image](https://github.com/user-attachments/assets/c775ed75-5ce4-44d2-9c2e-c4c9dd2f7f93)  
![image](https://github.com/user-attachments/assets/cd45f44a-1ab9-4a46-8b77-49279b172b1a)  
![image](https://github.com/user-attachments/assets/b71f0a67-8528-4f28-9f43-a8f454ebe126)

---

## 📃 餐廳清單資料庫

![image](https://github.com/user-attachments/assets/37cd08c7-3ae1-4460-9512-45cbe160dd85)
