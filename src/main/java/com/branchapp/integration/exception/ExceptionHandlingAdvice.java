package com.branchapp.integration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlingAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    Map<String, Object> resourceNotFoundExceptionHandler(ResourceNotFoundException exception, ServerHttpRequest request) {
        return buildErrorAttributes(exception, request, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ApplicationException.class)
    Map<String, Object> defaultApplicationExceptionHandler(ApplicationException exception, ServerHttpRequest request) {
        return buildErrorAttributes(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //TODO add more exception handling...

    private static Map<String, Object> buildErrorAttributes(Exception exception, ServerHttpRequest request, HttpStatus httpStatus) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("path", request.getPath().value());
        errorAttributes.put("status", httpStatus.value());
        errorAttributes.put("error", httpStatus.getReasonPhrase());
        errorAttributes.put("message", exception.getMessage());
        errorAttributes.put("requestId", request.getId());
        return errorAttributes;
    }
}
