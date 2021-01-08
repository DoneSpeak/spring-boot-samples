package io.github.donespeak.springbootsamples.springbootlogbook.web.controller;

import io.github.donespeak.springbootsamples.springbootlogbook.exception.ResourceNotFoundException;
import io.github.donespeak.springbootsamples.springbootlogbook.web.controller.entity.Account;
import io.github.donespeak.springbootsamples.springbootlogbook.web.controller.entity.request.AccountFilterRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yang Guanrong
 * @date 2020/04/03 23:30
 */
@RestController
@RequestMapping("/accounts")
public class AccountsController {

	@GetMapping("/{id:\\d+}")
	public Account getAccount(@PathVariable long id) {
		Account account = new Account();
		account.setId(id);
		account.setFirstName("阿");
		account.setLastName("D");
		return account;
	}

	@GetMapping("")
	public List<Account> findAccount(AccountFilterRequest request) {
		Account account = new Account();
		account.setFirstName(request.getFirstName());
		account.setLastName(request.getLastName());
		return Arrays.asList(account);
	}

	@PostMapping("")
	public Account createAccount(@RequestBody Account account) {
		return account;
	}

	@PatchMapping("")
	public Account updateAccount(@RequestBody Account account) {
		if (account.getId() == 0) {
			throw new ResourceNotFoundException("");
		}
		throw new IllegalArgumentException("account.firstName is too length.");
	}
}
