package io.github.donespeak.pringbootsamples.event.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.util.function.Function;

@Log4j2
@Import({
        ValuesProperties.class,
})
@Configuration
public class ValuesConfig {

    @Bean
    Function<String, String> converter(ValuesProperties valuesProperties) {
        log.info(valuesProperties);
        Function<String, String> result = (key) -> {
            return valuesProperties.getMapping().get(key);
        };
        System.out.println(result.apply("H.*"));
        return result;
    }
}
