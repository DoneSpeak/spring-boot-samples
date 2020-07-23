package io.github.donepspeak.springsamples.quartz.quartzadmin.repository;

import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model.JobTriggerDO;

import java.util.List;

/**
 * @author DoneSpeak
 */
public interface JobTriggerRepository {
    List<JobTriggerDO> list();
}
