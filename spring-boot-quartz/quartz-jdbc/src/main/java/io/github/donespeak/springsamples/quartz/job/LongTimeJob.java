package io.github.donespeak.springsamples.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author DoneSpeak
 */
@Slf4j
public class LongTimeJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("LongTimeJob start to run...");
        try {
            int times = 60;
            while((times --) > 0) {
                log.info("Times: " + times);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("LongTimeJob finished.");
    }
}
