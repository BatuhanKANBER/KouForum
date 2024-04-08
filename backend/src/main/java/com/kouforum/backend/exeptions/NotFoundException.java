package com.kouforum.backend.exeptions;

import org.springframework.context.i18n.LocaleContextHolder;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super(com.kouforum.backend.shared.Messages.getMessageForLocale("kouforum.user.not.found",
                LocaleContextHolder.getLocale(), id));

    }
}
