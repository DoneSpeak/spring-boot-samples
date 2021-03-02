package io.github.donespeak.pringbootsamples.event.web.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.donespeak.pringbootsamples.event.event.ErrorEvent;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author DoneSpeak
 */
@Slf4j
@RestController
@RequestMapping("errors")
public class ErrorController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @GetMapping("/event")
    public void errorEvent() {
        try {
            publisher.publishEvent(new ErrorEvent(this));
        } catch (UndeclaredThrowableException ex) {
            // log.error(ex.getMessage(), ex);
            log.error(ex.getUndeclaredThrowable().getMessage(), ex.getUndeclaredThrowable());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
