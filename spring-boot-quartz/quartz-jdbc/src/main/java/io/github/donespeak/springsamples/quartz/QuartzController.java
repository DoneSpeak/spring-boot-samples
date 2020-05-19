package io.github.donespeak.springsamples.quartz;

import io.github.donespeak.springsamples.quartz.service.QuartzJobService;
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
@RequestMapping("quartz")
public class QuartzController {

    @Autowired
    private QuartzJobService quartzJobService;

    @GetMapping("load")
    public void loadAll() throws SchedulerException {
        quartzJobService.addSimpleTriggerJob();
    }

    @GetMapping("longtime")
    public void addLongTimeJob() throws SchedulerException {
        quartzJobService.addLongTime();
    }

    @GetMapping("every2second")
    public void addEveryTwoSecondJob() throws SchedulerException {
        quartzJobService.addEveryTwoSecondJob();
    }

    @GetMapping("failretry")
    public void addDatabaseJob() throws SchedulerException {
        quartzJobService.addFailRetryJob();
    }

    @GetMapping("image")
    public void addImageJob() throws SchedulerException {
        quartzJobService.addImageJob();
    }

    @GetMapping("tmp")
    public void temp() throws SchedulerException {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.execute(() -> {
            int a = 1 / 0;
        });
    }
}
