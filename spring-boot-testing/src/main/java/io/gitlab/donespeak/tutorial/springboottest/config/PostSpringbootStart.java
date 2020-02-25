package io.gitlab.donespeak.tutorial.springboottest.config;

import io.gitlab.donespeak.tutorial.springboottest.entity.User;
import io.gitlab.donespeak.tutorial.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

/**
 * Temporary
 *
 * @author Yang Guanrong
 * @date 2020/02/24 22:12
 */
@Configuration
public class PostSpringbootStart {

    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initDataToRedis() {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        List<User> users = userService.listUsers();
        String userPrefix = "user:";
        for(User user: users) {
            operations.set(userPrefix + user.getUserId(), user);
        }
    }
}
