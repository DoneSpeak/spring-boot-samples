package io.gitlab.donespeak.tutorial.es.controller;

import io.gitlab.donespeak.tutorial.es.repository.model.GoodsInfo;
import io.gitlab.donespeak.tutorial.es.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author DoneSpeak
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping
    public List<GoodsInfo> list(int page, int size, String query, String sort) {
        return goodsService.list(page, size, query, sort);
    }

    @GetMapping("{id:\\d+}")
    public GoodsInfo get(@PathVariable String id) {
        return goodsService.get(id);
    }

    @PostMapping
    public GoodsInfo create(@RequestBody GoodsInfo goodsInfo) {
        return goodsService.create(goodsInfo);
    }

    @PutMapping("/{id:\\d+}")
    public GoodsInfo update(@PathVariable String id, @RequestBody GoodsInfo goodsInfo) {
        goodsInfo.setId(id);
        return goodsService.update(goodsInfo);
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        goodsService.delete(id);
    }
}
