package io.github.donespeak.pringbootsamples.event.listener.account;

import io.github.donespeak.pringbootsamples.event.entity.Account;
import io.github.donespeak.pringbootsamples.event.event.account.NormalAccountEvent;
import io.github.donespeak.pringbootsamples.event.event.account.ThrowExceptionAccountEvent;
import io.github.donespeak.pringbootsamples.event.repository.AccountRepository;
import io.github.donespeak.pringbootsamples.event.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:54
 */
@Slf4j
@Component
public class SyncAccountListener {

	@Autowired
	public AccountRepository accountRepository;

	/**
	 * 异步发送邮件
	 * @param event
	 */
	// @EventListener
	@Transactional
	@Order(2)
	@Async
	public void doOnNormalEvent(NormalAccountEvent event) {
		log.info("#### In doOnNormalEvent");
		Account account = new Account();
		account.setPassword("yyyyyyy");
		account.setFirstName("xY");
		account.setLastName("xxx");
		account.setEmail("xy@gr.com");

		accountRepository.save(account);

		// try {
		// 	log.debug("befor");
		// 	Thread.sleep(1000);
		// 	log.debug("after");
		// } catch (InterruptedException e) {
		// 	log.error(e.getMessage(), e);
		// }
		throw new RuntimeException("Throw exception on purpose.");
	}

	// @EventListener
	@Transactional
	@Order(1)
	public void doOnNormalEvent2(NormalAccountEvent event) {
		log.info("#### In doOnNormalEvent2");
		Account account = new Account();
		account.setPassword("aaaaaa");
		account.setFirstName("aa");
		account.setLastName("aaaa");
		account.setEmail("aaaa@gr.com");

		accountRepository.save(account);
	}

	@Transactional
	// @TransactionalEventListener
	public void doOnNormalEvent3(NormalAccountEvent event) {
		log.info("#### In doOnNormalEvent3");
		Account account = new Account();
		account.setPassword("33333");
		account.setFirstName("333");
		account.setLastName("333");
		account.setEmail("33333@gr.com");

		accountRepository.save(account);
		accountRepository.flush();
		List<Account> accountList = accountRepository.findAll();
		accountList.forEach(a -> log.info("EVENT: {}", a));
	}

	@Transactional
	// @TransactionalEventListener(fallbackExecution = true, phase = TransactionPhase.BEFORE_COMMIT)
	public void doOnNormalEvent4(NormalAccountEvent event) {
		log.info("#### In doOnNormalEvent4");
		Account account = new Account();
		account.setPassword("4444444");
		account.setFirstName("444");
		account.setLastName("4444");
		account.setEmail("4444@gr.com");

		accountRepository.save(account);
		List<Account> accountList = accountRepository.findAll();
		accountList.forEach(a -> log.info("EVENT: {}", a));
	}

	@Transactional
	@EventListener
	public Account doOnNormalEvent5(NormalAccountEvent event) {
		log.info("#### In doOnNormalEvent5");
		Account account = new Account();
		account.setPassword("55555");
		account.setFirstName("55");
		account.setLastName("5555");
		account.setEmail("5555@gr.com");

		accountRepository.save(account);

		return account;
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
