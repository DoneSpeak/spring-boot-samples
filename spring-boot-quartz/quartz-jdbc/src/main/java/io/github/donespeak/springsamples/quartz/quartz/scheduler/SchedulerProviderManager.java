package io.github.donespeak.springsamples.quartz.quartz.scheduler;

import org.quartz.Job;

/**
 * @author DoneSpeak
 */
public interface SchedulerProviderManager {
    SchedulerProvider getProvider(Class<? extends Job> jobClass);
}
