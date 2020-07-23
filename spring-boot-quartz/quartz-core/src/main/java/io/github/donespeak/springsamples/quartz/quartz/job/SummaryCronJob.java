package io.github.donespeak.springsamples.quartz.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author DoneSpeak
 */
@Slf4j
public class SummaryCronJob extends QuartzJobBean {

    private String name;

    private void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("The name: {}", name);
    }
}
