package com.kouforum.backend.services;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kouforum.backend.configuration.KouForumProperties;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    JavaMailSenderImpl mailSender;

    @Autowired
    KouForumProperties kouForumProperties;

    @Autowired
    MessageSource messageSource;

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

    String activationEmail = """
            <html>
                <body>
                    <h1>${title}</h1>
                    <a href="${url}">${clickHere}</a>
                </body>
            </html>
            """;

    public void sendActivationEmail(String email, String activationToken) {
        var activationUrl = kouForumProperties.getClient().host() + "/activation/" + activationToken;
        var title = messageSource.getMessage("kouforum.mail.user.created.title", null, LocaleContextHolder.getLocale());
        var clickHere = messageSource.getMessage("kouforum.mail.click.here", null, LocaleContextHolder.getLocale());

        var mailBody = activationEmail
                .replace("${url}", activationUrl)
                .replace("${title}", title)
                .replace("${clickHere}", clickHere);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            message.setFrom(kouForumProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        this.mailSender.send(mimeMessage);
    }

    public void sendPasswordResetEmail(String email, String passwordResetToken) {
        String passwordResetUrl = kouForumProperties.getClient().host() + "/password-reset/set?tk="
                + passwordResetToken;
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        var title = "Reset your password";
        var clickHere = messageSource.getMessage("kouforum.mail.click.here", null, LocaleContextHolder.getLocale());
        var mailBody = activationEmail.replace("${url}", passwordResetUrl).replace("${title}", title)
                .replace("${clickHere}", clickHere);
        try {
            message.setFrom(kouForumProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);
        } catch (MessagingException exception) {
            exception.printStackTrace();
        }
        this.mailSender.send(mimeMessage);
    }

}
