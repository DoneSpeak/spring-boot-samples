package io.github.donespeak.pringbootsamples.event.event.account;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:52
 */
public class AccountCreatedEvent extends AccountEvent {
    public AccountCreatedEvent(AccountEventSource source) {
        super(source);
    }
}
