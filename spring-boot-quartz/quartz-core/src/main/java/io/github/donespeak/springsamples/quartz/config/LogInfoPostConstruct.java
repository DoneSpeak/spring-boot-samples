package io.github.donespeak.springsamples.quartz.config;

import io.github.donespeak.springsamples.quartz.quartz.scheduler.SchedulerFactoryBeanProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author DoneSpeak
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class LogInfoPostConstruct {

    private final List<SchedulerFactoryBean> schedulerFactoryBeanList;

    private final List<SchedulerFactoryBeanProvider> providers;

    private final Scheduler scheduler;

    @PostConstruct
    public void post() {
        try {
            log.info(scheduler.getSchedulerName());
            showSchedulers();
            showProvider();
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void showSchedulers() throws SchedulerException {
        for (SchedulerFactoryBean bean : schedulerFactoryBeanList) {
            log.info("scheduler: {}", bean.getScheduler().getSchedulerName());
        }
    }

    private void showProvider() throws SchedulerException {
        for(SchedulerFactoryBeanProvider provider: providers) {
            log.info("provider: {}", provider.getScheduler().getSchedulerName());
        }
    }
}
