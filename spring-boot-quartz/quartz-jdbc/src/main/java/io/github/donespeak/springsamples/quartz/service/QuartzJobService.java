package io.github.donespeak.springsamples.quartz.service;

import org.quartz.SchedulerException;

/**
 * @author DoneSpeak
 */
public interface QuartzJobService {
    void addSimpleTriggerJob() throws SchedulerException;

    void addCronTriggerJob() throws SchedulerException;
}
