package io.github.donespeak.pringbootsamples.event.event.account;

import io.github.donespeak.pringbootsamples.event.entity.Account;
import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:45
 */
@Data
public class AccountEventData {
	private Account account;
}