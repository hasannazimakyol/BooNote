package com.boonote.ws.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boonote.ws.user.User;
import com.boonote.ws.user.UserService;

@Service
public class AppUserDetailsService implements UserDetailsService {// springin kullanıcıları bulması için kullandığı
                                                                  // servis

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User inDB = userService.findByEmail(email);
        if (inDB == null) {
            throw new UsernameNotFoundException(email + " is not found");
        }
        return new CurrentUser(inDB);
    }

}
