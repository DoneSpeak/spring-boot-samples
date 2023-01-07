package io.github.donespeak.samples.springbootredis.demo.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("key-value")
public class KeyValueController {

    private final RedisTemplate<String, String> redisTemplate;

    public KeyValueController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PutMapping("")
    public boolean update(@RequestBody KeyValue keyValue) {
        var valueOps = redisTemplate.opsForValue();
        return valueOps.setIfAbsent(keyValue.getKey(), keyValue.getValue(), keyValue.getValidSecond(), TimeUnit.SECONDS);
    }
}
