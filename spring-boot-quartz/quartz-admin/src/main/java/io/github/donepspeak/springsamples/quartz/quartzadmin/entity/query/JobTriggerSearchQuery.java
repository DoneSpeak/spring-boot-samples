package io.github.donepspeak.springsamples.quartz.quartzadmin.entity.query;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class JobTriggerSearchQuery {
    private String schedName;
    private String triggerState;
}
