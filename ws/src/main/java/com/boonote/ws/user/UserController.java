package com.boonote.ws.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boonote.ws.shared.GenericMessage;

@RestController
public class UserController {

    // @Autowired
    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@RequestBody User user) {
        userService.save(user);
        return new GenericMessage("User is created");
    }

}
