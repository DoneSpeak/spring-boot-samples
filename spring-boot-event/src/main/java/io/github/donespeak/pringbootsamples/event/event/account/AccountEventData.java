package io.github.donespeak.pringbootsamples.event.event.account;

import io.github.donespeak.pringbootsamples.event.entity.Account;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:45
 */
public class AccountEventData {
	private Account account;

	AccountEventData(Account account) {
		this.account = account;
	}

	public static AccountEventDataBuilder builder() {
		return new AccountEventDataBuilder();
	}

	public Account getAccount() {
		return this.account;
	}

	public String toString() {
		return "AccountEventData(account=" + this.getAccount() + ")";
	}

	public static class AccountEventDataBuilder {
		private Account account;

		AccountEventDataBuilder() {
		}

		public AccountEventData.AccountEventDataBuilder account(Account account) {
			this.account = account;
			return this;
		}

		public AccountEventData build() {
			return new AccountEventData(account);
		}

		public String toString() {
			return "AccountEventData.AccountEventDataBuilder(account=" + this.account + ")";
		}
	}
}