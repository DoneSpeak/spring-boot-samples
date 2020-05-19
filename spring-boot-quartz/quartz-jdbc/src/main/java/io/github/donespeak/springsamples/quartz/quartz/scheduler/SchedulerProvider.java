package io.github.donespeak.springsamples.quartz.quartz.scheduler;

import org.quartz.Job;
import org.quartz.Scheduler;

/**
 * @author DoneSpeak
 */
public interface SchedulerProvider {

    Class<? extends Job> getSupportedJobType();

    Scheduler getScheduler();
}
