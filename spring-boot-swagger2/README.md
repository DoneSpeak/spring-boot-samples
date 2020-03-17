Spring Boot + Swagger2
===

- [ ] 尚未完成

Quick Start
---

访问：http://localhost:8080/swagger-ui.html

基础知识
---

![常用注解](https://segmentfault.com/img/bVbqXuU)  

- swagger的路径扫描

最佳实践
---


可能的坑
---

### springboot shiro+swagger 权限问题

```java
@Bean
public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = 
            new DefaultAdvisorAutoProxyCreator();
    defaultAdvisorAutoProxyCreator.setUsePrefix(true);
    
    return defaultAdvisorAutoProxyCreator;
}
```

参考：[springboot shiro+swagger 权限问题](https://segmentfault.com/q/1010000019792598)

Swagger2相关技术
---

### 拓展Swagger2

```xml
<dependency>
    <groupId>com.spring4all</groupId>
    <artifactId>swagger-spring-boot-starter</artifactId>
    <version>1.9.0.RELEASE</version>
</dependency>
```

提供了配置文件的配置方法，目前了解到的功能并不需要通过这个starter实现，自己写写就好。

- [ ] 需要进一步了解

### Swagger2 替代方案

评估
---

参考
---

- [关于Swagger2和SpringBoot整合使用](https://segmentfault.com/a/1190000018779378) 写得一般
- [SpringBoot结合swagger2快速生成简单的接口文档](https://segmentfault.com/a/1190000019072857)