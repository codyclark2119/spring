package com.tekcamp.springExercise.Model.Exceptions.Handler;

import com.tekcamp.springExercise.Model.Exceptions.ExistingUserException;
import com.tekcamp.springExercise.Model.Exceptions.Response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
//400 Existing user exception
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ExistingUserException.class)
    protected ResponseEntity<Object> resolveExistingUserException(ExistingUserException e, WebRequest req){

        Timestamp time = new Timestamp(System.currentTimeMillis());
        ErrorResponse errorResponse = new ErrorResponse(time,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                req.getDescription(false));

        return handleExceptionInternal(e, errorResponse.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
    }

}
