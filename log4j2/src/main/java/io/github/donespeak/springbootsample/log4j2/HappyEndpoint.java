package io.github.donespeak.springbootsample.log4j2;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("happy")
public class HappyEndpoint {

    @GetMapping("")
    public Happy happy() {
        return new Happy();
    }

    @GetMapping("ex")
    public Happy ex() {
        log.error("This is a happy exception: {}", () -> "not happy", () -> new Exception());
        return new Happy();
    }
}
