package com.kouforum.backend.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kouforum.backend.dto.PasswordResetRequest;
import com.kouforum.backend.dto.PasswordUpdate;
import com.kouforum.backend.dto.UserCreate;
import com.kouforum.backend.dto.UserDTO;
import com.kouforum.backend.dto.UserUpdate;
import com.kouforum.backend.models.CurrentUser;
import com.kouforum.backend.services.UserService;
import com.kouforum.backend.shared.GenericMessage;
import com.kouforum.backend.shared.Messages;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
    Page<UserDTO> getUsers(Pageable page,
            @AuthenticationPrincipal CurrentUser currentUser) {
        return userService.getUsers(page, currentUser).map(UserDTO::new);
    }

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id) {
        return new UserDTO(userService.getUser(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == principal.id")
    UserDTO updateUser(@PathVariable long id, @Valid @RequestBody UserUpdate userUpdate) {
        return new UserDTO(userService.updateUser(id, userUpdate));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#id == principal.id")
    GenericMessage deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return new GenericMessage("User is deleted.");
    }

    @PostMapping("/password-reset")
    GenericMessage passwordResetRequest(@Valid @RequestBody PasswordResetRequest passwordResetRequest) {
        userService.handleResetRequest(passwordResetRequest);
        return new GenericMessage("Check your email to reset your password.");
    }

    @PatchMapping("/{token}/password")
    GenericMessage setPassword(@PathVariable String token, @Valid @RequestBody PasswordUpdate passwordUpdate) {
        userService.updatePassword(token, passwordUpdate);
        return new GenericMessage("Password updated successfully.");
    }
}
