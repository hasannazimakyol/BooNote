package com.boonote.ws.error;

import java.nio.file.AccessDeniedException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
// import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ DisabledException.class, AccessDeniedException.class })
    ResponseEntity<?> handleDisabledException(DisabledException exception, HttpServletRequest request) {
        ApiError error = new ApiError();
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        if (exception instanceof DisabledException) {
            error.setStatus(401);
        // } else if (exception instanceof AccessDeniedException) {
        } else  {
            error.setStatus(403);
        }
        return ResponseEntity.status(error.getStatus()).body(error);
    }

}
