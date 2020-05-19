package io.github.donespeak.springsamples.quartz.quartz.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author DoneSpeak
 */
@Slf4j
public class DefaultSchedulerProviderManager implements SchedulerProviderManager, InitializingBean {

    private static final Class<? extends Job> DEFAULT_SUPPORTED_JOB_TYPE = null;

    private final Map<Class<?>, SchedulerProvider> providerRegistry;

    public DefaultSchedulerProviderManager(List<SchedulerProvider> providers) {
        providerRegistry = new HashMap<>();
        for (SchedulerProvider provider : providers) {
            if(providerRegistry.containsKey(provider)) {
                Class jobType = provider.getSupportedJobType();
                SchedulerProvider oldProvider = providerRegistry.get(jobType);
                log.warn("Provider {} will be overwritten by {}, since both of their supported job types are {}.",
                    oldProvider, provider, jobType);
            }
            providerRegistry.put(provider.getSupportedJobType(), provider);
        }
    }

    @Override
    public SchedulerProvider getProvider(Class<? extends Job> jobClass) {
        SchedulerProvider provider = providerRegistry.get(jobClass);
        if (provider == null) {
            provider = providerRegistry.get(DEFAULT_SUPPORTED_JOB_TYPE);
        }
        return provider;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SchedulerProvider provider = providerRegistry.get(DEFAULT_SUPPORTED_JOB_TYPE);
        if (provider == null) {
            throw new AssertionError(
                "There should be a Provider for any type job, whose supported job type is null value.)");
        }
    }
}
