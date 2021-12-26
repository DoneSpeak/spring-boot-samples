package io.github.donespeak.springbootsamples.i18n.web;

import io.github.donespeak.springbootsamples.i18n.core.MessageCode;
import io.github.donespeak.springbootsamples.i18n.core.ServiceException;
import io.github.donespeak.springbootsamples.i18n.entity.User;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String TOKEN_EMAIL = "token@example.com";

    @PostMapping
    public User createUser(@RequestBody @Valid User user) {
        if (TOKEN_EMAIL.equalsIgnoreCase(user.getEmail())) {
            String message = String.format("The email %s is token.", user.getEmail());
            throw new ServiceException(MessageCode.USER_EMAIL_TOKEN, message);
        }
        return user;
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable String userId) {
        return null;
    }
}
