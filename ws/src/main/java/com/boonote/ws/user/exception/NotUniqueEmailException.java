package com.boonote.ws.user.exception;

import java.util.Collections;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;

import com.boonote.ws.shared.Messages;

public class NotUniqueEmailException extends RuntimeException {

    public NotUniqueEmailException() {
        super(Messages.getMessageForLocale("boonote.error.validation", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors() {
        return Collections.singletonMap("email",
                Messages.getMessageForLocale("boonote.constraint.email.notunique", LocaleContextHolder.getLocale()));
    }

}
