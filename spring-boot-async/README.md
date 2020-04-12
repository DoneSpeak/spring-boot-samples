Async
===

写在前面
---

### 使用的工具版本


启动异步
---

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

设置异步
---

```java
public class UserService {
    
    @Async
    public void method() {
    
    }

    @Async    
    public Future<Object> method2() {
        
    }
}
```

异常处理
---

异常处理策略

事务
---

- （主线程非异步）主线程和子线程在事务上是相互隔离的，子线程的异步不会影响主线程的事务
- 不同线程之间的事务完全隔离，异步线程内仍是可以调用异步

### 如何在任意一个线程发生异常的时候，所有的线程都回退？

分布式事务

坑
---

- 内存溢出：没有设置队列长度
- 没有设置线程池大小：

可替代方案
--

- 适用的场景
- 什么时候应该使用其他的方案代替，如MQ

实现原理
---

另外的文章中拓展说明。  

- 代理模式
- 委托模式

参考
---

- [异步事务？关于异步@Async + 事务@Transactional的结合使用问题分析【享学Spring MVC】](https://blog.csdn.net/f641385712/article/details/98642777)