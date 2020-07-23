package io.gitlab.donespeak.tutorial.es.service.impl;

import io.gitlab.donespeak.tutorial.es.repository.GoodsRepository;
import io.gitlab.donespeak.tutorial.es.repository.model.GoodsInfo;
import io.gitlab.donespeak.tutorial.es.service.GoodsService;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author DoneSpeak
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public List<GoodsInfo> list(int page, int size, String query, String sort) {
        return null;
    }

    @Override
    public GoodsInfo get(String id) {
        return goodsRepository.findById(id).orElse(null);
    }

    @Override
    public GoodsInfo create(GoodsInfo goodsInfo) {
        goodsRepository.save(goodsInfo);
        return get(goodsInfo.getId());
    }

    @Override
    public GoodsInfo update(GoodsInfo goodsInfo) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
