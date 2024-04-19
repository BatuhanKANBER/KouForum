package com.kouforum.backend.exeptions;

import org.springframework.context.i18n.LocaleContextHolder;

public class AuthorizationExeption extends RuntimeException {
    public AuthorizationExeption() {
        super(com.kouforum.backend.shared.Messages.getMessageForLocale("kouforum.authorization.failure",
                LocaleContextHolder.getLocale()));

    }
}
