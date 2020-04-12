package io.github.donespeak.pringbootsamples.event.service;

import io.github.donespeak.pringbootsamples.event.entity.Account;

import java.util.List;

/**
 * @author Yang Guanrong
 * @date 2020/04/10 18:02
 */
public interface AccountService {
	Account createAccount(Account account);

	Account getAccount(Long id);

	List<Account> listAccounts();
}
