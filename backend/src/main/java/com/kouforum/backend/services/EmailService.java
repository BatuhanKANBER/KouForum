package com.kouforum.backend.services;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.kouforum.backend.configuration.KouForumProperties;

import jakarta.annotation.PostConstruct;

@Service
public class EmailService {

    JavaMailSenderImpl mailSender;

    @Autowired
    KouForumProperties kouForumProperties;

    @PostConstruct
    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(kouForumProperties.getEmail().host());
        mailSender.setPort(kouForumProperties.getEmail().port());
        mailSender.setUsername(kouForumProperties.getEmail().username());
        mailSender.setPassword(kouForumProperties.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put(kouForumProperties.getEmail().key(), true);
    }

    public void sendActivationEmail(String email, String activationToken) {
        String activationUrl = (kouForumProperties.getClient().host() + "/activation/" + activationToken).toString();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(kouForumProperties.getEmail().from());
        message.setTo(email);
        message.setSubject("Account Activation");
        message.setText(activationUrl);
        this.mailSender.send(message);
    }
}
