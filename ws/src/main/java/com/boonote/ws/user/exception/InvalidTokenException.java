package com.boonote.ws.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.boonote.ws.shared.Messages;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(){
        super(Messages.getMessageForLocale("boonote.activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }
    
}
