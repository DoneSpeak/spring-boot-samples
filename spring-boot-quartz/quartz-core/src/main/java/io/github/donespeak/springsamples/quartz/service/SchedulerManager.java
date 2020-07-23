package io.github.donespeak.springsamples.quartz.service;

import io.github.donespeak.springsamples.quartz.entity.SchedulerInstance;

import java.util.List;
import java.util.Optional;

/**
 * @author DoneSpeak
 */
public interface SchedulerManager {
    List<SchedulerInstance> listAll();

    Optional<SchedulerInstance> get(String schedName, String instanceName);
}
