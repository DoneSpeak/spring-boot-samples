package io.github.donespeak.springbootsamples.ws.client;

import io.github.donespeak.springbootsamples.ws.client.wsdl.country.Country;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Yang Guanrong
 * @date 2020/03/06 17:37
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void findCountry() {
        Map<String, Object> params = new HashMap<>();
        ResponseEntity<Country> response = restTemplate.getForEntity("/ws/countries", Country.class, params);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        log.info("Result Response Country: " + response.getBody());
    }
}
