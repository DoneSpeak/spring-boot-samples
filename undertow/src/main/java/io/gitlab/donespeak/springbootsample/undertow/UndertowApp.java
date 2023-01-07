package io.gitlab.donespeak.springbootsample.undertow;

import io.gitlab.donespeak.springbootsample.undertow.http.EchoEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({
        EchoEndpoint.class,
})
@EnableAutoConfiguration
public class UndertowApp {

    public static void main(String[] args) {
        SpringApplication.run(UndertowApp.class, args);
    }
}
