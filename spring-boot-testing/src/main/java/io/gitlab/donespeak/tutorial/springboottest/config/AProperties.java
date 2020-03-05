package io.gitlab.donespeak.tutorial.springboottest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/02/29 11:45
 */
@Data
@Component
@ConfigurationProperties(prefix = "test")
public class AProperties {

    private String nameName;
}
