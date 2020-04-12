package io.github.donespeak.pringbootsamples.event.event.account;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:52
 */
public class NormalAccountEvent extends AccountEvent {
    public NormalAccountEvent(Object source, AccountEventData eventData) {
        super(source, eventData);
    }
}
