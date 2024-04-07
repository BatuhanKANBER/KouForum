package com.kouforum.backend.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kouforum.backend.exeptions.ActivationNotificationExeption;
import com.kouforum.backend.exeptions.InvalidTokenExeption;
import com.kouforum.backend.exeptions.NotUniqueEmailExeption;
import com.kouforum.backend.models.User;
import com.kouforum.backend.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    EmailService emailService;

    @Transactional(rollbackOn = MailException.class)
    public void save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.saveAndFlush(user);
            emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());
        } catch (MailException exception) {
            throw new ActivationNotificationExeption();
        } catch (DataIntegrityViolationException exception){
            throw new NotUniqueEmailExeption();
        }
    }

    public void activateUser(String token){
        User inDB = userRepository.findByActivationToken(token);
        if(inDB == null){
            throw new InvalidTokenExeption();
        }
        inDB.setIs_active(true);
        inDB.setActivationToken(null);
        userRepository.save(inDB);
    }
}
