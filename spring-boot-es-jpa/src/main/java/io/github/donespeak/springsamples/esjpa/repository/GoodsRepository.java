package io.github.donespeak.springsamples.esjpa.repository;

import io.github.donespeak.springsamples.esjpa.repository.model.GoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author DoneSpeak
 */
public interface GoodsRepository extends ElasticsearchRepository<GoodsInfo, Long> {
}
