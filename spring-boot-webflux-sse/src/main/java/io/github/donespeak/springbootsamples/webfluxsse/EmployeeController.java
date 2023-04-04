package io.github.donespeak.springbootsamples.webfluxsse;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class EmployeeController {

    @CrossOrigin
    @GetMapping(value = "sse")
    public Flux<ServerSentEvent<String>> sse(){
        return Flux.interval(Duration.ofMillis(1000)).map(val ->{
            return ServerSentEvent.<String>builder()
                    //.id(UUID.randomUUID().toString())
                    .event("ping")
                    .data(val.toString())
                    .build();
        });
    }
}
