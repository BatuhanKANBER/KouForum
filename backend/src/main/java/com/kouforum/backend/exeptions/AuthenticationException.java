package com.kouforum.backend.exeptions;

import org.springframework.context.i18n.LocaleContextHolder;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super(com.kouforum.backend.shared.Messages.getMessageForLocale("kouforum.auth.invalid.credentials",
                LocaleContextHolder.getLocale()));

    }

}
