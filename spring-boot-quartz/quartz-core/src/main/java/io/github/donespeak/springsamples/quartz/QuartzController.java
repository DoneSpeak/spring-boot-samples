package io.github.donespeak.springsamples.quartz;

import io.github.donespeak.springsamples.quartz.quartz.scheduler.SchedulerProviderManager;
import io.github.donespeak.springsamples.quartz.service.TestQuartzJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DoneSpeak
 */
@RestController
@RequestMapping("quartz-admin")
public class QuartzController {

    @Autowired
    private TestQuartzJobService testQuartzJobService;

    @Autowired
    private SchedulerProviderManager schedulerProviderManager;

    @GetMapping("load")
    public void loadAll() throws SchedulerException {
        testQuartzJobService.addSimpleTriggerJob();
    }

    @GetMapping("longtime")
    public void addLongTimeJob() throws SchedulerException {
        testQuartzJobService.addLongTime();
    }

    @GetMapping("every2second")
    public void addEveryTwoSecondJob() throws SchedulerException {
        testQuartzJobService.addEveryTwoSecondJob();
    }

    @GetMapping("failretry")
    public void addDatabaseJob() throws SchedulerException {
        testQuartzJobService.addFailRetryJob();
    }

    @GetMapping("image")
    public void addImageJob() throws SchedulerException {
        testQuartzJobService.addImageJob();
    }

    @GetMapping("tmp")
    public void temp() throws SchedulerException {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.execute(() -> {
            int a = 1 / 0;
        });
    }

    @GetMapping("unsch")
    public void unsch() throws SchedulerException {
        testQuartzJobService.addUnschJob();
    }

    @GetMapping("spilt")
    public void addJobThenTrigger(boolean startNow) throws SchedulerException {
        testQuartzJobService.addJobThenTrigger(startNow);
    }
}
