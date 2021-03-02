package io.github.donespeak.pringbootsamples.event.listener;

import io.github.donespeak.pringbootsamples.event.event.ErrorEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author DoneSpeak
 */
@Component
public class ErrorEventListener {

    @EventListener
    public void throwError(ErrorEvent errorEvent) throws ErrorEvent.ErrorException {
        throw new ErrorEvent.ErrorException("This is an error.");
    }
}
