package io.github.donespeak.pringbootsamples.event.event.account;

/**
 * 会抛出异常的事件
 */
public class ThrowExceptionAccountEvent extends AccountEvent {
	public ThrowExceptionAccountEvent(Object source, AccountEventData eventData) {
		super(source, eventData);
	}
}
