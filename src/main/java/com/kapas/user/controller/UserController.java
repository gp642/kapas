package com.kapas.user.controller;

import com.kapas.user.entity.User;
import com.kapas.user.model.LoggedInUser;
import com.kapas.user.model.Login;
import com.kapas.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping(value = "/test")
    public ResponseEntity<String> test(HttpServletRequest request) {
        User user = (User) request.getAttribute("principal");
        return new ResponseEntity<>(user.getEmail(), HttpStatus.OK);
    }
}
