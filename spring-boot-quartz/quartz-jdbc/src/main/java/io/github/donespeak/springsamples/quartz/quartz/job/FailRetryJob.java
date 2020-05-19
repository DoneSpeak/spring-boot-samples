package io.github.donespeak.springsamples.quartz.quartz.job;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author DoneSpeak
 */
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class FailRetryJob extends QuartzJobBean {

    private final int PROCESS_TIMES_MAX = 10;

    @Setter
    private int processTimes = 0;

    @Setter
    private String lastTime;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("start to run, lastTime is " + lastTime);
        log.info("processTimes: " + processTimes);
        processTimes ++;
        lastTime = new Date().toString();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        context.getJobDetail().getJobDataMap().put("processTimes", processTimes + "");
        context.getJobDetail().getJobDataMap().put("processTimes2", processTimes + "");
        if(processTimes < PROCESS_TIMES_MAX) {
            throw new JobExecutionException("Retry: " + processTimes + "/" + PROCESS_TIMES_MAX, true);
        } else {
            // throw new RuntimeException("Exceeds all retries.");
            JobExecutionException e = new JobExecutionException("Finished");
            // 结束所有和该job相关的调度器
            e.setUnscheduleAllTriggers(true);
            throw e;
        }
    }
}
