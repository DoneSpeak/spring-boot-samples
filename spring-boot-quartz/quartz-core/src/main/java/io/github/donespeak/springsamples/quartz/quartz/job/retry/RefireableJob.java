package io.github.donespeak.springsamples.quartz.quartz.job.retry;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 失败则重试Job。这里的重试方式为同步重新调用QuartzJobBean#executeInternal方法，而非重新提交一个Job到Scheduler中。
 * @author DoneSpeak
 */
@Slf4j
public abstract class RefireableJob extends QuartzJobBean {

    @Setter
    protected int retry = 0;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        boolean successful = true;
        Exception lastEx = null;
        try {
            tryExecute(context);
        } catch (Exception e) {
            successful = false;
            lastEx = e;
            if(!stopRetry(e)) {
                tryRefiredAfterCountDown(context, e);
            }
        } finally {
            updateRetryTimes(context);
        }
        try {
            afterFinalExecute(successful, lastEx);
        } finally {
            unscheduleAllTriggers(successful, lastEx);
        }
    }

    private void updateRetryTimes(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        jobDataMap.put("retry", retry);
    }

    /**
     * 尝试执行，失败则抛出异常
     *
     * @param context
     * @throws Exception 抛出异常表示执行失败
     */
    protected abstract void tryExecute(JobExecutionContext context) throws Exception;

    /**
     * 提供子类一个方法，可以在成功执行或者全部重试均失败之后可以做一些处理，然后再终止所有与该job相关的触发器
     *
     * @param successful 执行结果
     * @param lastEx 最后一次导致失败的异常，当 {@code successful} 为true时，ex为null。
     */
    protected void afterFinalExecute(boolean successful, Exception lastEx) {}

    /**
     * 用于在发生提前结束重试，比如发生某种无法通过重试可以解决的异常的时候。
     */
    protected boolean stopRetry(Exception e) {
        if(e instanceof StopRefiredableJobException) {
            return true;
        }
        List<Class<? extends Throwable>> causes = getCausesToStopRetry();
        if(causes == null || causes.isEmpty()) {
            return false;
        }
        for(Class<? extends Throwable> cause: causes) {
            if(cause.isAssignableFrom(e.getClass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果执行过程中抛出如下的异常，则停止重试
     */
    protected List<Class<? extends Throwable>> getCausesToStopRetry() {
        return new ArrayList<>();
    }

    private void unscheduleAllTriggers(boolean successful, Exception lastEx) throws JobExecutionException {
        String message = "";
        if(successful) {
            message = "Unschedule all triggers of this job since the job is completed.";
        } else {
            message = "Unschedule all triggers of this job since the job exceeds the retry times.";
        }
        JobExecutionException e = new JobExecutionException(message, lastEx);
        // 结束所有和该job相关的调度器
        e.setUnscheduleAllTriggers(true);
        throw e;
    }

    private void tryRefiredAfterCountDown(JobExecutionContext context, Exception e) throws JobExecutionException {
        retry --;

        JobKey jobKey = context.getJobDetail().getKey();
        log.debug("JobDetail({}, {}) count down and left {}", jobKey.getName(), jobKey.getGroup(), retry);
        if(retry > 0) {
            String message = String.format("Refire the job(%s, %s) since it's not terminal: retry (%s).",
                jobKey.getName(), jobKey.getGroup(), retry);
            // 立即重新运行
            throw new JobExecutionException(message, e, true);
        }
    }
}
