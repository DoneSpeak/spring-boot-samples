package io.github.donespeak.springsamples.quartz.repository.model;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class JobTrigger {
    private String schedName;
    private String jobGroup;
    private String jobName;
    private String jobDescription;
    private String triggerName;
    private String triggerGroup;
    private String triggerType;
}
