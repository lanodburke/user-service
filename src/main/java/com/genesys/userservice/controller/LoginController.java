package com.genesys.userservice.controller;

import com.genesys.userservice.model.User;
import com.genesys.userservice.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User loggedInUser = loginService.loginUser(user);

        if(loggedInUser.isLoggedIn()) {
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logoutUser(@RequestBody User user) {
        User loggedOutUser = loginService.logoutUser(user);

        if(!loggedOutUser.isLoggedIn()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
