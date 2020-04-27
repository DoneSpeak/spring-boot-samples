package io.github.donespeak.springbootsamples.quartz.service;

import io.github.donespeak.springbootsamples.quartz.repository.model.ScheduleJob;

import java.util.List;

/**
 * @author Yang Guanrong
 */
public interface QuartzTaskService {

    ScheduleJob getById(long id);

    ScheduleJob insert(ScheduleJob scheduleJob);

    ScheduleJob update(ScheduleJob update);

    int delete(long id);

    List<ScheduleJob> listAll();
}
