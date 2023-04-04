package io.github.donespeak.springbootsample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/echo")
public class TimeEchoController {

    @GetMapping("now")
    public DateTimeEcho getDateTime() {
        var datetime = new DateTimeEcho();
        datetime.setNow(LocalDate.now());
        datetime.setYesterday(LocalDate.now().minusDays(1));
        datetime.setTomorrow(LocalDate.now().plusDays(1));
        return datetime;
    }
}
