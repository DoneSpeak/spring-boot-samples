package io.github.donespeak.springsamples.quartz.quartz.job;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author DoneSpeak
 */
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class EveryTwoSecondJob extends QuartzJobBean {

    @Setter
    private int times = 0;

    private Story story = new Story();

    @Setter
    private byte[] aPic;

    public void setStory(Story story) {
        this.story = story;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        times ++;
        log.info("Times: " + times);

        story.setMessage("The " + times + " to miss you.");
        story.setPictrue(Integer.toBinaryString(times).getBytes());
        aPic = Integer.toBinaryString(times).getBytes();

        JobDataMap jdm = context.getJobDetail().getJobDataMap();
        jdm.put("times", times + "");
        jdm.put("story", story);
        // byte[] 这里不能序列化
        jdm.put("aPic", aPic);
    }
}
