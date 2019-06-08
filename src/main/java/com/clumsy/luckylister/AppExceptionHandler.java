package com.clumsy.luckylister;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.clumsy.luckylister.data.ErrorDao;
import com.clumsy.luckylister.exceptions.NotLoggedInException;
import com.clumsy.luckylister.exceptions.ObjectNotFoundException;
import com.clumsy.luckylister.exceptions.UserServiceException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ObjectNotFoundException.class })
    protected ResponseEntity<Object> handleNotExists(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDao("Error", ex.getMessage()), 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler(value = { NotLoggedInException.class })
    protected ResponseEntity<Object> handleNotLoggedIn(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDao("Error", "Not logged in"), 
          new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = { UserServiceException.class })
    protected ResponseEntity<Object> handleOtherError(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDao("Error", ex.getMessage()), 
          new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
