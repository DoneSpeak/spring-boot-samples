package io.github.donespeak.springbootsamples.web.endpoint;

import io.github.donespeak.springbootsamples.web.common.ClientType;
import io.github.donespeak.springbootsamples.web.common.ClientTypeMark;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientTypeEndpoint {

    @GetMapping("/cur")
    public ClientType getCurClientType(@ClientTypeMark ClientType clientType) {
        return clientType;
    }
}
