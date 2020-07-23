package io.github.donespeak.springsamples.quartz.repository.model;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class QuartzJob {
    private String schedName;
    private String jobGroup;
    private String jobName;
    private String jobClass;
    private String jobStatus;
    private String triggerName;
}
