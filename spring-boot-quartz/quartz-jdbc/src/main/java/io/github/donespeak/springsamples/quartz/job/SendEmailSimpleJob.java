package io.github.donespeak.springsamples.quartz.job;

import io.github.donespeak.springsamples.quartz.repository.model.User;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import io.github.donespeak.springsamples.quartz.service.UserService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

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
        User user = userService.getById(100);
        log.info("User({}): {}", email, user);
    }
}
