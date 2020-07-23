package io.github.donespeak.springsamples.quartz.quartz.job.retry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.classify.SubclassClassifier;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryOperations;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.NeverRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public abstract class NeverEndRetryableJob extends RetryableJob {

    // 重试间隔的最长时间（30min）
    private static final long DEFAULT_MAX_INTERVAL = 30 * 60 * 1000;
    private static final long DEFAULT_INITIAL_INTERVAL = 30 * 1000;
    private static final double DEFAULT_MULTIPLIER = 2;

    private long initialInterval;
    private double multiplier;
    private long maxInterval;

    public NeverEndRetryableJob() {
        this(DEFAULT_INITIAL_INTERVAL, DEFAULT_MULTIPLIER, DEFAULT_MAX_INTERVAL);
    }

    public NeverEndRetryableJob(long initialInterval, double multiplier, long maxInterval) {
        this.initialInterval = (initialInterval > 1 ? initialInterval : 1);
        this.multiplier = (multiplier > 1.0 ? multiplier : 1.0);
        this.maxInterval = (maxInterval > 0 ? maxInterval : 1);
    }

    @Override
    protected RetryOperations getRetryOperations() {
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        // 初始时间间隔为30s
        backOffPolicy.setInitialInterval(initialInterval);
        backOffPolicy.setMultiplier(multiplier);
        backOffPolicy.setMaxInterval(maxInterval);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        // 一直重试
        retryTemplate.setRetryPolicy(getRetryPolicy());
        return retryTemplate;
    }

    /**
     * 当重试时间间隔达到最大重试时间间隔时，转化为error输出
     */
    @Override
    protected boolean switchToLogError(RetryContext context) {
        int retryCount = context.getRetryCount();
        return initialInterval * Math.pow(multiplier, retryCount) > maxInterval;
    }

    /**
     * 该方法指定的异常，
     */
    protected List<Class<? extends Throwable>> getCausesToStopRetry() {
        return new ArrayList<>();
    }

    private RetryPolicy getRetryPolicy() {
        Map<Class<? extends Throwable>, RetryPolicy> policyMap = new HashMap<>();
        RetryPolicy neverRetryPolicy = new NeverRetryPolicy();
        List<Class<? extends Throwable>> causesToStop = getCausesToStopRetry();
        if (causesToStop != null) {
            for (Class<? extends Throwable> cause : causesToStop) {
                policyMap.put(cause, neverRetryPolicy);
            }
        }
        ExceptionClassifierRetryPolicy policy = new ExceptionClassifierRetryPolicy();
        policy.setExceptionClassifier(new SubclassClassifier<>(policyMap, new AlwaysRetryPolicy()));
        return policy;
    }
}
