package io.github.donespeak.springbootsamples.ws.server.repository;

import io.github.donespeak.springbootsamples.webservice.entry.country.Country;

/**
 * @author Yang Guanrong
 * @date 2020/03/06 10:04
 */
public interface CountryRepository {
    Country findCountry(String name);
}
