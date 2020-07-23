package io.github.donespeak.springsamples.quartz.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author DoneSpeak
 */
@Slf4j
public class UnschedulerJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String message = String.format("Job (%s): this is error message", context.getJobDetail().getKey());
        Exception ex = new Exception(message);
        log.error(message, ex);
        JobExecutionException e = new JobExecutionException("unschedule all triggers", ex);
        // 结束所有和该job相关的调度器
        e.setUnscheduleAllTriggers(true);
        throw e;
    }
}
