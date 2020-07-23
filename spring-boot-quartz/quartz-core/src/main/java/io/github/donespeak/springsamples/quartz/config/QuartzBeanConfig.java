package io.github.donespeak.springsamples.quartz.config;

import io.github.donespeak.springsamples.quartz.quartz.job.FailRetryJob;
import io.github.donespeak.springsamples.quartz.quartz.job.ImageJob;
import io.github.donespeak.springsamples.quartz.quartz.scheduler.DefaultSchedulerProviderManager;
import io.github.donespeak.springsamples.quartz.quartz.scheduler.SchedulerFactoryBeanProvider;
import io.github.donespeak.springsamples.quartz.quartz.scheduler.SchedulerProvider;
import lombok.RequiredArgsConstructor;
import org.quartz.Calendar;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author DoneSpeak
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(CustomerQuartzProperties.class)
public class QuartzBeanConfig {

    private final QuartzProperties properties;

    private final ObjectProvider<SchedulerFactoryBeanCustomizer> customizers;

    private final JobDetail[] jobDetails;

    private final Map<String, Calendar> calendars;

    private final Trigger[] triggers;

    private final ApplicationContext applicationContext;

    private final CustomerQuartzProperties customerQuartzProperties;

    @Bean
    public DefaultSchedulerProviderManager schedulerProviderManager(List<SchedulerProvider> providers) {
        DefaultSchedulerProviderManager providerManager = new DefaultSchedulerProviderManager(providers);
        return providerManager;
    }

    @Bean
    @Primary
    public SchedulerFactoryBeanProvider quartzScheduler() {
        SchedulerFactoryBeanProvider schedulerFactoryBean = new SchedulerFactoryBeanProvider(null);
        configSchedulerFactoryBean(schedulerFactoryBean, getDefaultProperties(), this.properties.getSchedulerName());
        customize(schedulerFactoryBean);
        if (this.jobDetails != null && this.jobDetails.length > 0) {
            schedulerFactoryBean.setJobDetails(this.jobDetails);
        }
        if (this.calendars != null && !this.calendars.isEmpty()) {
            schedulerFactoryBean.setCalendars(this.calendars);
        }
        if (this.triggers != null && this.triggers.length > 0) {
            schedulerFactoryBean.setTriggers(this.triggers);
        }
        customize(schedulerFactoryBean);
        return schedulerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBeanProvider imageJobSchedulerFactoryBean() {
        Class<? extends Job> imageJobClass = ImageJob.class;
        SchedulerFactoryBeanProvider schedulerFactoryBean = new SchedulerFactoryBeanProvider(imageJobClass);
        Properties quartzProperties = getDefaultProperties();
        quartzProperties.put("org.quartz.threadPool.threadCount", "3");
        configSchedulerFactoryBean(schedulerFactoryBean, quartzProperties, "imageJobSchedulerFactoryBean");
        customize(schedulerFactoryBean);
        return schedulerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBeanProvider databaseJobSchedulerFactoryBean2() {
        SchedulerFactoryBeanProvider schedulerFactoryBean = new SchedulerFactoryBeanProvider(FailRetryJob.class);
        Properties quartzProperties = getDefaultProperties();
        quartzProperties.put("org.quartz.threadPool.threadCount", "1");
        configSchedulerFactoryBean(schedulerFactoryBean, quartzProperties, "imageJobSchedulerFactoryBean2");
        customize(schedulerFactoryBean);
        return schedulerFactoryBean;
    }

    private Properties getDefaultProperties() {
        return asProperties(this.properties.getProperties());
    }

    private void configSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean, Properties quartzProperties,
        String schedulerName) {
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(this.applicationContext);
        schedulerFactoryBean.setJobFactory(jobFactory);
        if (schedulerName != null) {
            schedulerFactoryBean.setSchedulerName(schedulerName);
        }
        schedulerFactoryBean.setAutoStartup(this.properties.isAutoStartup());
        schedulerFactoryBean.setStartupDelay((int)this.properties.getStartupDelay().getSeconds());
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(this.properties.isWaitForJobsToCompleteOnShutdown());
        schedulerFactoryBean.setOverwriteExistingJobs(this.properties.isOverwriteExistingJobs());
        if(!quartzProperties.isEmpty()) {
            schedulerFactoryBean.setQuartzProperties(quartzProperties);
        }
    }

    private Properties asProperties(Map<String, String> source) {
        Properties properties = new Properties();
        properties.putAll(source);
        return properties;
    }

    private void customize(SchedulerFactoryBean schedulerFactoryBean) {
        this.customizers.orderedStream().forEach((customizer) -> customizer.customize(schedulerFactoryBean));
    }
}
