package com.kouforum.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kouforum.backend.models.User;
import com.kouforum.backend.services.UserService;
import com.kouforum.backend.shared.GenericMessage;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    GenericMessage createUser(@RequestBody User user) {
        userService.save(user);
        return new GenericMessage("User is created.");
    }
}
