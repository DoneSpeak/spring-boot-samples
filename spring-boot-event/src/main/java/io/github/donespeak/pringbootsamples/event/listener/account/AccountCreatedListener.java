package io.github.donespeak.pringbootsamples.event.listener.account;

import io.github.donespeak.pringbootsamples.event.event.account.AccountCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:54
 */
@Component
public class AccountCreatedListener {

	/**
	 * 发送邮件
	 * @param event
	 */
	@Async
	@EventListener
	public void sendEmail(AccountCreatedEvent event) {
		// TODO
	}

	/**
	 * 发送短信
	 * @param event
	 */
	@EventListener
	public void sendSMS(AccountCreatedEvent event) {
		// TODO
	}

	/**
	 * 添加积分
	 * @param event
	 */
	@EventListener
	public void addIntegral(AccountCreatedEvent event) {
		// TODO
	}
}
