package io.github.donespeak.springbootsamples.quartz.repository.model;

import lombok.Data;

/**
 * @author Yang Guanrong
 */
@Data
public class ScheduleJob {
    private Long id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务状态
     */
    private String jobStatus;
    /**
     * 任务执行方法
     */
    private String jobClass;
    /**
     * cron 表达式
     */
    private String cronExpression;
    /**
     * 任务描述
     */
    private String jobDescription;
    /**
     * 时区
     */
    private String timeZoneId;
    private Long startTime;
    private Long endTime;
    /**
     * 状态
     */
    private String state;
}
