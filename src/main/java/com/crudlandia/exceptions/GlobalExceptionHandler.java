package com.crudlandia.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata todas as exceções anotadas com @ApiException Retorna status 500 com a string da
     * anotação como mensagem
     */
    @ExceptionHandler({ExemploNaoEncontradoException.class, ExemploNomeDuplicadoException.class,
            ReferenciaNaoEncontradoException.class})
    public ResponseEntity<Map<String, Object>> handleApiException(Exception ex,
            WebRequest request) {
        String message = ex.getClass().getSimpleName();

        // Obtém o valor da anotação @ApiException
        if (ex.getClass().isAnnotationPresent(ApiException.class)) {
            ApiException annotation = ex.getClass().getAnnotation(ApiException.class);
            message = annotation.value();
        }

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex,
            WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
