package com.boonote.ws.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.boonote.ws.shared.Messages;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id){
        super(Messages.getMessageForLocale("boonote.user.not.found", LocaleContextHolder.getLocale(), id));
    }
}
