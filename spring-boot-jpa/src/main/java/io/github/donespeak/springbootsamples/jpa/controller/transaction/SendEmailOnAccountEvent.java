package io.github.donespeak.springbootsamples.jpa.controller.transaction;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author DoneSpeak
 */
@Component
public class SendEmailOnAccountEvent {


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendEmailOnAccountCreated(AccountCreatedEvent event) {

    }
}
