package io.github.donespeak.springbootsamples.ws.client.controller;

import io.github.donespeak.springbootsamples.ws.client.wsclient.WsCountryClient;
import io.github.donespeak.springbootsamples.ws.client.wsdl.country.Country;
import io.github.donespeak.springbootsamples.ws.client.wsdl.country.GetCountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author Yang Guanrong
 * @date 2020/03/06 15:35
 */
@Slf4j
@RestController
@RequestMapping("/ws/countries")
public class CountryController {

    @Autowired
    private WsCountryClient countryClient;

    @GetMapping("")
    public Country findCountry(String name) {
        GetCountryResponse response = countryClient.getCountry(name);
        return response.getCountry();
    }

    @PostConstruct
    public void print() {
        log.info("This is " + getClass());
    }
}
