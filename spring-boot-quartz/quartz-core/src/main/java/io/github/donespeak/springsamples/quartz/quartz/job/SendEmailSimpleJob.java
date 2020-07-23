package io.github.donespeak.springsamples.quartz.quartz.job;

import io.github.donespeak.springsamples.quartz.repository.model.User;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import io.github.donespeak.springsamples.quartz.service.UserService;
import lombok.NoArgsConstructor;

/**
 * @author DoneSpeak
 */
@Slf4j
@NoArgsConstructor
public class SendEmailSimpleJob extends QuartzJobBean {

    @Autowired
    private UserService userService;

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Class<? extends Job> job = SendEmailSimpleJob.class;
        User user = userService.getById(100);
        log.info("User({}): {}", email, user);
    }
}
