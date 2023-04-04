package io.github.donespeak.springbootsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({
        TimeEchoController.class,
})
@EnableAutoConfiguration
public class FastjsonApp {

    public static void main(String[] args) {
        SpringApplication.run(FastjsonApp.class, args);
    }
}
