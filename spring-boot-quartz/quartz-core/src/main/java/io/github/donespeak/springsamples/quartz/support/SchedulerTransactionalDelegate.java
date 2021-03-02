package io.github.donespeak.springsamples.quartz.support;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author DoneSpeak
 */
@Slf4j
public class SchedulerTransactionalDelegate {

    @Transactional(rollbackFor = SchedulerException.class)
    public void scheduleJob(@NonNull Scheduler scheduler, @NonNull JobDetail jobDetail, @NonNull Trigger trigger)
        throws SchedulerException {
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void scheduleJob(@NonNull Scheduler scheduler, @NonNull Trigger trigger) {
        try {
            scheduler.scheduleJob(trigger);
        } catch (SchedulerException e) {
            log.error("Fail to schedule job ({}) with trigger ({}): {}", trigger.getJobKey(), trigger.getKey(),
                trigger.getDescription(), e);
        }
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void scheduleJobs(@NonNull Scheduler scheduler, @NonNull Map<JobDetail, Set<? extends Trigger>> triggersAndJobs, boolean replace) throws SchedulerException {
        scheduler.scheduleJobs(triggersAndJobs, replace);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void scheduleJob(@NonNull Scheduler scheduler, @NonNull JobDetail jobDetail, @NonNull Set<? extends Trigger> triggersForJob, boolean replace) throws SchedulerException {
        scheduler.scheduleJob(jobDetail, triggersForJob, replace);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public boolean unscheduleJob(@NonNull Scheduler scheduler, @NonNull TriggerKey triggerKey) throws SchedulerException {
        return scheduler.unscheduleJob(triggerKey);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public boolean unscheduleJobs(@NonNull Scheduler scheduler, @NonNull List<TriggerKey> triggerKeys)
        throws SchedulerException {
        return scheduler.unscheduleJobs(triggerKeys);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public Date rescheduleJob(@NonNull Scheduler scheduler, @NonNull TriggerKey triggerKey, @NonNull Trigger newTrigger)
        throws SchedulerException {
        return scheduler.rescheduleJob(triggerKey, newTrigger);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void addJob(@NonNull Scheduler scheduler, @NonNull JobDetail jobDetail, boolean replace)
        throws SchedulerException {
        scheduler.addJob(jobDetail, replace);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void addJob(@NonNull Scheduler scheduler, @NonNull JobDetail jobDetail, boolean replace, boolean storeNonDurableWhileAwaitingScheduling)
        throws SchedulerException {
        scheduler.addJob(jobDetail, replace, storeNonDurableWhileAwaitingScheduling);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public boolean deleteJob(@NonNull Scheduler scheduler, @NonNull JobKey jobKey) throws SchedulerException {
        return scheduler.deleteJob(jobKey);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public boolean deleteJobs(@NonNull Scheduler scheduler, @NonNull List<JobKey> jobKeys) throws SchedulerException {
        return scheduler.deleteJobs(jobKeys);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void triggerJob(@NonNull Scheduler scheduler, @NonNull JobKey jobKey) throws SchedulerException {
        scheduler.triggerJob(jobKey);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void triggerJob(@NonNull Scheduler scheduler, @NonNull JobKey jobKey, @NonNull JobDataMap data) throws SchedulerException {
        scheduler.triggerJob(jobKey, data);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void pauseJob(@NonNull Scheduler scheduler, @NonNull JobKey jobKey) throws SchedulerException {
        scheduler.pauseJob(jobKey);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void pauseJobs(@NonNull Scheduler scheduler, @NonNull GroupMatcher<JobKey> matcher) throws SchedulerException {
        scheduler.pauseJobs(matcher);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void pauseTrigger(@NonNull Scheduler scheduler, @NonNull TriggerKey triggerKey)
        throws SchedulerException {
        scheduler.pauseTrigger(triggerKey);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void pauseTriggers(@NonNull Scheduler scheduler, @NonNull GroupMatcher<TriggerKey> matcher) throws SchedulerException {
        scheduler.pauseTriggers(matcher);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void resumeJob(@NonNull Scheduler scheduler, @NonNull JobKey jobKey)
        throws SchedulerException {
        scheduler.resumeJob(jobKey);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void resumeJobs(@NonNull Scheduler scheduler, @NonNull GroupMatcher<JobKey> matcher) throws SchedulerException {
        scheduler.resumeJobs(matcher);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void resumeTrigger(@NonNull Scheduler scheduler, @NonNull TriggerKey triggerKey) throws SchedulerException {
        scheduler.resumeTrigger(triggerKey);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void resumeTriggers(@NonNull Scheduler scheduler, @NonNull GroupMatcher<TriggerKey> matcher) throws SchedulerException {
        scheduler.resumeTriggers(matcher);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void pauseAll(@NonNull Scheduler scheduler) throws SchedulerException {
        scheduler.pauseAll();
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void resumeAll(@NonNull Scheduler scheduler) throws SchedulerException {
        scheduler.resumeAll();
    }

    @Transactional(readOnly = true)
    public List<String> getJobGroupNames(@NonNull Scheduler scheduler) throws SchedulerException {
        return scheduler.getJobGroupNames();
    }

    @Transactional(readOnly = true)
    public Set<JobKey> getJobKeys(@NonNull Scheduler scheduler, @NonNull GroupMatcher<JobKey> matcher) throws SchedulerException {
        return scheduler.getJobKeys(matcher);
    }

    @Transactional(readOnly = true)
    public List<? extends Trigger> getTriggersOfJob(@NonNull Scheduler scheduler, @NonNull JobKey jobKey)
        throws SchedulerException {
        return scheduler.getTriggersOfJob(jobKey);
    }

    @Transactional(readOnly = true)
    public List<String> getTriggerGroupNames(@NonNull Scheduler scheduler) throws SchedulerException {
        return scheduler.getTriggerGroupNames();
    }

    @Transactional(readOnly = true)
    public Set<TriggerKey> getTriggerKeys(@NonNull Scheduler scheduler, @NonNull GroupMatcher<TriggerKey> matcher) throws SchedulerException {
        return scheduler.getTriggerKeys(matcher);
    }

    @Transactional(readOnly = true)
    public Set<String> getPausedTriggerGroups(@NonNull Scheduler scheduler) throws SchedulerException {
        return scheduler.getPausedTriggerGroups();
    }

    @Transactional(readOnly = true)
    public JobDetail getJobDetail(@NonNull Scheduler scheduler, @NonNull JobKey jobKey) throws SchedulerException {
        return scheduler.getJobDetail(jobKey);
    }

    @Transactional(readOnly = true)
    public Trigger getTrigger(@NonNull Scheduler scheduler, @NonNull TriggerKey triggerKey)
        throws SchedulerException {
        return scheduler.getTrigger(triggerKey);
    }

    @Transactional(readOnly = true)
    public Trigger.TriggerState getTriggerState(@NonNull Scheduler scheduler, @NonNull TriggerKey triggerKey)
        throws SchedulerException {
        return scheduler.getTriggerState(triggerKey);
    }

    @Transactional(rollbackFor = SchedulerException.class)
    public void resetTriggerFromErrorState(@NonNull Scheduler scheduler, @NonNull TriggerKey triggerKey)
        throws SchedulerException {
        scheduler.resetTriggerFromErrorState(triggerKey);
    }
}
