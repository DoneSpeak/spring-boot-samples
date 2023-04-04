package io.github.donespeak.springbootsamples.webfluxsse;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.Executors;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/flux")
public class SseWebflux {

    @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Flux - " + LocalTime.now().toString());
    }

    @GetMapping("/sse-flux-2")
    public Flux<ServerSentEvent> sseFlux2() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.builder()
                        .id(String.valueOf(sequence))
                        .event("EVENT_TYPE")
                        .data("SSE - " + LocalTime.now().toString())
                        .build());
    }

    @GetMapping("/sse-emitter")
    public SseEmitter sseEmitter() {
        SseEmitter emitter = new SseEmitter();
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                for (int i = 0; true; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .id(String.valueOf(i))
                            .name("SSE_EMITTER_EVENT")
                            .data("SSE EMITTER - " + LocalTime.now().toString());
                    log.info("Send event: {}", event);
                    emitter.send(event);
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }
}
