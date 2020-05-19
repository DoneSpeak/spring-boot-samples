package io.github.donespeak.springsamples.quartz.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author DoneSpeak
 */
@Slf4j
public class ImageJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("start to run...");
        try {
            int times = 60;
            while((times --) > 0) {
                log.info("Process image. Times: " + times);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("finished.");
    }
}
