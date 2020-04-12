package io.github.donespeak.pringbootsamples.event.listener.account;

import io.github.donespeak.pringbootsamples.event.event.account.NormalAccountEvent;
import io.github.donespeak.pringbootsamples.event.event.account.ThrowExceptionAccountEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:54
 */
@Slf4j
@Component
public class SyncAccountListener {

	/**
	 * 异步发送邮件
	 * @param event
	 */
	@EventListener
	public void doOnNormalEvent(NormalAccountEvent event) {
		try {
			log.debug("befor");
			Thread.sleep(1000);
			log.debug("after");
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 发送短信
	 * @param event
	 */
	@EventListener
	public void doOnExceptionEvent(ThrowExceptionAccountEvent event) {
		try {
			log.debug("before");
			Thread.sleep(1000);
			log.debug("after");
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
		throw new RuntimeException("Throw a exception on purpose");
	}
}
