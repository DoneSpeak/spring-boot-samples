package io.gitlab.donespeak.springsamplejett.jetty;

import io.gitlab.donespeak.springsamplejett.jetty.http.EchoEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({
        EchoEndpoint.class,
})
@EnableAutoConfiguration
public class JettyApp {

    public static void main(String[] args) {
        SpringApplication.run(JettyApp.class, args);
    }
}
