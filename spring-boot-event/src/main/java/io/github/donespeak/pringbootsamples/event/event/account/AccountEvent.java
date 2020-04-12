package io.github.donespeak.pringbootsamples.event.event.account;

import org.springframework.context.ApplicationEvent;

/**
 * 账户相关的事件
 *
 * @author Yang Guanrong
 * @date 2020/04/04 10:35
 */
public abstract class AccountEvent extends ApplicationEvent {

	/**
	 * 该类型事件携带的信息
	 */
	private AccountEventData eventData;

	/**
	 *
	 * @param source 最初触发该事件的对象，不可为 null
	 * @param eventData 该类型事件携带的信息
	 */
	public AccountEvent(Object source, AccountEventData eventData) {
		super(source);
		this.eventData = eventData;
	}

	public AccountEventData getEventData() {
		return eventData;
	}
}
