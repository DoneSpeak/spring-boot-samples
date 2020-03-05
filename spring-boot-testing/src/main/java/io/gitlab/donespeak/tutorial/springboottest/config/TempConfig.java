package io.gitlab.donespeak.tutorial.springboottest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author Yang Guanrong
 * @date 2020/02/29 11:45
 */
@Slf4j
@Configuration
public class TempConfig {

    @Autowired
    private AProperties aProperties;

    @Autowired
    private SystemConfig systemConfig;

    @PostConstruct
    public void test() {
        log.info(aProperties.toString());
        log.info(systemConfig.toString());
    }
}
