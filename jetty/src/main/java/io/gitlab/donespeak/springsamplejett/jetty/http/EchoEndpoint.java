package io.gitlab.donespeak.springsamplejett.jetty.http;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("echo")
public class EchoEndpoint {

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String echo() {
        return "Hello!";
    }
}
