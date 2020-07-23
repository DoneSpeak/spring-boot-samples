package io.github.donespeak.springsamples.quartz.service;

import org.quartz.SchedulerException;

/**
 * @author DoneSpeak
 */
public interface TestQuartzJobService {
    void addEveryTwoSecondJob() throws SchedulerException;

    void addFailRetryJob() throws SchedulerException;

    void addImageJob() throws SchedulerException;

    void addLongTime() throws SchedulerException;

    void addSimpleTriggerJob() throws SchedulerException;

    void addCronTriggerJob() throws SchedulerException;

    void addUnschJob() throws SchedulerException;

    void addJobThenTrigger(boolean startNow) throws SchedulerException;
}
