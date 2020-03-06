package io.github.donespeak.springbootsamples.ws.client.wsclient;

import io.github.donespeak.springbootsamples.ws.client.config.WsCountryConfiguration;
import io.github.donespeak.springbootsamples.ws.client.config.WsSchemaConst;
import io.github.donespeak.springbootsamples.ws.client.wsdl.GetCountryRequest;
import io.github.donespeak.springbootsamples.ws.client.wsdl.GetCountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * @author Yang Guanrong
 * @date 2020/03/06 17:14
 */
@Slf4j
public class WsCountryClient extends WebServiceGatewaySupport {

    public GetCountryResponse getCountry(String country) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        log.info("Requesting location for " + country);

        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
            .marshalSendAndReceive(WsCountryConfiguration.WS_COUNTRY_CLIENT_URL, request,
                new SoapActionCallback(WsSchemaConst.WS_COUNTRY_SERVER + "/GetCountryRequest"));
        return response;
    }
}
