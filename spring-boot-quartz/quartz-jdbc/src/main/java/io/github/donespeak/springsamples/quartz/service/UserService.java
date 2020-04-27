package io.github.donespeak.springsamples.quartz.service;

import io.github.donespeak.springsamples.quartz.repository.model.User;

/**
 * @author DoneSpeak
 */
public interface UserService {
    User getById(long id);
}
