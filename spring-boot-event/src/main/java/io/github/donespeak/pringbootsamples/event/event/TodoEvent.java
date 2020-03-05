package io.github.donespeak.pringbootsamples.event.event;

import io.github.donespeak.pringbootsamples.event.entity.TodoItem;
import org.springframework.context.ApplicationEvent;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 02:07
 */
public class TodoEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public TodoEvent(TodoItem source) {
        super(source);
    }
}
