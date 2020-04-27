package io.github.donespeak.springbootsamples.quartz.repository;

import io.github.donespeak.springbootsamples.quartz.repository.model.ScheduleJob;

import java.util.List;

/**
 * @author Yang Guanrong
 */
public interface ScheduleJobRepository {

    ScheduleJob getById(long id);

    int insert(ScheduleJob scheduleJob);

    int update(ScheduleJob update);

    List<ScheduleJob> listAll();
}
