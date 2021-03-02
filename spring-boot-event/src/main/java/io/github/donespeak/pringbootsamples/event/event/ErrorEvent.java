package io.github.donespeak.pringbootsamples.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author DoneSpeak
 */
public class ErrorEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ErrorEvent(Object source) {
        super(source);
    }

    public static class ErrorException extends Exception {

        public ErrorException(String message) {
            super(message);
        }
    }
}
