Spring Event
===

写在前面
---

基本开发
---

Event是Spring中的概念，不是Spring Event所有的。只要添加了`spring-context`依赖就可以引入了Spring的事件。  

要使用Event只要准备三个部分：

- 事件类：定义事件，继承`ApplicationEvent`的类成为一个事件类。
- 发布者：发布事件，通过`ApplicationEventPublisher`发布事件。
- 监听者：监听并处理事件，实现`ApplicationListener`接口或者使用`@EventListener`注解。

### 事件类

只要继承`org.springframework.context.ApplicationEvent`，便是一个Spring Event类。一般我们会专门为一个类型的Event写一个抽象的事件类，作为该类型的所有事件的父类。

```java
/**
 * 账户相关的事件
 */
public abstract class AccountEvent extends ApplicationEvent {

	/**
	 * 该类型事件携带的信息
	 */
	private AccountEventData eventData;

	/**
	 *
	 * @param source 最初触发该事件的对象
	 * @param eventData 该类型事件携带的信息
	 */
	public AccountEvent(Object source, AccountEventData eventData) {
		super(source);
		this.eventData = eventData;
	}

	public AccountEventData getEventData() {
		return eventData;
	}
}
```

然后定义具体的发布事件。这里推荐使用类实现的方式来发布具体的事件，而不是在事件中使用`private String eventType`的方式发布。使用具体的类表示具体的事件，监听器只要监听具体的事件类即可，而无需再做判断，同时也不需要再另外维护事件类型列表。

```java
public class AccountCreatedEvent extends AccountEvent {
    public AccountCreatedEvent(Object source, AccountEventData eventData) {
        super(source, eventData);
    }
}
```

还有一种实践是，利用泛型定义一个统一的父类。
```java
public abstract class BaseEvent<T> extends ApplicationEvent {

	/**
	 * 该类型事件携带的信息
	 */
	private T eventData;

	/**
	 *
	 * @param source 最初触发该事件的对象
	 * @param eventData 该类型事件携带的信息
	 */
	public BaseEvent(Object source, T eventData) {
		super(source);
		this.eventData = eventData;
	}

	public T getEventData() {
		return eventData;
	}
}
```

然后再指令事件类型即可。
```java
public class AccountCreatedEvent extends BaseEvent<AccountEventData> {
    public AccountCreatedEvent(Object source, AccountEventData eventData) {
        super(source, eventData);
    }
}

public class TodoCreatedEvent extends BaseEvent<TodoEventData> {
    public TodoCreatedEvent(Object source, TodoEventData eventData) {
        super(source, eventData);
    }
}
```

以上均使用了一个`AccountEventData`，这是为了方便拓展，如果后续需要给事件增加新的字段，可以直接在该类上增加即可，而无需修改所有的子事件类。

### 发布者

发布者负责发布消息，有三种实现方式。Spring容器中默认的`ApplicationEventPublisher`是`AbstractApplicationContext`，同时`AbstractApplicationContext`也是`ApplicationContext`的一个子类，也就是说，Spring默认使用`AbstractApplicationContext`发布事件。

**方式1：直接使用`ApplicationEventPublisher`(推荐)**  
```java
import org.springframework.context.ApplicationEventPublisher;

public class AccountsController {

	@PostMapping("")
	public Account createAccount(@RequestBody Account account) {
		
		...

		publisher.publishEvent(new AccountCreatedEvent(this, new AccountEventData()));
		return account;
	}
}
```

**方式2：实现`ApplicationEventPublisherAware`接口(推荐)**  
```java
public interface ApplicationEventPublisherAware extends Aware {

	void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher);
}
```

这个方式其实就是注入一个`ApplicationEventPublisher`，然后再用`ApplicationEventPublisher#publisheEvent(ApplicationEvent)`方法发布事件。

```java
package org.springframework.data.rest.webmvc;

@RepositoryRestController
class RepositoryEntityController extends AbstractRepositoryRestController implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher publisher;
    
    @Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

    private ResponseEntity<ResourceSupport> saveAndReturn(Object domainObject, RepositoryInvoker invoker,
    			HttpMethod httpMethod, PersistentEntityResourceAssembler assembler, boolean returnBody) {

        publisher.publishEvent(new BeforeSaveEvent(domainObject));
        Object obj = invoker.invokeSave(domainObject);
        publisher.publishEvent(new AfterSaveEvent(obj));

        ...
    }
    ...
}
```

