package io.github.donespeak.pringbootsamples.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author DoneSpeak
 */
@Slf4j
@Component
public class AsyncListener {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
    public void throwException(AsyncErrorEvent event) throws InterruptedException {
        log.info("receive a event(" + event.getClass() + ") with value " + event.getValue() + ".");
        if (event.getValue() == 1) {
            throw new RuntimeException("this is an error from " + event.getSource());
        }
        if (event.getValue() == 2) {
            long milli = 1000 * 60L;
            log.info("start to sleep for " + milli + " milli.");
            Thread.sleep(milli);
            log.info("wake up from sleep.");
        }
    }
}
