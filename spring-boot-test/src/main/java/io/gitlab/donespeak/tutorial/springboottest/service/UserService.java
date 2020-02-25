package io.gitlab.donespeak.tutorial.springboottest.service;

import io.gitlab.donespeak.tutorial.springboottest.entity.User;

import java.util.List;

/**
 * @author Yang Guanrong
 * @date 2019/11/22 12:26
 */
public interface UserService {
    List<User> listUsers();

    User getUser(int userId);

    int insertUser(User user);

    User updateUser(User user);

    int deleteUser(int userId);

}
