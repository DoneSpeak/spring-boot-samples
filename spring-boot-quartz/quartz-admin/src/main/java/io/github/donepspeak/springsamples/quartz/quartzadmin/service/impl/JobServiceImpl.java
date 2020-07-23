package io.github.donepspeak.springsamples.quartz.quartzadmin.service.impl;

import java.util.List;
import java.util.Optional;

import io.github.donespeak.springsamples.quartz.quartz.scheduler.SchedulerProviderManager;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.github.pagehelper.PageInfo;

import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto.JobDetailDto;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto.JobKeyDto;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto.TriggerKeyDto;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model.JobDetailDO;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.query.JobDetailSearchQuery;
import io.github.donepspeak.springsamples.quartz.quartzadmin.service.JobDetailService;
import io.github.donepspeak.springsamples.quartz.quartzadmin.util.JobKeyGenerator;
import lombok.RequiredArgsConstructor;

/**
 * @author DoneSpeak
 */
@RequiredArgsConstructor
public class JobServiceImpl implements JobDetailService {

    private final SchedulerProviderManager schedulerProviderManager;
    private final JobKeyGenerator jobKeyGenerator;

    /**
     * 列表
     */
    public List<JobDetailDO> listJobs() {
        return null;
    }

    /**
     *
     */
    public List<JobDetailDO> listForTrigger(TriggerKeyDto triggerKey) {
        return null;
    }

    /**
     * 增加 TODO 考虑名称和Group是否应该由系统生成，因为这两者构成了唯一主键
     */
    public JobDetailDO addJob(JobDetailDto job)
        throws IllegalAccessException, InstantiationException, ClassNotFoundException, SchedulerException {
        // 通过类名获取实体类，即要执行的定时任务的类
        Class<?> clazz = Class.forName(job.getJobClassName());

        Job jobEntity = (Job)clazz.newInstance();
        // 通过实体类和任务名创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(jobEntity.getClass())
            .withIdentity(jobKeyGenerator.generateJobKey(jobEntity.getClass())).storeDurably().build();

        // 执行定时任务
        Scheduler scheduler = getScheduler(jobDetail.getJobClass());
        try {
            scheduler.addJob(jobDetail, false);
        } catch (ObjectAlreadyExistsException ex) {
            // TODO job 已经存在
        }
        return getJob(
            new JobKeyDto(scheduler.getSchedulerName(), jobDetail.getKey().getGroup(), jobDetail.getKey().getName()));
    }

    public JobDetailDO getJob(JobKeyDto jobKeyDto) {
        return null;
    }

    /**
     * 删除
     */
    public void deleteJob(JobKeyDto job) throws SchedulerException {
        Scheduler scheduler = getScheduler(job.getSchedName());
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        // TODO 删除的效果是什么?
        scheduler.deleteJob(jobKey);
    }

    /**
     * 让Job从暂停状态恢复
     */
    public void resumeJob(JobKeyDto job) throws SchedulerException {
        Scheduler scheduler = getScheduler(job.getSchedName());
        JobKey jobKey = JobKey.jobKey(job.getJobName());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 立即执行 TODO 是否会导致重复执行，应该只会执行一次，且该job必须已经存在于Scheduler中
     */
    public void runAJobNow(JobKeyDto job) throws SchedulerException {
        Scheduler scheduler = getScheduler(job.getSchedName());
        JobKey jobKey = JobKey.jobKey(job.getJobName());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 暂停一个job
     * 
     * @throws SchedulerException
     */
    public void pauseJob(JobKeyDto job) throws SchedulerException {
        Scheduler scheduler = getScheduler(job.getSchedName());
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     *
     * @param schedName
     * @return NonNull
     * @throws SchedulerException
     */
    private Scheduler getScheduler(String schedName) throws SchedulerException {

        Optional<Scheduler> schedulerOptional = schedulerProviderManager.getScheduler(schedName);
        if (!schedulerOptional.isPresent()) {
            // TODO
            throw new IllegalArgumentException();
        }
        return schedulerOptional.orElse(null);
    }

    private Scheduler getScheduler(Class<? extends Job> jobClass) throws SchedulerException {
        return schedulerProviderManager.getScheduler(jobClass);
    }

    @Override
    public PageInfo<JobDetailDO> listJobs(JobDetailSearchQuery query, int pageSize, int pageIndex) {
        return null;
    }
}
