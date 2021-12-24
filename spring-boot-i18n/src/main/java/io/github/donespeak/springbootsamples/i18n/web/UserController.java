package io.github.donespeak.springbootsamples.i18n.web;

import io.github.donespeak.springbootsamples.i18n.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public User createUser(@RequestBody @Valid User user) {
        return user;
    }
}
