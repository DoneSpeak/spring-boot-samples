package io.github.donespeak.springsamples.quartz.service.impl;

import io.github.donespeak.springsamples.quartz.job.SendEmailSimpleJob;
import io.github.donespeak.springsamples.quartz.job.SummaryCronJob;
import io.github.donespeak.springsamples.quartz.service.QuartzJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author DoneSpeak
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class QuartzJobServiceImpl implements QuartzJobService {

    private final SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void addSimpleTriggerJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail job = JobBuilder.newJob(SendEmailSimpleJob.class).withIdentity("send_email_simple_job").build();
        job.getJobDataMap().put("email", "donespeak@gr.com");

        // 每隔10秒钟执行一次，共执行5次
        SimpleTrigger trigger = TriggerBuilder.newTrigger().startNow()
            .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(5, 10))
            .build();

        // job和trigger注册到scheduler
        scheduler.scheduleJob(job, trigger);
    }

    @Override
    public void addCronTriggerJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(SummaryCronJob.class).withIdentity("summary_cron_job").build();
        jobDetail.getJobDataMap().put("name", "Bugu");

        // 每个11秒运行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("");

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("summary_trigger", "cron")
            .startNow().withSchedule(cronScheduleBuilder).build();

        // job和trigger注册到scheduler
        scheduler.scheduleJob(jobDetail, trigger);
    }

    // 无法持久化的方式
    public void addJobNonpersistent() {
        // 1. 通过注解定义的方式
    }
}
