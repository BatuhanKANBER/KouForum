package com.kouforum.backend.exeptions;

import org.springframework.context.i18n.LocaleContextHolder;

public class ActivationNotificationExeption extends RuntimeException {
    public ActivationNotificationExeption() {
        super(com.kouforum.backend.shared.Messages.getMessageForLocale("kouforum.create.user.email.failure", LocaleContextHolder.getLocale()));

    }
}
