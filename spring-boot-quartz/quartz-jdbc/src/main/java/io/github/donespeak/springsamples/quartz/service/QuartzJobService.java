package io.github.donespeak.springsamples.quartz.service;

import org.quartz.SchedulerException;

/**
 * @author DoneSpeak
 */
public interface QuartzJobService {
    void addEveryTwoSecondJob() throws SchedulerException;

    void addFailRetryJob() throws SchedulerException;

    void addImageJob() throws SchedulerException;

    void addLongTime() throws SchedulerException;

    void addSimpleTriggerJob() throws SchedulerException;

    void addCronTriggerJob() throws SchedulerException;
}
