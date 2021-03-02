package io.github.donespeak.springbootsamples.intercept.aspect;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class WebLog {
    private String basePath;
    private String url;
    private String uri;
    private String method;
    private String ip;
    private Long startTime;
    private Long endTime;
    private Integer spendTime;
    private String status;
    private Object parameter;
}
