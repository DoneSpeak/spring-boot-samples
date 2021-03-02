package io.github.donespeak.pringbootsamples.event.web.controller;

import io.github.donespeak.pringbootsamples.event.listener.AsyncErrorEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DoneSpeak
 */
@RestController
@RequestMapping("/async")
public class AsyncErrorController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @GetMapping("/error")
    public void errorEvent(Integer value) {
        publisher.publishEvent(new AsyncErrorEvent(this, value));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
