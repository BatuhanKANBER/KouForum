package com.kouforum.backend.exeptions;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthorizationExeption extends RuntimeException {
    public AuthorizationExeption() {
        super(com.kouforum.backend.shared.Messages.getMessageForLocale("kouforum.auth.invalid",
                LocaleContextHolder.getLocale()));

    }
}
