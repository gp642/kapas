package com.example.kapas.controller;

import com.example.kapas.model.LoggedInUser;
import com.example.kapas.model.Login;
import com.example.kapas.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoggedInUser> login(@Valid @RequestBody Login login) throws Exception {
        LoggedInUser loggedInUser = userService.loginWithPassword(login);
        return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }
}
