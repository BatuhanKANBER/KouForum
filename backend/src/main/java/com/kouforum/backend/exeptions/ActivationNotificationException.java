package com.kouforum.backend.exeptions;

import org.springframework.context.i18n.LocaleContextHolder;

public class ActivationNotificationException extends RuntimeException {
    public ActivationNotificationException() {
        super(com.kouforum.backend.shared.Messages.getMessageForLocale("kouforum.create.user.email.failure", LocaleContextHolder.getLocale()));

    }
}
