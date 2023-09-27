package com.boonote.ws.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boonote.ws.user.exception.NotUniqueEmailException;

@Service
public class UserService {

    // @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new NotUniqueEmailException();
        }
    }

}
