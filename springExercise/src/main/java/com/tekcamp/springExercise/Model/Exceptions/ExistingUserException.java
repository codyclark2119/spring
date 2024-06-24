package com.tekcamp.springExercise.Model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email already in use.")
public class ExistingUserException extends RuntimeException {

    public ExistingUserException(String msg) {
        super(msg);
    }
}

