package io.gitlab.donespeak.tutorial.es.controller;

import com.google.gson.Gson;
import io.gitlab.donespeak.tutorial.es.repository.model.GoodsInfo;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.engine.DocumentMissingException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author DoneSpeak
 */
@RestController
@RequestMapping("/es")
public class RestClientController {

    @Autowired
    private RestHighLevelClient restClient;

    private Gson gson = new Gson();

    private static final String DEFAULT_INDEX = "es_test";

    @GetMapping("index")
    public Object index(String index, String id) throws IOException {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setId(id);
        goodsInfo.setName("book_" + id);
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index(index).id(id).source(gson.toJson(goodsInfo), XContentType.JSON);
        IndexResponse indexResponse = restClient.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse;
    }

    @GetMapping("/get")
    public Object getById(String index, String id) throws IOException {
        GetRequest getRequest = new GetRequest(index, id);
        GetResponse getResponse = restClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());
        return getResponse;
    }

    @GetMapping("/search")
    public Object search(String index, String name) throws IOException {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.fuzzyQuery("name", name));
        SearchRequest projectRequest = new SearchRequest(index);
        projectRequest.source(new SearchSourceBuilder().query(boolQuery));
        SearchResponse search = restClient.search(projectRequest, RequestOptions.DEFAULT);
        return search;
    }

    @GetMapping("/update")
    public Object update(String index, String id, String name, @RequestParam(required = false, defaultValue = "false") boolean upsert) throws IOException {

        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setId(id);
        goodsInfo.setName(name);
        String doc = gson.toJson(goodsInfo);

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index);
        updateRequest.id(id);
        updateRequest.doc(doc, XContentType.JSON);
        if(upsert) {
            // 如果不存在，则自动创建index
            updateRequest.upsert(doc, XContentType.JSON);
        }
        try {
            UpdateResponse updateResponse = restClient.update(updateRequest, RequestOptions.DEFAULT);
            return updateResponse;
        } catch (DocumentMissingException e) {
            return e;
        }
    }

    @GetMapping("/delete")
    public Object update(String index, String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index, id);
        DeleteResponse response = restClient.delete(deleteRequest, RequestOptions.DEFAULT);
        return response;
    }
}
