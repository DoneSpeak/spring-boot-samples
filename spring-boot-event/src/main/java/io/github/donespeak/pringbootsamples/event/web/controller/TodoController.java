package io.github.donespeak.pringbootsamples.event.web.controller;

import io.github.donespeak.pringbootsamples.event.entity.TodoItem;
import io.github.donespeak.pringbootsamples.event.event.todo.TodoEvent;
import io.github.donespeak.pringbootsamples.event.event.todo.TodoEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 02:31
 */
@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoEventPublisher todoEventPublisher;

    @PostMapping("")
    public TodoItem creatTodo(@RequestBody TodoItem todoItem) {
        todoEventPublisher.publish(new TodoEvent.TodoCreatedEvent(this, null));
        return todoItem;
    }
}
