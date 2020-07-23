package io.github.donepspeak.springsamples.quartz.quartzadmin.repository;

import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model.SchedulerDO;

import java.util.List;
import java.util.Optional;

/**
 * @author DoneSpeak
 */
public interface SchedulerRepository {

    List<SchedulerDO> listAll();

    Optional<SchedulerDO> get(String schedName, String instanceName);
}
