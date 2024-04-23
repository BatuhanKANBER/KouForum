package com.kouforum.backend.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kouforum.backend.dto.CurrentUser;
import com.kouforum.backend.dto.PasswordResetRequest;
import com.kouforum.backend.dto.PasswordUpdate;
import com.kouforum.backend.dto.UserUpdate;
import com.kouforum.backend.exeptions.ActivationNotificationException;
import com.kouforum.backend.exeptions.InvalidTokenException;
import com.kouforum.backend.exeptions.NotFoundException;
import com.kouforum.backend.exeptions.NotUniqueEmailException;
import com.kouforum.backend.models.User;
import com.kouforum.backend.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    FileService fileService;

    @Transactional(rollbackOn = MailException.class)
    public void save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.saveAndFlush(user);
            emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());
        } catch (MailException exception) {
            throw new ActivationNotificationException();
        } catch (DataIntegrityViolationException exception) {
            throw new NotUniqueEmailException();
        }
    }

    public void activateUser(String token) {
        User inDB = userRepository.findByActivationToken(token);
        if (inDB == null) {
            throw new InvalidTokenException();
        }
        inDB.setIs_active(true);
        inDB.setActivationToken(null);
        userRepository.save(inDB);
    }

    public Page<User> getUsers(Pageable page, CurrentUser currentUser) {
        if (currentUser == null) {
            return userRepository.findAll(page);
        }
        return userRepository.findByIdNot(currentUser.getId(), page);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(long id, UserUpdate userUpdate) {
        User inDB = getUser(id);
        inDB.setUsername(userUpdate.username());
        if (userUpdate.image() != null) {
            String fileName = fileService.saveBase64StringAsFile(userUpdate.image());
            fileService.deleteProfileImage(inDB.getImage());
            inDB.setImage(fileName);
        }
        return userRepository.save(inDB);
    }

    public void deleteUser(long id) {
        User inDB = getUser(id);
        if (inDB.getImage() != null) {
            fileService.deleteProfileImage(inDB.getImage());
        }
        userRepository.deleteById(id);
    }

    public void handleResetRequest(PasswordResetRequest passwordResetRequest) {
        User inDB = findByEmail(passwordResetRequest.email());
        if (inDB == null) {
            throw new NotFoundException((long) 0);
        }
        inDB.setPasswordResetToken(UUID.randomUUID().toString());
        this.userRepository.save(inDB);
        this.emailService.sendPasswordResetEmail(inDB.getEmail(), inDB.getPasswordResetToken());
    }

    public void updatePassword(String token, PasswordUpdate passwordUpdate) {
        User inDB = userRepository.findByPasswordResetToken(token);
        if (inDB == null) {
            throw new InvalidTokenException();
        }
        inDB.setPasswordResetToken(null);
        inDB.setPassword(passwordEncoder.encode(passwordUpdate.password()));
        inDB.setIs_active(true);
        inDB.setActivationToken(null);
        userRepository.save(inDB);
    }
}
