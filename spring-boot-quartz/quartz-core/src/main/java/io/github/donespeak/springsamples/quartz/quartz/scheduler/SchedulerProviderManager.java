package io.github.donespeak.springsamples.quartz.quartz.scheduler;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import java.util.Optional;

/**
 * @author DoneSpeak
 */
public interface SchedulerProviderManager {
    SchedulerProvider getProvider(Class<? extends Job> jobClass);

    Optional<SchedulerProvider> getProvider(String schedulerName) throws SchedulerException;

    Scheduler getScheduler(Class<? extends Job> jobClass);

    Optional<Scheduler> getScheduler(String schedulerName) throws SchedulerException;
}
