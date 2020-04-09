package io.github.donespeak.pringbootsamples.event.event.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 02:17
 */
@Slf4j
@Component
public class TodoEventPublisher {

    private final ApplicationContext applicationContext;

    public TodoEventPublisher(ApplicationContext context) {
        this.applicationContext = context;
    }

    public void publish(TodoEvent ev) {
        log.info("publish event: " + ev);
        applicationContext.publishEvent(ev);
    }
}
