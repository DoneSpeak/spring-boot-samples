package io.github.donespeak.springsamples.quartz.quartz.scheduler;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author DoneSpeak
 */
public class SchedulerFactoryBeanProvider extends SchedulerFactoryBean implements SchedulerProvider {

    private final Class<? extends Job> jobClass;

    public SchedulerFactoryBeanProvider(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
    }

    @Override
    public Class<? extends Job> getSupportedJobType() {
        return jobClass;
    }
}
