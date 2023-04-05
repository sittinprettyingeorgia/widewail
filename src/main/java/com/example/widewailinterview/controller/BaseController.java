package com.example.widewailinterview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Objects;

@Log4j2
public class BaseController {
    protected static final String NOT_FOUND = "Item not found";
    protected static final String INVALID_UUID = "Please enter a valid uuid";

    protected ResponseEntity<Object> handleError(Exception e) {
        String message = e.getMessage();
        log.error(message, e);
        HttpStatus code = Objects.equals(message, NOT_FOUND) ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code).body(Map.of("message",message));
    }

    protected ResponseEntity<Object> handleResponse(Object body) {
        Map<String, Object> result = Map.of("success",true);
        if(body != null){
            result.put("result",body);
        }
        return ResponseEntity.status(200).body(result);
    }
}
