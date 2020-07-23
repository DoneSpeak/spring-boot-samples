package io.github.donespeak.springsamples.esjpa.service.impl;

import java.util.List;

import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import io.github.donespeak.springsamples.esjpa.repository.GoodsRepository;
import io.github.donespeak.springsamples.esjpa.repository.model.GoodsInfo;
import io.github.donespeak.springsamples.esjpa.service.GoodsService;

/**
 * @author DoneSpeak
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public List<GoodsInfo> list(int page, int size, String query, String sort) {
        SearchQuery searchQuery = getEntitySearchQuery(page, size, query, sort);
        Page<GoodsInfo> goodsPage = goodsRepository.search(searchQuery);
        return goodsPage.getContent();
    }

    private SearchQuery getEntitySearchQuery(int page, int size, String query, String sort) {
        FunctionScoreQueryBuilder queryBuilder = QueryBuilders
            .functionScoreQuery(QueryBuilders.matchPhraseQuery("name", query),
                ScoreFunctionBuilders.weightFactorFunction(100))
            .scoreMode(FunctionScoreQuery.ScoreMode.SUM).setMinScore(10);

        Pageable pageable = PageRequest.of(page, size);
        return new NativeSearchQueryBuilder().withPageable(pageable).withQuery(queryBuilder).build();
    }

    @Override
    public GoodsInfo get(long id) {
        return goodsRepository.findById(id).orElse(null);
    }

    @Override
    public GoodsInfo create(GoodsInfo goodsInfo) {
        return goodsRepository.save(goodsInfo);
    }

    @Override
    public GoodsInfo update(GoodsInfo goodsInfo) {
        return goodsRepository.save(goodsInfo);
    }

    @Override
    public void delete(long id) {
        goodsRepository.deleteById(id);
    }
}
