package com.thoughtworks.springbootemployee.config;

import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataExceptopn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class globalExceptionHandler {

    @ExceptionHandler(IllegalOperationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    String handleIllegalOperationException() {
        return IllegalOperationException.getIllegalOperationData();
    }

    @ExceptionHandler(NoSuchDataExceptopn.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    String handleNoSuchException() {
        return NoSuchDataExceptopn.getNoSuchData();
    }



}
