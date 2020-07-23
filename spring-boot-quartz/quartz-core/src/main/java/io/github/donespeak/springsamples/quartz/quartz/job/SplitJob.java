package io.github.donespeak.springsamples.quartz.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

/**
 * @author DoneSpeak
 */
@Slf4j
public class SplitJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Processing: " + System.currentTimeMillis());

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        StringBuilder sb = new StringBuilder();
        for(Map.Entry entry: jobDataMap.entrySet()) {
            sb.append("\t" + entry.getKey() + ", " + entry.getValue() + "\n");
        }
        log.info(sb.toString());
    }
}
