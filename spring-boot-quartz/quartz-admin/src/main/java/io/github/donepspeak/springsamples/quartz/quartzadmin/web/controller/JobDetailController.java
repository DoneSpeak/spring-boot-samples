package io.github.donepspeak.springsamples.quartz.quartzadmin.web.controller;

import com.github.pagehelper.PageInfo;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto.JobDetailDto;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto.JobKeyDto;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto.TriggerKeyDto;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model.JobDetailDO;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model.JobTriggerDO;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.query.JobDetailSearchQuery;
import io.github.donepspeak.springsamples.quartz.quartzadmin.service.impl.JobServiceImpl;
import io.github.donepspeak.springsamples.quartz.quartzadmin.service.impl.JobTriggerServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author DoneSpeak
 */
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/schedule-admin/job")
public class JobDetailController {

    private final JobServiceImpl jobDetailService;

    @GetMapping("")
    public PageInfo<JobDetailDO> listJobs(JobDetailSearchQuery query,
        @RequestParam(defaultValue = "10") @Range(min = 1, max = 100) int pageSize,
        @RequestParam(defaultValue = "1") @Min(1) int pageIndex) {
        return jobDetailService.listJobs(query, pageSize, pageIndex);
    }

    @PostMapping("")
    public JobDetailDO addJob(JobDetailDto job) throws Exception {
        return jobDetailService.addJob(job);
    }

    @GetMapping("instance")
    public JobDetailDO getJob(JobKeyDto jobKey) throws SchedulerException {
        return jobDetailService.getJob(jobKey);
    }

    @PutMapping("existence")
    public void deleteJob(JobKeyDto jobKey) throws SchedulerException {
        jobDetailService.deleteJob(jobKey);
    }

    @ApiOperation(value = "获取执行该job的trigger")
    @GetMapping("trigger")
    public List<JobDetailDO> listByTriggers(TriggerKeyDto triggerKey) {
        return jobDetailService.listForTrigger(triggerKey);
    }
}
