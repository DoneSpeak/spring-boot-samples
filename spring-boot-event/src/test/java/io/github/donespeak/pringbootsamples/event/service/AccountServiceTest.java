package io.github.donespeak.pringbootsamples.event.service;

import io.github.donespeak.pringbootsamples.event.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.List;

/**
 * @author DoneSpeak
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;

	@Test
	public void createAccount() {
		Account account = new Account();
		account.setEmail("gr@gr.com");
		account.setFirstName("gr");
		account.setLastName("yang");
		account.setPassword("xxxxxxx");
		try {
			accountService.createAccount(account);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		List<Account> accounts = accountService.listAccounts();
		accounts.forEach(a -> log.info("TEST: {}", a));
		Assert.assertEquals(2, accounts.size());
	}

	@Test
	public void listAccounts() {
		List<Account> accounts = accountService.listAccounts();
		accounts.forEach(a -> log.info("{}", a));
		Assert.assertEquals(0, accounts.size());
	}
}
