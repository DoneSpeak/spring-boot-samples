package io.github.donespeak.springsamples.quartz.service.impl;

import io.github.donespeak.springsamples.quartz.repository.model.User;
import io.github.donespeak.springsamples.quartz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author DoneSpeak
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getById(long id) {
        return new User();
    }
}
