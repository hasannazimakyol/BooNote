package com.boonote.ws.auth.token;

import com.boonote.ws.auth.dto.Credentials;
import com.boonote.ws.user.User;

public interface TokenService {

    public Token createToken(User user, Credentials creds);

    public User verifyToken(String authorizationHeader);

}
