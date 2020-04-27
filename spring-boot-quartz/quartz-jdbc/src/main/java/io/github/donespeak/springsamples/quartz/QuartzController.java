package io.github.donespeak.springsamples.quartz;

import io.github.donespeak.springsamples.quartz.service.QuartzJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("load")
    public void loadAll() throws SchedulerException {
        quartzJobService.addSimpleTriggerJob();
    }
}
