package io.github.donespeak.springsamples.quartz.manager;

import io.github.donespeak.springsamples.quartz.quartz.scheduler.SchedulerProviderManager;
import io.github.donespeak.springsamples.quartz.repository.model.CronJob;
import io.github.donespeak.springsamples.quartz.repository.model.QuartzJob;
import lombok.RequiredArgsConstructor;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author DoneSpeak
 */
@Service
@RequiredArgsConstructor
public class QuartzCronJobManager {

    private final SchedulerProviderManager schedulerProviderManager;

    /**
     * 增加
     */
    public CronJob addJob(CronJob job)
        throws IllegalAccessException, InstantiationException, ClassNotFoundException, SchedulerException {
        //通过类名获取实体类，即要执行的定时任务的类
        Class<?> clazz = Class.forName(job.getJobClass());

        Job jobEntity = (Job)clazz.newInstance();
        //通过实体类和任务名创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(jobEntity.getClass())
            .withIdentity(job.getJobName()).build();
        //通过触发器名和cron 表达式创建 Trigger
        Trigger cronTrigger = TriggerBuilder.newTrigger()
            .withIdentity(job.getTriggerName())
            .startNow()
            .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
            .build();

        //执行定时任务
        Scheduler scheduler = schedulerProviderManager.getScheduler(jobDetail.getJobClass());
        try {
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (ObjectAlreadyExistsException ex) {
            // job 已经存在
        }

        return job;
    }

    /**
     * 删除
     */
    public void deleteJob(CronJob job) throws SchedulerException {
        Scheduler scheduler = getScheduler(job.getSchedName());
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        // TODO 删除的效果是什么?
        scheduler.deleteJob(jobKey);
    }

    /**
     * 让Job从暂停状态恢复
     */
    public void resumeJob(CronJob job) throws SchedulerException {
        Scheduler scheduler = getScheduler(job.getSchedName());
        JobKey jobKey = JobKey.jobKey(job.getJobName());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 立即执行
     * TODO 是否会导致重复执行，应该只会执行一次，且该job必须已经存在于Scheduler中
     */
    public void runAJobNow(QuartzJob job) throws SchedulerException {
        Scheduler scheduler = getScheduler(job.getSchedName());
        JobKey jobKey = JobKey.jobKey(job.getJobName());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 暂停一个job
     * @throws SchedulerException
     */
    public void pauseJob(QuartzJob job) throws SchedulerException {
        Scheduler scheduler = getScheduler(job.getSchedName());
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 修改cron表达式
     */
    public void updateJobCron(CronJob job) throws SchedulerException {

        Optional<Scheduler> schedulerOptional = schedulerProviderManager.getScheduler(job.getSchedName());
        if(!schedulerOptional.isPresent()) {
            // TODO
            return;
        }
        Scheduler scheduler = schedulerOptional.orElse(null);

        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 修改cron表达式
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     *
     * @param schedName
     * @return NonNull
     * @throws SchedulerException
     */
    private Scheduler getScheduler(String schedName) throws SchedulerException {

        Optional<Scheduler> schedulerOptional = schedulerProviderManager.getScheduler(schedName);
        if(!schedulerOptional.isPresent()) {
            // TODO
            throw new IllegalArgumentException();
        }
        return schedulerOptional.orElse(null);
    }

    private Scheduler getScheduler(Class<? extends Job> jobClass) throws SchedulerException {
        return schedulerProviderManager.getScheduler(jobClass);
    }
}
