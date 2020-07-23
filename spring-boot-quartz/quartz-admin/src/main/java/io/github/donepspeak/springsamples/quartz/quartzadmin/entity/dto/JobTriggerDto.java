package io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class JobTriggerDto {
    private String jobClassName;
    private String description;
    private String triggerType;

    // ==== cron trigger ======
    private String cronExpression;
    private String timeZone;

    // ==== simple trigger ======
    private long repeatCount;
    private long repeatInterval;
    private long timesTriggered;
}
