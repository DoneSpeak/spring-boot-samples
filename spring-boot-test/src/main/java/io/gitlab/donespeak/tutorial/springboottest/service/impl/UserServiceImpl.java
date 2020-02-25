package io.gitlab.donespeak.tutorial.springboottest.service.impl;

import io.gitlab.donespeak.tutorial.springboottest.entity.User;
import io.gitlab.donespeak.tutorial.springboottest.mapper.UserMapper;
import io.gitlab.donespeak.tutorial.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yang Guanrong
 * @date 2019/11/22 12:27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public User getUser(int userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User updateUser(User user) {
        userMapper.updateById(user);
        return userMapper.selectById(user.getUserId());
    }

    @Override
    public int deleteUser(int userId) {
        return userMapper.deleteById(userId);
    }
}
