package io.github.donespeak.pringbootsamples.event.repository;

import io.github.donespeak.pringbootsamples.event.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yang Guanrong
 * @date 2020/04/10 17:59
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
