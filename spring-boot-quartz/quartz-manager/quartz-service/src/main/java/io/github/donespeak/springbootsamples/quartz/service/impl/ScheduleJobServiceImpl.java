package io.github.donespeak.springbootsamples.quartz.service.impl;

import java.util.List;

import io.github.donespeak.springbootsamples.quartz.repository.ScheduleJobRepository;
import io.github.donespeak.springbootsamples.quartz.repository.model.ScheduleJob;
import io.github.donespeak.springbootsamples.quartz.service.QuartzTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yang Guanrong
 */
@RequiredArgsConstructor
@Service
public class ScheduleJobServiceImpl implements QuartzTaskService {

    private final ScheduleJobRepository scheduleJobRepository;

    @Override
    @Transactional(readOnly = true)
    public ScheduleJob getById(long id) {
        return scheduleJobRepository.getById(id);
    }

    @Override
    public ScheduleJob insert(ScheduleJob scheduleJob) {
        // TODO 创建 Quartz Job
        scheduleJobRepository.insert(scheduleJob);
        return scheduleJobRepository.getById(scheduleJob.getId());
    }

    @Override
    public ScheduleJob update(ScheduleJob scheduleJob) {
        // TODO 修改 Quartz Job
        scheduleJobRepository.update(scheduleJob);
        return scheduleJobRepository.getById(scheduleJob.getId());
    }

    @Override
    public int delete(long id) {
        // TODO 删除job
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleJob> listAll() {
        return scheduleJobRepository.listAll();
    }
}
