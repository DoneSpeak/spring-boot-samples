package io.github.donespeak.springsamples.quartz.quartz.job;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author DoneSpeak
 */
@Slf4j
public class LongTimeJob extends QuartzJobBean {

    @Setter
    private String current;

    @Setter
    private String name;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("LongTimeJob start to run...");
        log.info("current:{}", current);
        log.info("name:{}", name);
        current = "@recover:" + current;
        name = "@name:" + name;
        context.put("night", "Good");
        try {
            int times = 2;
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
