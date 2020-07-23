package io.github.donespeak.springsamples.quartz.repository;

import io.github.donespeak.springsamples.quartz.entity.SchedulerInstance;

import java.util.List;
import java.util.Optional;

/**
 * @author DoneSpeak
 */
public interface SchedulerInstanceRepository {

    List<SchedulerInstance> listAll();

    Optional<SchedulerInstance> get(String schedName, String instanceName);
}
