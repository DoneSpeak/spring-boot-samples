package io.github.donespeak.springbootsamples.web;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class AspectConfig {

    @Around(
        "execution(public *  io.github.donespeak.springbootsamples.web.endpoint.ClientTypeEndpoint.*(..))")
    public Object retryExecute(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Start to run {}...", pjp);
        var result = pjp.proceed();
        log.info("Start to run {}...", pjp);
        return result;
    }
}
