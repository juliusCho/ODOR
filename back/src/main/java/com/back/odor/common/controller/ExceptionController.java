package com.back.odor.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    private ApiResultVO<String> exceptionResult(Throwable e, HttpStatus status) {
        return new ApiResultVO<>(status.value(), e.getMessage());
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            TypeMismatchException.class,
            MissingServletRequestParameterException.class,
            JSONException.class
    })
    public ApiResultVO<String> handleBadRequestException(Exception e) {
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        return this.exceptionResult(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ApiResultVO<String> handleMediaTypeException(Exception e) {
        log.debug("Media Type exception occurred: {}", e.getMessage(), e);
        return this.exceptionResult(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResultVO<String> handleHttprequestMethodException(Exception e) {
        log.debug("Http request method exception occurred: {}", e.getMessage(), e);
        return this.exceptionResult(e, HttpStatus.METHOD_NOT_ALLOWED);
    }

}
