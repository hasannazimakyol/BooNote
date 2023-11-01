package com.boonote.ws.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boonote.ws.auth.dto.AuthResponse;
import com.boonote.ws.auth.dto.Credentials;
import com.boonote.ws.user.User;
import com.boonote.ws.user.UserService;
import com.boonote.ws.user.dto.UserDTO;
import com.boonote.ws.auth.exception.AuthenticationException;
import com.boonote.ws.auth.token.Token;
import com.boonote.ws.auth.token.TokenService;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    public AuthResponse authenticate(Credentials creds) {
        User inDB = userService.findByEmail(creds.email());
        if (inDB == null)
            throw new AuthenticationException();
        if (!passwordEncoder.matches(creds.password(), inDB.getPassword()))
            throw new AuthenticationException();

        Token token = tokenService.createToken(inDB, creds);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(new UserDTO(inDB));
        return authResponse;
    }

}
