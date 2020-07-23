package io.github.donespeak.springsamples.quartz.repository.model;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class CronJob extends QuartzJob {
    private String cronExpression;
    private String timeZone;
}
