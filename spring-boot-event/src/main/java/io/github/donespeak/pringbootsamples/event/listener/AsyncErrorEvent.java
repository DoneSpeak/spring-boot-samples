package io.github.donespeak.pringbootsamples.event.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author DoneSpeak
 */
public class AsyncErrorEvent extends ApplicationEvent {

    private int value;

    public AsyncErrorEvent(Object source, int value) {
        super(source);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
