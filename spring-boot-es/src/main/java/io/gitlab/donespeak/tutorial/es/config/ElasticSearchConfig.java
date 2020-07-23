package io.gitlab.donespeak.tutorial.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;

/**
 * @author DoneSpeak
 */
public class ElasticSearchConfig {

    @Bean(name = "restHighLevelClient", destroyMethod = "close")
    public RestHighLevelClient highLevelClient() {
        HttpHost[] hosts = new HttpHost[] {new HttpHost("127.0.0.1", 9300)};
        RestClientBuilder builder = RestClient.builder(hosts);
        return new RestHighLevelClient(builder);
    }

}
