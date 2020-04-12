package io.github.donespeak.pringbootsamples.event.service.impl;

import java.util.List;

import io.github.donespeak.pringbootsamples.event.event.account.NormalAccountEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import io.github.donespeak.pringbootsamples.event.core.exception.ServiceException;
import io.github.donespeak.pringbootsamples.event.entity.Account;
import io.github.donespeak.pringbootsamples.event.event.account.AccountEventData;
import io.github.donespeak.pringbootsamples.event.event.account.ThrowExceptionAccountEvent;
import io.github.donespeak.pringbootsamples.event.repository.AccountRepository;
import io.github.donespeak.pringbootsamples.event.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yang Guanrong
 * @date 2020/04/10 18:03
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService, ApplicationEventPublisherAware {

    public ApplicationEventPublisher publisher;

    @Autowired
    public AccountRepository accountRepository;

    @Override
    @Transactional
    public Account createAccount(Account account) {
        accountRepository.save(account);
        try {
            publisher.publishEvent(new NormalAccountEvent(this, new AccountEventData()));
        } catch (Exception e) {
            // 捕获同步Listener抛出的异常
            throw new ServiceException(e.getMessage(), e);
        }
        // throw new RuntimeException("Throw exception on purpose.");
        return account;
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository.getOne(id);
    }

    @Override
    public List<Account> listAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
