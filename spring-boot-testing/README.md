Spring Boot Test
----

```java
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
		accountService.createAccount(account);
		List<Account> accounts = accountService.listAccounts();
		accounts.forEach(a -> log.info("{}", a));
		Assert.assertEquals(accounts.size(), 1);
	}

	@Test
	public void listAccounts() {
		List<Account> accounts = accountService.listAccounts();
		accounts.forEach(a -> log.info("{}", a));
		Assert.assertEquals(accounts.size(), 0);
	}
}
```

使用的是h2数据库，两个Test方法没有相互影响。