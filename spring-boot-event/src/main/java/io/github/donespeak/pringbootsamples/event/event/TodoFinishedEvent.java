package io.github.donespeak.pringbootsamples.event.event;

import io.github.donespeak.pringbootsamples.event.entity.TodoItem;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 02:17
 */
public class TodoFinishedEvent extends TodoEvent {

    public TodoFinishedEvent(TodoItem source) {
        super(source);
    }
}