如果你希望你的Service类能够发布事件，可以实现这个接口`ApplicationEventPublisherAware`。

**方式3：使用`ApplicationContext#publishEvent(ApplicationEvent)`发布。**  
```java
public class AccountEventPublisher {

    private final ApplicationContext applicationContext;

    public AccountEventPublisher(ApplicationContext context) {
        this.applicationContext = context;
    }

    public void publish(TodoEvent ev) {
        applicationContext.publishEvent(ev);
    }
}
```

`ApplicationContext`是`ApplicationEventPublisher`的一个实现，在有前面的两种方案之后，其实就不需要这个重复封装实现方案了。当然，你也可以直接使用`ApplicationContext`。

### 监听器

监听器负责接收和处理事件。

**基本用法**  

有两种实现方法：实现`ApplicationListener`接口或者使用`@EventListener`注解。

**实现`ApplicationListener`接口：**  

```java
public class TodoFinishedListener implements ApplicationListener<TodoEvent.TodoFinishedEvent> {
    @Override
    public void onApplicationEvent(TodoEvent.TodoFinishedEvent event) {
        // do something
    }
}
```

**使用`@EventListener`注解(推荐)**  
```java
@Slf4j
@Component
public class SyncAccountListener {

	/**
	 * 异步发送邮件
	 * @param event
	 */
	@EventListener
	public void doOnNormalEvent(NormalAccountEvent event) {
		try {
			log.debug("befor");
			Thread.sleep(1000);
			log.debug("after");
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}
}
```

可以使用`@EventListener`在同一个类中用不同的方法监听多个不同的事件。相对于实现`ApplicationListener`接口，使用`@EventListener`会更加灵活。  

**`@EventListener` 注解的说明**  

```java
@EventListener(value = {AccountCreatedEvent.class, AccountUpdatedEvent.class}, condition = "#event.account.age > 10")
public void doSomethingOnAccountEvent(AccountEvent event) {
	// TODO
}
```

- `value`: 监听的事件（组），用于支持同一父类的事件
- `class`: 同value
- `condition`: SpEL，使得Event Handler变得conditional
    - `#root.event`, Application的引用
    - `#root.args`, 表示方法参数，#root.args[0]表示第0个方法参数
    - `#<name>`, 如上面代码中的#event表示以参数名关联参数

**`@Listener` 注解方法返回值**    

> And there's an alternative way of publishing events. If we return a non-null value from a method annotated with @EventListener as the result, Spring Framework will send that result as a new event for us. Moreover, we can publish multiple new events by returning them in a collection as the result of event processing.
> 
> https://www.baeldung.com/spring-events

### ApplicationEventMulticaster 事件广播器

事件广播器负责将`ApplicationEventPublisher`发布的事件广播给所有的监听器。如果没有提供事件广播器，Spring会自动使用`SimpleApplicationEventMulticaster`作为默认的事件广播器。

#### 构建事件基础    

**AbstractApplicationContext.java**中的`refresh()`方法中构建了完整的事件基础。`AbstractApplicationContext#initApplicationEventMulticaster()`初始化了事件广播器，`AbstractApplicationContext#registerListeners()`则负责添加Spring容器中的事件监听器。
    
```java
/**
 * Initialize the ApplicationEventMulticaster.
 * Uses SimpleApplicationEventMulticaster if none defined in the context.
 * @see org.springframework.context.event.SimpleApplicationEventMulticaster
 */
protected void initApplicationEventMulticaster() {
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();
    if (beanFactory.containsLocalBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME)) {
        this.applicationEventMulticaster =
                beanFactory.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        if (logger.isTraceEnabled()) {
            logger.trace("Using ApplicationEventMulticaster [" + this.applicationEventMulticaster + "]");
        }
    }
    else {
        this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
        if (logger.isTraceEnabled()) {
            logger.trace("No '" + APPLICATION_EVENT_MULTICASTER_BEAN_NAME + "' bean, using " +
                    "[" + this.applicationEventMulticaster.getClass().getSimpleName() + "]");
        }
    }
}
```

