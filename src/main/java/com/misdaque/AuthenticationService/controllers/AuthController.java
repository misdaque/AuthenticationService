package com.misdaque.AuthenticationService.controllers;

import com.misdaque.AuthenticationService.dtos.*;
import com.misdaque.AuthenticationService.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {

        LoginResponseDto loginResponseDto = new LoginResponseDto();

        if (loginRequestDto.getEmail() == null || loginRequestDto.getPassword() == null) {
            throw new IllegalArgumentException("Email or password is required");
        }

        if (authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword()))
            loginResponseDto.setStatus(RequestStatus.SUCCESS);
        else
            loginResponseDto.setStatus(RequestStatus.FAILURE);

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @PostMapping("/sign_up")
    public ResponseEntity<SignupResponseDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {

        SignupResponseDto signupResponseDto = new SignupResponseDto();

        if (signupRequestDto.getEmail() == null || signupRequestDto.getEmail().isBlank() || signupRequestDto.getPassword() == null) {
            throw new IllegalArgumentException("Email or password is required");
        }

        if (authService.signUp(signupRequestDto.getEmail(), signupRequestDto.getPassword()))
            signupResponseDto.setStatus(RequestStatus.SUCCESS);
        else
            signupResponseDto.setStatus(RequestStatus.FAILURE);

        return new ResponseEntity<>(signupResponseDto, HttpStatus.OK);
    }
}
