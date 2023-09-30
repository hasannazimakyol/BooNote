package com.boonote.ws.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.boonote.ws.shared.Messages;

public class ActivationNotificationException extends RuntimeException {

    public ActivationNotificationException(){
        super(Messages.getMessageForLocale("boonote.create.user.email.failure", LocaleContextHolder.getLocale()));
    }
    
}
