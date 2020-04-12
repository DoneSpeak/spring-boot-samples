package io.github.donespeak.pringbootsamples.event.listener.todo;

import org.springframework.context.ApplicationListener;

import io.github.donespeak.pringbootsamples.event.event.todo.TodoEvent;

/**
 * @author DoneSpeak
 */
public class TodoFinishedListener implements ApplicationListener<TodoEvent.TodoFinishedEvent> {
    @Override
    public void onApplicationEvent(TodoEvent.TodoFinishedEvent event) {

    }
}
