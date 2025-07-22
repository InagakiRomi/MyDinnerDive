package com.romi.my_dinnerdive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/** 負責統一處理 API 發生的錯誤，回傳格式化過的錯誤訊息（JSON）*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 處理開發者主動拋出的錯誤（通常用於業務邏輯）
     * <p>
     * 用法範例：
     *   throw new ResponseStatusException(HttpStatus.CONFLICT, "帳號已存在")
     *
     * @param ex 包含 HTTP 狀態與錯誤訊息
     * @return 回傳錯誤訊息 JSON，例如：{ "error": "帳號已存在" }
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatus(ResponseStatusException ex) {
        Map<String, String> error = new HashMap<>();

        // 把錯誤訊息加到回傳資料中
        error.put("error", ex.getReason());

        // 設定 HTTP 狀態碼，回傳錯誤內容
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    /**
     * 處理輸入驗證失敗（例如欄位為空、格式錯）
     * <p>
     * 搭配@Valid 使用時，欄位驗證不通過會進到這裡
     *
     * @param ex 驗證錯誤資訊，內含所有欄位的錯誤訊息
     * @return 回傳欄位錯誤對應訊息，例如：{ "email": "不能為空" }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // 把每個欄位錯誤的欄位名稱與訊息加到 Map 中
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );

        // 回傳 400 Bad Request 狀態與錯誤詳細資訊
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}