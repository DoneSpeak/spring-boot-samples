package io.github.donespeak.springbootsamples.jpa.repository;

import io.github.donespeak.springbootsamples.jpa.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DoneSpeak
 */
public interface AccountJpa extends JpaRepository<Account, String> {
}
