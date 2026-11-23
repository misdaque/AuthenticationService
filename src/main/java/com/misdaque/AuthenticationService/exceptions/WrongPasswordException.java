package com.misdaque.AuthenticationService.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WrongPasswordException extends RuntimeException {

    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private final String message;

    public WrongPasswordException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
