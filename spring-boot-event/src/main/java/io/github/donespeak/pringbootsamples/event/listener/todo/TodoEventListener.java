package io.github.donespeak.pringbootsamples.event.listener.todo;

import io.github.donespeak.pringbootsamples.event.event.todo.TodoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 02:21
 */
@Slf4j
@Component
public class TodoEventListener {

    @EventListener
    public void todoCreated(TodoEvent.TodoCreatedEvent ev) {
        log.info("receive event: " + ev);
    }
}
