package io.github.donespeak.samples.springbootredis.demo.controller;

import lombok.Data;

@Data
public class KeyValue {
    private String key;
    private String value;
    private long validSecond;
}
