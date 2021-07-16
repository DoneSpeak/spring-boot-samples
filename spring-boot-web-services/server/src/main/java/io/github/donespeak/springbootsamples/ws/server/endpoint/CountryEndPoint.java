package io.github.donespeak.springbootsamples.ws.server.endpoint;

import io.github.donespeak.springbootsamples.ws.server.core.SchemaConst;
import io.github.donespeak.springbootsamples.ws.server.repository.CountryRepository;
import io.github.donespeak.springbootsamples.webservice.entry.country.Country;
import io.github.donespeak.springbootsamples.webservice.entry.country.GetCountryRequest;
import io.github.donespeak.springbootsamples.webservice.entry.country.GetCountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Yang Guanrong
 * @date 2020/03/06 10:06
 */
@Endpoint
public class CountryEndPoint {
    private CountryRepository countryRepository;

    public CountryEndPoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = SchemaConst.NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
