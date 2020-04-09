package io.github.donespeak.pringbootsamples.event.event.account;

import org.springframework.context.ApplicationEvent;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:35
 */
public abstract class AccountEvent extends ApplicationEvent {

	public AccountEvent(AccountEventSource source) {
		super(source);
	}

	@Override
	public AccountEventSource getSource() {
		return (AccountEventSource) super.getSource();
	}
}
