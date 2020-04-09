package io.github.donespeak.pringbootsamples.event.web.controller;

import io.github.donespeak.pringbootsamples.event.entity.Account;
import io.github.donespeak.pringbootsamples.event.event.account.AccountCreatedEvent;
import io.github.donespeak.pringbootsamples.event.event.account.AccountEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:48
 */
@RestController
@RequestMapping("/accounts")
public class AccountsController {

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping("")
	public List<Account> listAccount() {
		Account account = new Account();
		return Arrays.asList(account);
	}

	@GetMapping("/{id:\\d+}")
	public Account getAccount(@PathVariable int id) {
		Account account = new Account();
		account.setId(id);
		return account;
	}

	@PostMapping("")
	public Account createAccount(@RequestBody Account account) {
		account.setId(1000);

		publisher.publishEvent(new AccountCreatedEvent(new AccountEventSource()));

		return account;
	}
}