```java
/**
 * Add beans that implement ApplicationListener as listeners.
 * Doesn't affect other listeners, which can be added without being beans.
 */
protected void registerListeners() {
    // Register statically specified listeners first.
    for (ApplicationListener<?> listener : getApplicationListeners()) {
        getApplicationEventMulticaster().addApplicationListener(listener);
    }

    // Do not initialize FactoryBeans here: We need to leave all regular beans
    // uninitialized to let post-processors apply to them!
    String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
    for (String listenerBeanName : listenerBeanNames) {
        getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
    }

    // Publish early application events now that we finally have a multicaster...
    Set<ApplicationEvent> earlyEventsToProcess = this.earlyApplicationEvents;
    this.earlyApplicationEvents = null;
    if (earlyEventsToProcess != null) {
        for (ApplicationEvent earlyEvent : earlyEventsToProcess) {
            getApplicationEventMulticaster().multicastEvent(earlyEvent);
        }
    }
}
```

#### 事件发布

我们可以在`AbstractApplicationContext.java`中找到事件的直接发布方法。`AbstractApplicationContext#publish(Object, ResolvableType)`中，事件的发布是通过`ApplicationEventMulticaster`做的广播发布。

```java
protected void publishEvent(Object event, @Nullable ResolvableType eventType) {
    Assert.notNull(event, "Event must not be null");

    // Decorate event as an ApplicationEvent if necessary
    ApplicationEvent applicationEvent;
    if (event instanceof ApplicationEvent) {
        applicationEvent = (ApplicationEvent) event;
    }
    else {
        applicationEvent = new PayloadApplicationEvent<>(this, event);
        if (eventType == null) {
            eventType = ((PayloadApplicationEvent<?>) applicationEvent).getResolvableType();
        }
    }

    // Multicast right now if possible - or lazily once the multicaster is initialized
    if (this.earlyApplicationEvents != null) {
        this.earlyApplicationEvents.add(applicationEvent);
    }
    else {
        getApplicationEventMulticaster().multicastEvent(applicationEvent, eventType);
    }

    // Publish event via parent context as well...
    if (this.parent != null) {
        if (this.parent instanceof AbstractApplicationContext) {
            ((AbstractApplicationContext) this.parent).publishEvent(event, eventType);
        }
        else {
            this.parent.publishEvent(event);
        }
    }
}
```

- [ ] AbstractApplicationContext是否是ApplicationEventPublisher的默认实现

在前面我们可以知道，`SimpleApplicationEventMulticaster`是`ApplicationEventMulticaster`在Spring容器中的默认实现。理所当然地可以从中找到事件发布的真实方式。`multicastEvent`方法会找到监听当前事件的**所有**监听器，然后再执行执行监听方法。  

`SimpleApplicationEventMulticaster`中有两个属性，`Executor taskExecutor`和`ErrorHandler errorHandler`。前者可以定义**所有监听器**是否异步执行，默认为null，等价于同步执行的`SyncTaskExecutor`，你也可以使用`SimpleAsyncTaskExecutor`将所有监听器设置为异步执行。但这里有一点非常重要，如果你设置了Executor为异步的，那么所有的监听器都会异步执行，监听器和调用类会处于不同的上下文，不同的事务中，除非你有办法让TaskExecutor支持。其实我们完全不用通过修改广播器taskExecutor的方式来让监听器异步，可以通过`@EnableAsync`启动异步，并`@Async`将监听器设置为异步执行。通过`@Async`的方式，可以自由地决定任意一个监听器是否为异步，而非暴力地让所有的监听器都异步化。  

```java
public void multicastEvent(final ApplicationEvent event, @Nullable ResolvableType eventType) {
    ResolvableType type = (eventType != null ? eventType : resolveDefaultEventType(event));
    Executor executor = getTaskExecutor();
    for (ApplicationListener<?> listener : getApplicationListeners(event, type)) {
        if (executor != null) {
            executor.execute(() -> invokeListener(listener, event));
        }
        else {
            invokeListener(listener, event);
        }
    }
}
```

而`ErrorHandler errorHandler`则定义了在监听器发生异常之后的行为，在这里你可以看到，如果没有定义`errorHandler`的话，会直接抛到上一层。

- [ ] 默认的ErrorHandler

```java
protected void invokeListener(ApplicationListener<?> listener, ApplicationEvent event) {
    ErrorHandler errorHandler = getErrorHandler();
    if (errorHandler != null) {
        try {
            doInvokeListener(listener, event);
        }
        catch (Throwable err) {
            errorHandler.handleError(err);
        }
    }
    else {
        doInvokeListener(listener, event);
    }
}

private void doInvokeListener(ApplicationListener listener, ApplicationEvent event) {
    try {
        listener.onApplicationEvent(event);
    }
    catch (ClassCastException ex) {
        String msg = ex.getMessage();
        if (msg == null || matchesClassCastMessage(msg, event.getClass())) {
            // Possibly a lambda-defined listener which we could not resolve the generic event type for
            // -> let's suppress the exception and just log a debug message.
            Log logger = LogFactory.getLog(getClass());
            if (logger.isTraceEnabled()) {
                logger.trace("Non-matching event type for listener: " + listener, ex);
            }
        }
        else {
            throw ex;
        }
    }
}
```

