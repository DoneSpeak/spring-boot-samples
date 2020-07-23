package io.gitlab.donespeak.tutorial.es.repository;

import io.gitlab.donespeak.tutorial.es.repository.model.GoodsInfo;

import java.util.Optional;

/**
 * @author DoneSpeak
 */
public interface GoodsRepository {
    Optional<GoodsInfo> findById(String id);

    void save(GoodsInfo goods);
}
