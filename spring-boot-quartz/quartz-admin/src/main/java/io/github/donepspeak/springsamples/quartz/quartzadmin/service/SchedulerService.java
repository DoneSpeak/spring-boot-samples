package io.github.donepspeak.springsamples.quartz.quartzadmin.service;

import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model.SchedulerDO;

import java.util.List;

/**
 * @author DoneSpeak
 */
public interface SchedulerService {
    List<SchedulerDO> listAll();
}