可以修改默认的`ApplicationEventMulticaster`，或者直接继承/实现`AbstractApplicationEventMulticaster/ApplicationEventMulticaster`。

```java
// 是谁，是谁让我所有的监听器都编程异步了。-》原来是有人修改了事件广播器的taskExecutor为异步的了。
public class EventConfig {

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }
}
```

- `@EnableAspectJAutoProxy(proxyTargetClass = true)` 作用

同步
---

### Listener默认都是同步的

```java
public Account createAccount(Account account) {
    ...
    try {
        publisher.publishEvent(new ThrowExceptionAccountEvent(this, new AccountEventData()));
    } catch (Exception e) {
        // 捕获同步Listener抛出的异常
        throw new ServiceException(e.getMessage(), e);
    }
    ...
    return account;
}
```

以上的方法中，`publishEvent`会执行完所有的同步监听器之后才返回。既然是同步，那么你可以通过`@Order`注解来指定执行顺序。使用同步的监听器，可以让事件参与到publisher所在的事务中。从上面对`ApplicationEventMulticaster`的讲解中可以知道，同步的执行其实就是简单的方法调用罢了。  

### 异常与事务

上面已经讲到，同步的listener如果发生异常，而且没有被ErrorHandler拦截的话，是会往上抛出的，直接在publishEvent方法中捕获即可。

- [ ] listener和publish方法是否在同一个事务中 -> listener回滚是否会让publish方法处回滚，publisher是否会让listener回滚
- [ ] 不同的listener是否在同一事务中 -> 一个listener回滚是否会让其他的listener也回滚
- [ ] 与事务隔离级别，事务传播行为

使用`@TransactionalEventListener`是`@EventListener`的拓展，可以指定监听器和发布事件的方法的事务隔离级别。隔离级别确保数据的有效性。

- AFTER_COMMIT (default) is used to fire the event if the transaction has completed successfully
- AFTER_ROLLBACK – if the transaction has rolled back
- AFTER_COMPLETION – if the transaction has completed (an alias for AFTER_COMMIT and AFTER_ROLLBACK)
- BEFORE_COMMIT is used to fire the event right before transaction commit

- [ ] 什么场景使用什么隔离级别，使用错误会造成什么后果

`@TransactionalEventListener` 注解的说明

- `fallbackExecution` 默认为false，指发布事件的方法没有事务控制时，监听器不进行监听事件，此为默认情况！ fallbackExecution=true，则指发布事件的方法没有事务控制时，监听方法仍可以监听事件进行处理。也就是说，`@TransactionalEventListener` 默认只会监听有事务的发布事件方法。

```java
// 默认情况，在事务中的Event将会被执行,其他情况不触发。fallbackExecution = true 让其他情况也可以执行。
@TransactionalEventListener(fallbackExecution = true)
public void afterRegisterSendMail(MessageEvent event) {
    mailService.send(event);
}
```

参考：
- [在Spring中使用异步事件实现同步事务](https://www.jdon.com/dl/best/springevent.html) 异步可能引入的竞争条件

异步
---

启动异步
```java
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
    	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    	executor.setCorePoolSize(10);
    	executor.setMaxPoolSize(20);
    	executor.setQueueCapacity(1000);
    	executor.setKeepAliveSeconds(300);
    	executor.setThreadNamePrefix("dspk-Executor-");
    	// 拒绝策略
    	executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	    executor.initialize();
        return executor;
    }

	/**
	 * 异常处理器
	 */
	@Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
```

- 抛出异常与异常处理

### 异常

事务
---



### 异常

开发原则
---

- 必须能够知道触发Event的对象，及发布源。
- 

总结
---

- 什么时候用异步，什么时候用同步
- 事务

参考
---

- [Spring Events @www.baeldung.com](https://www.baeldung.com/spring-events)
- [Spring事件体系 @blog.csdn.net](https://blog.csdn.net/caihaijiang/article/details/7460888)