package io.github.donespeak.springbootsamples.ws.client.config;

import io.github.donespeak.springbootsamples.ws.client.wsclient.WsCountryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * @author Yang Guanrong
 * @date 2020/03/06 17:23
 */
@Configuration
public class WsCountryConfiguration {

    public static final String WS_COUNTRY_CLIENT_URL = "http://localhost:8080/ws/countries";

    /**
     * The marshaller is pointed at the collection of generated domain objects
     * and will use them to both serialize and deserialize between XML and POJOs.
     */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("io.github.donespeak.springbootsamples.ws.client.wsdl");
        return marshaller;
    }

    @Bean
    public WsCountryClient countryClient(Jaxb2Marshaller marshaller) {
        WsCountryClient client = new WsCountryClient();
        client.setDefaultUri(WS_COUNTRY_CLIENT_URL);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
