package io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class JobTriggerDO {
    private String schedName;
    private String triggerName;
    private String triggerGroup;
    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String description;
    private Long nextFireTime;
    private Long prevFireTime;
    private int priority;
    private String triggerState;
    private String triggerType;
    private long startTime;
    private Long endTime;
    private Integer misfireInsr;
    private String jobData;

    // ==== cron trigger ======
    private String cronExpression;
    private String timeZone;

    // ==== simple trigger ======
    private long repeatCount;
    private long repeatInterval;
    private long timesTriggered;
}
