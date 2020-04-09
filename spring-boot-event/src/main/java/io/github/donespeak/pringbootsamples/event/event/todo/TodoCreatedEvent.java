package io.github.donespeak.pringbootsamples.event.event.todo;

import io.github.donespeak.pringbootsamples.event.entity.TodoItem;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 02:16
 */
public class TodoCreatedEvent extends TodoEvent {

    public TodoCreatedEvent(TodoItem source) {
        super(source);
    }
}
