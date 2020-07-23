package io.github.donespeak.springsamples.quartz.entity;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class SchedulerInstance {
    private String schedName;
    private String instanceName;
    private long lastCheckinTime;
    private long checkinInterval;
}
