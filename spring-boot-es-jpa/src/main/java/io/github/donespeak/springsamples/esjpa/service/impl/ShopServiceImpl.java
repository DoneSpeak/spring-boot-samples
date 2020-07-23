package io.github.donespeak.springsamples.esjpa.service.impl;

import java.util.List;

import io.github.donespeak.springsamples.esjpa.repository.model.Shop;
import io.github.donespeak.springsamples.esjpa.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @author DoneSpeak
 */
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Shop> list(int page, int size, String query, String sort) {
        return null;
    }

    @Override
    public Shop get(long id) {
        return null;
    }

    @Override
    public Shop create(Shop goodsInfo) {
        return null;
    }

    @Override
    public Shop update(Shop goodsInfo) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
