package io.github.donepspeak.springsamples.quartz.quartzadmin.repository;

import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model.JobDetailDO;

import java.util.List;

/**
 * @author DoneSpeak
 */
public interface JobDetailRepository {
    List<JobDetailDO> list();
}
