package io.gitlab.donespeak.tutorial.springboottest.controller;

import io.gitlab.donespeak.tutorial.springboottest.entity.User;
import io.gitlab.donespeak.tutorial.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yang Guanrong
 * @date 2019/11/22 14:03
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> listUser() {
        return userService.listUsers();
    }

    @PostMapping("")
    public int insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @PutMapping("")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId:\\d+}")
    public int deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/{userId:\\d+}")
    public User getUser(@PathVariable  int userId) {
        return userService.getUser(userId);
    }
}
