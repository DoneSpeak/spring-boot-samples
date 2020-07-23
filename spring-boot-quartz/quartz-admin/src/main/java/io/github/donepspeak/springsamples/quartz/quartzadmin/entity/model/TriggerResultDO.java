package io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class TriggerResultDO {
    private String schedName;
    private String triggerName;
    private String triggerGroup;
    private String resultType;
    private String detail;
}
