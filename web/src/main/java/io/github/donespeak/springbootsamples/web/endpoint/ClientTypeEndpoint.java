package io.github.donespeak.springbootsamples.web.endpoint;

import io.github.donespeak.springbootsamples.web.common.ClientType;
import io.github.donespeak.springbootsamples.web.common.ClientTypeMark;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/client")
public class ClientTypeEndpoint {

    @GetMapping("/cur")
    public ClientType getCurClientType(@ClientTypeMark ClientType clientType) {
        return clientType;
    }

    @PostMapping("/cur")
    public void create() {
        log.info("will return void.");
    }
}
