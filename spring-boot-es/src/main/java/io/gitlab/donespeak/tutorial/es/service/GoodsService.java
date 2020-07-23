package io.gitlab.donespeak.tutorial.es.service;

import io.gitlab.donespeak.tutorial.es.repository.model.GoodsInfo;

import java.util.List;

/**
 * @author DoneSpeak
 */
public interface GoodsService {

    List<GoodsInfo> list(int page, int size, String query, String sort);

    GoodsInfo get(String id);

    GoodsInfo create(GoodsInfo goodsInfo);

    GoodsInfo update(GoodsInfo goodsInfo);

    void delete(String id);
}
