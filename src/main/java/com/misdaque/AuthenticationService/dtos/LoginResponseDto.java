package com.misdaque.AuthenticationService.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {

    private RequestStatus status;
    private String message;

}
