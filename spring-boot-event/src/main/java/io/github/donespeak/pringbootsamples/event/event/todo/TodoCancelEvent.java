package io.github.donespeak.pringbootsamples.event.event.todo;

import io.github.donespeak.pringbootsamples.event.entity.TodoItem;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 02:16
 */
public class TodoCancelEvent extends TodoEvent {

    public TodoCancelEvent(TodoItem source) {
        super(source);
    }
}
