package io.github.donespeak.springbootsample.springbootasync;

import javax.annotation.PostConstruct;

import io.github.donespeak.springbootsample.springbootasync.entity.Human;
import io.github.donespeak.springbootsample.springbootasync.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author DoneSpeak
 */
@Slf4j
@Component
public class ApplicationContextTry implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Value("${spring.mail.subtitle-prefix}")
    private String subtitlePrefix;

    @PostConstruct
    public void test() {
        log.info(subtitlePrefix);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
