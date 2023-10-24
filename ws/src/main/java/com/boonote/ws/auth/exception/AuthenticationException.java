package com.boonote.ws.auth.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.boonote.ws.shared.Messages;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(){
        super(Messages.getMessageForLocale("boonote.auth.invalid.credentials", LocaleContextHolder.getLocale()));
    }

}
