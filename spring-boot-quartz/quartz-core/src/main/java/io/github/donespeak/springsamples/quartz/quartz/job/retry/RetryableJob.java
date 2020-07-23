package io.github.donespeak.springsamples.quartz.quartz.job.retry;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryOperations;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangguanrong
 */
@Slf4j
public abstract class RetryableJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {
        RetryOperations retryOperations = getRetryOperations();
        boolean successful = true;
        Throwable lastCause = null;
        try {
            if (retryOperations == null) {
                tryExecute(jobContext, null);
            } else {
                doWithRetry(retryOperations, jobContext);
            }
        } catch (Exception e) {
            successful = false;
            lastCause = e;
        }
        try {
            afterFinalExecute(successful, lastCause);
        } finally {
            unscheduleAllTriggers(successful, lastCause);
        }
    }

    /**
     * 设置重试操作
     */
    protected RetryOperations getRetryOperations() {
        return null;
    }

    private void doWithRetry(RetryOperations retryOperations, JobExecutionContext jobContext) throws Exception {
        retryOperations.execute(retryContext -> {
            try {
                tryExecute(jobContext, retryContext);
            } catch (Exception e) {
                if (switchToLogError(retryContext)) {
                    log.error("Retry [{}]: {}", retryContext.getRetryCount(), e.getMessage(), e);
                } else {
                    log.warn("Retry [{}]: {}", retryContext.getRetryCount(), e.getMessage(), e);
                }
                throw e;
            }
            return true;
        }, context -> {
            // 全部重试均失败
            Throwable lastCause = context.getLastThrowable();
            if (lastCause instanceof Exception) {
                throw (Exception)lastCause;
            } else {
                throw new Exception(lastCause);
            }
        });
    }

    /**
     * 每次从事失败之后会输入日志，该方法设置是否输出error级别日志
     */
    protected boolean switchToLogError(RetryContext context) {
        return false;
    }

    /**
     *
     * @param context
     * @param retryContext 重试机制的上下文，当{@code getRetryOperations()} 返回为null是，该值为null，否则为重试机制的上下文
     * @throws Exception
     */
    protected abstract void tryExecute(JobExecutionContext context, RetryContext retryContext) throws Exception;

    /**
     * 提供子类一个方法，可以在成功执行或者全部重试均失败之后可以做一些处理，然后再终止所有与该job相关的触发器
     *
     * @param successful
     *            执行结果
     * @param lastEx
     *            最后一次导致失败的异常，当 {@code successful} 为true时，ex为null。
     */
    protected void afterFinalExecute(boolean successful, Throwable lastEx) {}

    private void unscheduleAllTriggers(boolean successful, Throwable lastEx) throws JobExecutionException {
        String message = "";
        if (successful) {
            message = "Unschedule all triggers of this job since the job is completed.";
        } else {
            message = "Unschedule all triggers of this job since the job exceeds the retry times.";
        }
        JobExecutionException e = new JobExecutionException(message, lastEx);
        // 结束所有和该job相关的调度器
        e.setUnscheduleAllTriggers(true);
        throw e;
    }
}
