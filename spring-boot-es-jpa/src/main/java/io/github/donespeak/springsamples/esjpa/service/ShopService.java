package io.github.donespeak.springsamples.esjpa.service;

import io.github.donespeak.springsamples.esjpa.repository.model.Shop;

import java.util.List;

/**
 * @author DoneSpeak
 */
public interface ShopService {

    List<Shop> list(int page, int size, String query, String sort);

    Shop get(long id);

    Shop create(Shop goodsInfo);

    Shop update(Shop goodsInfo);

    void delete(long id);
}
