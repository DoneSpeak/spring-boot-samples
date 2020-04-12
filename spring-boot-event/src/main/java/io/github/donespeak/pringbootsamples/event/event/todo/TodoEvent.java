package io.github.donespeak.pringbootsamples.event.event.todo;

import io.github.donespeak.pringbootsamples.event.entity.TodoItem;
import org.springframework.context.ApplicationEvent;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 02:07
 */
public class TodoEvent extends ApplicationEvent {

    /**
     *
     * @param source 最初触发该事件的对象，不可为 null
     * @param todoEventSource 该类型事件携带的信息
     */
    public TodoEvent(Object source, TodoEventSource todoEventSource) {
        super(source);
    }

    public static class TodoCancelEvent extends TodoEvent {
        public TodoCancelEvent(Object source, TodoEventSource todoEventSource) {
            super(source, todoEventSource);
        }
    }

    /**
     * 创建Todo
     */
    public static class TodoCreatedEvent extends TodoEvent {

        public TodoCreatedEvent(Object source, TodoEventSource todoEventSource) {
            super(source, todoEventSource);
        }
    }

    /**
     * 完成一个Todo
     */
    public static class TodoFinishedEvent extends TodoEvent {
        public TodoFinishedEvent(Object source, TodoEventSource todoEventSource) {
            super(source, todoEventSource);
        }
    }
}
