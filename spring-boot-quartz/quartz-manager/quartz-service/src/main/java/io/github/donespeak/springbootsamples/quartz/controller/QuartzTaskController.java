package io.github.donespeak.springbootsamples.quartz.controller;

import java.util.List;

import io.github.donespeak.springbootsamples.quartz.core.exception.ResourceNotFoundException;
import io.github.donespeak.springbootsamples.quartz.repository.model.ScheduleJob;
import io.github.donespeak.springbootsamples.quartz.service.QuartzTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang Guanrong
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("schedule-job")
public class QuartzTaskController {

    private final QuartzTaskService quartzTaskService;

    @GetMapping("/{id:\\d+}")
    public ScheduleJob getById(long id) {
        ScheduleJob job = quartzTaskService.getById(id);
        if(job == null) {
            String message = String.format("Job %s not found.", id);
            throw new ResourceNotFoundException(message);
        }
        return job;
    }

    @PostMapping("")
    public ScheduleJob insert(@RequestBody ScheduleJob scheduleJob) {
        ScheduleJob job = quartzTaskService.insert(scheduleJob);
        return job;
    }

    @PutMapping("/{id:\\d+}")
    public int update(@RequestBody ScheduleJob update) {
        return 0;
    }

    @DeleteMapping("/{id:\\d+}")
    public int delete(@PathVariable long id) {
        return 0;
    }

    @GetMapping("")
    public List<ScheduleJob> listAll() {
        return null;
    }
}
