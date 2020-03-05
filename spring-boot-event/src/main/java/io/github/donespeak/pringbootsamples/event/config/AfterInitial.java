package io.github.donespeak.pringbootsamples.event.config;

import io.github.donespeak.pringbootsamples.event.entity.TodoItem;
import io.github.donespeak.pringbootsamples.event.event.TodoCreatedEvent;
import io.github.donespeak.pringbootsamples.event.event.TodoEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 02:19
 */
@Configuration
public class AfterInitial {

}
