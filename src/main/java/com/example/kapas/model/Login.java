package com.example.kapas.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Login {
    @NotBlank(message = "UserName is mandatory")
    private String userName;
    @NotBlank(message = "Password is mandatory")
    private String password;
}
