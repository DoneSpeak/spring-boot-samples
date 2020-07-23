package io.github.donespeak.springsamples.esjpa.service;

import java.util.List;

import io.github.donespeak.springsamples.esjpa.repository.model.GoodsInfo;

/**
 * @author DoneSpeak
 */
public interface GoodsService {

    List<GoodsInfo> list(int page, int size, String query, String sort);

    GoodsInfo get(long id);

    GoodsInfo create(GoodsInfo goodsInfo);

    GoodsInfo update(GoodsInfo goodsInfo);

    void delete(long id);
}
