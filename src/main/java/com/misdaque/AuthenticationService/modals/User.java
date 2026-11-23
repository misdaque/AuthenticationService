package com.misdaque.AuthenticationService.modals;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import tools.jackson.databind.annotation.JsonDeserialize;

@Getter
@Setter
@Entity
@JsonDeserialize(as = User.class)
public class User extends BaseModal{

    private String email;
    private String password;
}
