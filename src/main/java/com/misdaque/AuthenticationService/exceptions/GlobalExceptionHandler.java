package com.misdaque.AuthenticationService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {

        return problem(HttpStatus.CONFLICT, "User Already Exists", exception.getMessage());
    }

    @ExceptionHandler({UserNotFoundException.class, WrongPasswordException.class, IllegalArgumentException.class})
    public ResponseEntity<ProblemDetail> handleAuthenticationFailure(Exception exception) {
        return problem(HttpStatus.UNAUTHORIZED, "Authentication Failed", exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpectedException(Exception exception) {
        return problem(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", exception.getMessage());
    }

    private ResponseEntity<ProblemDetail> problem(HttpStatus status, String title, String message){

        ProblemDetail detail = ProblemDetail.forStatusAndDetail(status, message);
        detail.setTitle(title);

        return ResponseEntity.status(status).body(detail);
    }
}
