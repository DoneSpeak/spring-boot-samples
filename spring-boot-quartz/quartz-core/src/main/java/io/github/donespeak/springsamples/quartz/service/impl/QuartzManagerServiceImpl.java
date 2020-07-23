package io.github.donespeak.springsamples.quartz.service.impl;

import io.github.donespeak.springsamples.quartz.quartz.scheduler.SchedulerProviderManager;
import io.github.donespeak.springsamples.quartz.service.QuartzManagerService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.spi.JobStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author DoneSpeak
 */
@Service
public class QuartzManagerServiceImpl implements QuartzManagerService {

    @Autowired
    private SchedulerProviderManager schedulerProviderManager;

    public List<Object> listJob(String schedulerName) throws SchedulerException {
        Optional<Scheduler> schedulerOptional = schedulerProviderManager.getScheduler(schedulerName);
        if(!schedulerOptional.isPresent()) {
            return Collections.emptyList();
        }
        Scheduler scheduler = schedulerOptional.get();
        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
        List<Trigger> triggers = new ArrayList<>();
        for(TriggerKey key: triggerKeys) {
            Trigger trigger = scheduler.getTrigger(key);
            triggers.add(trigger);
        }
        return null;
    }
}
