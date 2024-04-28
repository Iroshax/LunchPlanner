package com.iro.lunchplanner.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 *@author Irosha Senevirathne
 *@version 1.0
 *@since 2024-04-25
 */
@ControllerAdvice
@Slf4j
public class LunchPlanExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(LunchPlanException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("StatusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("Status", HttpStatus.INTERNAL_SERVER_ERROR);
        body.put("Message", ex.getMessage());
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
