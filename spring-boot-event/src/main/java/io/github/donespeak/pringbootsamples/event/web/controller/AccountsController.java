package io.github.donespeak.pringbootsamples.event.web.controller;

import io.github.donespeak.pringbootsamples.event.entity.Account;
import io.github.donespeak.pringbootsamples.event.event.account.NormalAccountEvent;
import io.github.donespeak.pringbootsamples.event.event.account.AccountEventData;
import io.github.donespeak.pringbootsamples.event.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:48
 */
@Validated
@RestController
@RequestMapping("/accounts")
public class AccountsController {

	@Autowired
	private AccountService accountService;

	@GetMapping("")
	public List<Account> listAccount() {
		return accountService.listAccounts();
	}

	@GetMapping("/{id:\\d+}")
	public Account getAccount(@PathVariable long id) {
		return accountService.getAccount(id);
	}

	@PostMapping("")
	public Account createAccount(@Valid @RequestBody Account account) {
		accountService.createAccount(account);
		return accountService.getAccount(account.getId());
	}
}
