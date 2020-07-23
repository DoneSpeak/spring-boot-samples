package io.github.donepspeak.springsamples.quartz.quartzadmin.service.impl;

import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto.JobKeyDto;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto.SimpleTriggerConfig;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto.TriggerKeyDto;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model.JobTriggerDO;
import io.github.donespeak.springsamples.quartz.quartz.scheduler.SchedulerProviderManager;
import lombok.RequiredArgsConstructor;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import java.util.List;
import java.util.Optional;

/**
 * @author DoneSpeak
 */
@RequiredArgsConstructor
public class JobTriggerServiceImpl {

    private final SchedulerProviderManager schedulerProviderManager;

    /**
     * 获取列表
     */
    public List<JobTriggerDO> listTriggers() {
        return null;
    }

    public List<JobTriggerDO> listTriggersOfJob(JobKeyDto jobKey) {
        return null;
    }

    /**
     * 添加
     */
    public JobTriggerDO addJobTrigger(JobTriggerDO job)
        throws SchedulerException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //通过类名获取实体类，即要执行的定时任务的类
        Class<?> clazz = Class.forName(job.getJobClassName());

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
     * 暂停
     */
    public void pause(TriggerKeyDto key) throws SchedulerException {
        // TODO 如果trigger本身已经暂停会怎么样
        Scheduler scheduler = getScheduler(key.getSchedName());
        TriggerKey triggerKey = new TriggerKey(key.getTriggerName(), key.getTriggerGroup());
        scheduler.pauseTrigger(triggerKey);
    }

    /**
     * 启动
     */
    public void resume(TriggerKeyDto key) throws SchedulerException {
        // TODO 如果trigger本身没有暂停会怎么样
        Scheduler scheduler = getScheduler(key.getSchedName());
        TriggerKey triggerKey = new TriggerKey(key.getTriggerName(), key.getTriggerGroup());
        scheduler.resumeTrigger(triggerKey);
    }

    /**
     * 删除
     */
    public void delete(TriggerKeyDto key) throws SchedulerException {
        // TODO 没有从scheduler中找到实现
    }

    /**
     * 修改cron表达式
     */
    public void updateCron(TriggerKeyDto key, String cronExpression) throws SchedulerException {
        Optional<Scheduler> schedulerOptional = schedulerProviderManager.getScheduler(key.getSchedName());
        if(!schedulerOptional.isPresent()) {
            // TODO
            return;
        }
        Scheduler scheduler = schedulerOptional.orElse(null);

        TriggerKey triggerKey = TriggerKey.triggerKey(key.getTriggerName(), key.getTriggerGroup());
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if(!(trigger instanceof CronTrigger)) {
            // TODO
            return;
        }
        CronTrigger cronTrigger = (CronTrigger) trigger;
        // 修改cron表达式
        // TODO 校验 cron
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        trigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 修改simpleInfo的信息表达式
     */
    public void updateSimpleInfo(TriggerKeyDto key, SimpleTriggerConfig simpleTriggerConfig) throws SchedulerException {
        Optional<Scheduler> schedulerOptional = schedulerProviderManager.getScheduler(key.getSchedName());
        if(!schedulerOptional.isPresent()) {
            // TODO
            return;
        }
        Scheduler scheduler = schedulerOptional.orElse(null);

        TriggerKey triggerKey = TriggerKey.triggerKey(key.getTriggerName(), key.getTriggerGroup());
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if(!(trigger instanceof SimpleTrigger)) {
            // TODO
            return;
        }
        SimpleTrigger simpleTrigger = (SimpleTrigger) trigger;

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
            .simpleSchedule()
            .withRepeatCount(simpleTriggerConfig.getRepeatCount())
            .withIntervalInMilliseconds(simpleTriggerConfig.getIntervalInMillis());

        trigger = simpleTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

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
