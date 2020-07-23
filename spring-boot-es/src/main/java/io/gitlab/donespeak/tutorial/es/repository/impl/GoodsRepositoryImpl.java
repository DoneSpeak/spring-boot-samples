package io.gitlab.donespeak.tutorial.es.repository.impl;

import java.io.IOException;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import io.gitlab.donespeak.tutorial.es.repository.GoodsRepository;
import io.gitlab.donespeak.tutorial.es.repository.model.GoodsInfo;

/**
 * @author DoneSpeak
 */
@Slf4j
@Repository
public class GoodsRepositoryImpl implements GoodsRepository {

    @Autowired
    private RestHighLevelClient restClient;

    private static String INDEX_GOODS = "goods";

    private Gson gson = new Gson();

    @Override
    public Optional<GoodsInfo> findById(String id) {
        GetRequest getRequest = new GetRequest(INDEX_GOODS, id);
        try {
            GetResponse getResponse = restClient.get(getRequest, RequestOptions.DEFAULT);
            if (getResponse.isExists()) {
                String json = getResponse.getSourceAsString();
                return Optional.ofNullable(toGoodInfo(json));
            }
            return Optional.empty();
        } catch (IOException e) {
            // 已经尽力去尝试了，可是还是失败了，此时已无法再进一步处理
            throw ofException(e);
        }
    }

    @Override
    public void save(GoodsInfo goods) {
        IndexRequest indexRequest = new IndexRequest(INDEX_GOODS);
        indexRequest.source(gson.toJson(goods), XContentType.JSON).id(goods.getId());
        try {
            restClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw ofException(e);
        }
    }

    private GoodsInfo toGoodInfo(String json) {
        return gson.fromJson(json, GoodsInfo.class);
    }

    private RuntimeException ofException(Exception ex) {
        return new RuntimeException(ex.getMessage(), ex);
    }
}