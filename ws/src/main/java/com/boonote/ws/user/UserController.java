package com.boonote.ws.user;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boonote.ws.shared.GenericMessage;
import com.boonote.ws.shared.Messages;
import com.boonote.ws.user.dto.UserCreate;
import com.boonote.ws.user.dto.UserDTO;
import jakarta.validation.Valid;

@RestController
public class UserController {

    // @Autowired
    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody UserCreate user) {
        userService.save(user.toUser());
        String message = Messages.getMessageForLocale("boonote.create.user.success.message",
                LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @PatchMapping("/api/v1/users/{token}/active")
    GenericMessage activateUser(@PathVariable String token) {
        userService.activateUser(token);
        String message = Messages.getMessageForLocale("boonote.activate.user.success.message",
                LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @GetMapping("/api/v1/users")
    Page<UserDTO> getUsers(Pageable page) {
        return userService.getUsers(page).map(UserDTO::new);
    }

    @GetMapping("/api/v1/users/{id}")
    UserDTO getUserById(@PathVariable long id) {
        return new UserDTO(userService.getUser(id));
    }

}
