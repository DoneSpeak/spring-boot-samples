package io.github.donespeak.springsamples.quartz.config;

import javax.annotation.PostConstruct;

import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * @author DoneSpeak
 */
@Configuration
public class QuartzConfiguration {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private JobFactory jobFactory;

    /**
     * 配置任务工厂实例
     */
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        /**
         * 采用自定义任务工厂 整合spring实例来完成构建任务
         * @see AutowiringSpringBeanJobFactory
         */
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @PostConstruct
    public void postConstruct() {
        schedulerFactoryBean.setJobFactory(jobFactory);
    }

    /**
     * 继承org.springframework.scheduling.quartz.SpringBeanJobFactory 实现任务实例化方式
     */
    public static class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext context) {
            beanFactory = context.getAutowireCapableBeanFactory();
        }

        /**
         * 将job实例交给spring ioc托管 我们在job实例实现类内可以直接使用spring注入的调用被spring ioc管理的实例
         */
        @Override
        protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            /**
             * 将job实例交付给spring ioc
             */
            beanFactory.autowireBean(job);
            return job;
        }
    }
}