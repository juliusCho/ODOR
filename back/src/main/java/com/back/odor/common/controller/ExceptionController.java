package com.back.odor.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

//    @ExceptionHandler({
//            IllegalArgumentException.class,
//            TypeMismatchException.class,
//            MissingServletRequestParameterException.class,
//            JSONException.class
//    })
//    public ResponseEntity

}
