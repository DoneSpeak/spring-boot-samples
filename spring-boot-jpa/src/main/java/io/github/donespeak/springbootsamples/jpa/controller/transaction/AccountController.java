package io.github.donespeak.springbootsamples.jpa.controller.transaction;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DoneSpeak
 */
@RestController
@RequestMapping("account")
public class AccountController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @PostMapping
    public void createAccount() {
        publisher.publishEvent(new AccountCreatedEvent(this));
    }
}
