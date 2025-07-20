package com.romi.my_dinnerdive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * 全域例外處理類別
 * 使用 @RestControllerAdvice 可攔截全站 Controller 的例外，統一回應錯誤格式
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 處理 ResponseStatusException（例如：手動丟出錯誤狀態碼與訊息）
     * 常見於 Controller 中主動拋出，例如：
     *   throw new ResponseStatusException(HttpStatus.CONFLICT, "帳號已存在");
     *
     * @param ex 拋出的 ResponseStatusException 物件
     * @return 包含錯誤訊息的 JSON 回應，以及對應的 HTTP 狀態碼
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatus(ResponseStatusException ex) {
        Map<String, String> error = new HashMap<>();

        // 將例外原因（文字訊息）放入回應 JSON 中
        error.put("error", ex.getReason());

        // 回傳對應的 HTTP 狀態碼與錯誤訊息
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    /**
     * 處理 @Valid / @Validated 驗證失敗的例外
     * 例如：欄位為 null、格式不符、長度超過等
     *
     * @param ex 驗證失敗例外物件，包含所有欄位的錯誤資訊
     * @return Map 格式，key 為欄位名稱，value 為錯誤訊息
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