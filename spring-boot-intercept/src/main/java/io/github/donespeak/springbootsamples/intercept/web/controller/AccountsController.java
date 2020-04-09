package io.github.donespeak.springbootsamples.intercept.web.controller;

import io.github.donespeak.springbootsamples.intercept.entity.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang Guanrong
 * @date 2020/04/03 23:30
 */
@RestController
@RequestMapping("/accounts")
public class AccountsController {

	@GetMapping("/{id:\\d+}")
	public Account getAccount(long id) {
		Account account = new Account();
		account.setId(id);
		account.setFirstName("阿");
		account.setLastName("D");
		return account;
	}
}
