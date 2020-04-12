Transaction
===

写在前面
---

关键词说明：

- 上下文
- 

基本用法
---

### @Transaction

### 使用切面

Spring 5个隔离级别
---

事务的隔离级别定义的是事务在数据库中**读写方面的控制**，而事务传播行为定义的是**事务控制的范围**。

### 定义

数据库中的隔离级别。

### 使用场景

### 相互影响

Spring 7种事务传播行为
---

事务传播行为用来描述由某一个事务传播行为修饰的方法被嵌套进另一个方法的时事务如何传播。  

事务传播行为是Spring框架独有的事务增强特性，他不属于的事务实际提供方数据库行为。  

| 事务传播行为类型 | 说明 |
| --- | --- |
| PROPAGATION_REQUIRED | 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。 |
| PROPAGATION_SUPPORTS | 支持当前事务，如果当前没有事务，就以非事务方式执行。 |
| PROPAGATION_MANDATORY | 使用当前的事务，如果当前没有事务，就抛出异常。 |
| PROPAGATION_REQUIRES_NEW | 新建事务，如果当前存在事务，把当前事务挂起。 |
| PROPAGATION_NOT_SUPPORTED | 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 |
| PROPAGATION_NEVER | 以非事务方式执行，如果当前存在事务，则抛出异常。 |
| PROPAGATION_NESTED | 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。 |

两个方法分别抛出异常时候的结果。

### 使用场景

### 相互影响

案例
---

先给出结论：

- [ ] 事务方法调用事务方法
- [ ] 事务方法调用非事务方法
- [ ] 非事务方法调用事务方法
- [ ] 非事务方法调用非事务方法...再调用事务方法
- [ ] 事务方法调用非事务方法调用非事务方法
- [ ] 事务方法调用非事务方法...再调用事务方法


- txMethod：有事务的方法
- nonTxMethod：无事务的方法

### 事务方法调用事务方法

### 事务方法调用非事务方法

PROPAGATION_REQUIRED: 两个方法处于同一个事务中

### 非事务方法调用事务方法

PROPAGATION_REQUIRED: 事务方法在事务中，非事务方法不在事务中

以下代码中的两个txMethod方法处于不同的事务中。
```js
nonTxMethod() {
    // do something
    txMethod();
    txMethod();
    // do something
}
```

实现原理
---


### 代理模式

事务失效
--

- 同一个类中 @Async 调用 @Transactional: 需要从代理模式入手解析

简单来说就是：@Transactional 和 @Async 标记的方法 不允许被 ==同一个类中== 的其他方法 ==直接== 调用自己，同一类中直接调用则不会调用动态代理生成的ProxyClass（因为同一类中被调用方法的注解不被解析）

- @Transactional 调用 @Async（失效）（本文就是例子）
- @Transactional 调用 @Transactional（有效，其实是第一个 @Transactional 起的作用，参考事务默认的传播行为）
- @Async 调用 @Transactional（失效）
- @Async 调用 @Async（失效）

待解决
----

- [ ] 事务方法A调用事务方法B，B发生异常，但在A中被捕获，B是否回滚？

参考
----

- [Spring Testing](https://docs.spring.io/spring/docs/5.1.8.RELEASE/spring-framework-reference/testing.html#testing)
- [SpringBoot2 | 第二十四篇（三）：@Async与事务](https://ynfatal.github.io/2018/11/02/SpringBoot2/SpringBoot2%20%20%E7%AC%AC%E4%BA%8C%E5%8D%81%E5%9B%9B%E7%AF%87%EF%BC%88%E4%B8%89%EF%BC%89%EF%BC%9A@Async%E4%B8%8E%E4%BA%8B%E5%8A%A1/) 给出了一些@Asnyc和@Transactional同时使用失败的案例，也给了解决方法。