package io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class SimpleTriggerConfig {
    private Integer repeatCount;
    private Long intervalInMillis;
}
