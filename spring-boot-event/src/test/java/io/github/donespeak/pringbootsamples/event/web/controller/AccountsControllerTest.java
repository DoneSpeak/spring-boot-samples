package io.github.donespeak.pringbootsamples.event.web.controller;

import io.github.donespeak.pringbootsamples.event.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author DoneSpeak
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsControllerTest {

    @Autowired
    private AccountsController accountsController;

    @Test
    public void createAccount() {
        Account account = new Account();
        account.setEmail("gr@gr.com");
        account.setFirstName("gr");
        account.setLastName("yang");
        account.setPassword("xxxxxxx");

        accountsController.createAccount(account);
    }
}