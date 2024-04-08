package com.kouforum.backend.exeptions;

import org.springframework.context.i18n.LocaleContextHolder;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(){
        super(com.kouforum.backend.shared.Messages.getMessageForLocale("kouforum.activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }
}
