package com.kouforum.backend.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kouforum.backend.dto.UserCreate;
import com.kouforum.backend.dto.UserDTO;
import com.kouforum.backend.services.UserService;
import com.kouforum.backend.shared.GenericMessage;
import com.kouforum.backend.shared.Messages;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    // USER CREATE
    @PostMapping("/create")
    GenericMessage createUser(@Valid @RequestBody UserCreate user) {
        userService.save(user.toUser());
        String message = Messages.getMessageForLocale("kouforum.create.user.success", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @PatchMapping("/{token}/activate")
    GenericMessage activateUser(@PathVariable String token) {
        userService.activateUser(token);
        String message = Messages.getMessageForLocale("kouforum.activate.user.success",
                LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @GetMapping("/list")
    public Page<UserDTO> getUsers(Pageable page) {
        return userService.getUsers(page).map(UserDTO::new);
    }

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id) {
        return new UserDTO(userService.getUser(id));
    }

}
