package io.github.donespeak.springsamples.quartz.service.impl;

import java.util.List;
import java.util.Optional;

import io.github.donespeak.springsamples.quartz.entity.SchedulerInstance;
import io.github.donespeak.springsamples.quartz.repository.SchedulerInstanceRepository;
import io.github.donespeak.springsamples.quartz.service.SchedulerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author DoneSpeak
 */
@RequiredArgsConstructor
// @Service
public class SchedulerManagerImpl implements SchedulerManager {

    private final SchedulerInstanceRepository schedulerInstanceRepository;

    @Override
    public List<SchedulerInstance> listAll() {
        return schedulerInstanceRepository.listAll();
    }

    @Override
    public Optional<SchedulerInstance> get(String schedName, String instanceName) {
        return schedulerInstanceRepository.get(schedName, instanceName);
    }
}
