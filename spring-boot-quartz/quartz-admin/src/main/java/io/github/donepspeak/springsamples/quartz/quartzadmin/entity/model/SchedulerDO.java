package io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class SchedulerDO {
    private String schedName;
    private String instanceName;
    private long lastCheckinTime;
    private long checkinInterval;
}
