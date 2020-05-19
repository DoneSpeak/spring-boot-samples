package io.github.donespeak.springsamples.quartz.service.impl;

import io.github.donespeak.springsamples.quartz.quartz.job.EveryTwoSecondJob;
import io.github.donespeak.springsamples.quartz.quartz.job.FailRetryJob;
import io.github.donespeak.springsamples.quartz.quartz.job.ImageJob;
import io.github.donespeak.springsamples.quartz.quartz.job.LongTimeJob;
import io.github.donespeak.springsamples.quartz.quartz.job.SendEmailSimpleJob;
import io.github.donespeak.springsamples.quartz.quartz.job.SummaryCronJob;
import io.github.donespeak.springsamples.quartz.quartz.scheduler.SchedulerProviderManager;
import io.github.donespeak.springsamples.quartz.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DoneSpeak
 */
@Slf4j
// @RequiredArgsConstructor
@Service
public class QuartzJobServiceImpl implements QuartzJobService {

    @Autowired
    private SchedulerProviderManager schedulerProviderManager;

    @Override
    public void addEveryTwoSecondJob() throws SchedulerException {

        JobDetail job = JobBuilder.newJob(EveryTwoSecondJob.class)
            .withIdentity("every_two_second_" + System.currentTimeMillis(), "JOB_GROUP")
            .requestRecovery()
            .build();
        job.getJobDataMap().put("current", System.currentTimeMillis() + "");

        Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity(job.getKey().getName(), "TRIGGER_GROUP")
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(20))
            .startNow()
            .build();

        // job和trigger注册到scheduler

        scheduleJob(job, trigger);
    }

    @Override
    public void addFailRetryJob() throws SchedulerException {
        scheduleJob("fail_retry_job" + System.currentTimeMillis(), FailRetryJob.class);
    }

    @Override
    public void addImageJob() throws SchedulerException {
        scheduleJob("image_job" + System.currentTimeMillis(), ImageJob.class);
    }

    @Override
    public void addLongTime() throws SchedulerException {
        scheduleJob("long_time_job_" + System.currentTimeMillis(), LongTimeJob.class);
    }

    private void scheduleJob(String jobName, Class<? extends Job> jobClass) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(jobClass)
            .withIdentity(jobName, "JOB_GROUP")
            .requestRecovery()
            .build();
        job.getJobDataMap().put("current", System.currentTimeMillis() + "");

        Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity(job.getKey().getName(), "TRIGGER_GROUP")
            .withSchedule(SimpleScheduleBuilder.simpleSchedule())
            .startNow()
            .build();

        // job和trigger注册到scheduler

        scheduleJob(job, trigger);
    }

    private void scheduleJob(JobDetail job, Trigger trigger) throws SchedulerException {
        Scheduler scheduler = schedulerProviderManager.getProvider(job.getJobClass()).getScheduler();
        scheduler.scheduleJob(job, trigger);
    }

    @Override
    public void addSimpleTriggerJob() throws SchedulerException {

        JobDetail job = JobBuilder.newJob(SendEmailSimpleJob.class).withIdentity("send_email_simple_job").build();
        job.getJobDataMap().put("email", "donespeak@gr.com");

        // 每隔10秒钟执行一次，共执行5次
        SimpleTrigger trigger = TriggerBuilder.newTrigger().startNow()
            .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(5, 10))
            .build();

        // job和trigger注册到scheduler
        scheduleJob(job, trigger);
    }

    @Override
    public void addCronTriggerJob() throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(SummaryCronJob.class).withIdentity("summary_cron_job").build();
        jobDetail.getJobDataMap().put("name", "Bugu");

        // 每个11秒运行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("");

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("summary_trigger", "cron")
            .startNow().withSchedule(cronScheduleBuilder).build();

        // job和trigger注册到scheduler
        scheduleJob(jobDetail, trigger);
    }

    // 无法持久化的方式
    public void addJobNonpersistent() {
        // 1. 通过注解定义的方式

    }
}
