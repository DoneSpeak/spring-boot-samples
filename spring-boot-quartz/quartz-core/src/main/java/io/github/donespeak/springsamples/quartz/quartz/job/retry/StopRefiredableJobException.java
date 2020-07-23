package io.github.donespeak.springsamples.quartz.quartz.job.retry;

/**
 * 用于停止{@link RefireableJob}的重试
 *
 * @author Yang Guanrong
 */
public class StopRefiredableJobException extends Exception {
    public StopRefiredableJobException() {
        super();
    }

    public StopRefiredableJobException(String message) {
        super(message);
    }

    public StopRefiredableJobException(String message, Throwable cause) {
        super(message, cause);
    }
}
