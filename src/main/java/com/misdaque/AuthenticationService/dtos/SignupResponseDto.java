package com.misdaque.AuthenticationService.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupResponseDto {

    private RequestStatus status;
    private String message;
}
