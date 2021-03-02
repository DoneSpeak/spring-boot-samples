package io.github.donespeak.springbootsamples.jpa.controller.transaction;

import org.springframework.context.ApplicationEvent;

/**
 * @author DoneSpeak
 */
public class AccountCreatedEvent extends ApplicationEvent {
    public AccountCreatedEvent(Object source) {
        super(source);
    }
}
