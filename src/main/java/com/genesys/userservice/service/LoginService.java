package com.genesys.userservice.service;

import com.genesys.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    public User loginUser(User user) {
        Optional<User> userOptional = userService.getUser(user.getId());
        if(userOptional.isPresent()) {
            User validatedUser = userOptional.get();
            if(validatedUser.getEmail().equals(user.getEmail()) && validatedUser.getPassword().equals(user.getPassword())) {
                validatedUser.setLoggedIn(true);
                validatedUser.setLastLogin(new Date(System.currentTimeMillis()));
                userService.createUser(validatedUser);
                return validatedUser;
            }
        }
        return user;
    }

    public User logoutUser(User user) {
        Optional<User> userOptional = userService.getUser(user.getId());
        if(userOptional.isPresent()) {
            User validatedUser = userOptional.get();
            if(validatedUser.getEmail().equals(user.getEmail()) && validatedUser.getPassword().equals(user.getPassword())) {
                validatedUser.setLoggedIn(false);
                userService.createUser(validatedUser);
                return validatedUser;
            }
        }
        return user;
    }

}
