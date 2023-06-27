package com.github.marcelooo616.exception;

import org.springframework.http.HttpStatus;

public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException(HttpStatus httpStatus, String message) {
        super(message);
    }
}
