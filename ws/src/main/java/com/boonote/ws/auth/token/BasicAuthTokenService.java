package com.boonote.ws.auth.token;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.boonote.ws.auth.dto.Credentials;
import com.boonote.ws.user.User;

@Service
public class BasicAuthTokenService implements TokenService {

    @Override
    public Token createToken(User user, Credentials creds) {

        String emailColonPassword = creds.email() + ":" + creds.password();
        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());
        return new Token("Basic", token);

    }

    @Override
    public User verifyToken(String authorizationHeader) {
        return null;
    }

}
