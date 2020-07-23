// package io.github.donespeak.springsamples.quartz.quartz.job;
//
// import java.time.Duration;
//
// import org.quartz.JobDetail;
// import org.quartz.SchedulerException;
// import org.quartz.Trigger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.bees360.base.exception.ServiceException;
// import com.bees360.schedule.job.retry.RetryableJobTriggerFactory;
// import com.bees360.schedule.support.SchedulerProviderManager;
// import com.bees360.security.core.authorize.UrlAccessSecurityConfigProvider;
// import com.bees360.web.symbility.service.ClaimExternalDocumentTransferService;
//
// /**
//  * @author Guanrong Yang
//  */
// @RestController
// @RequestMapping("/tmpjob")
// public class UploadTrigger implements UrlAccessSecurityConfigProvider {
//
//     @Autowired
//     private ClaimExternalDocumentTransferService claimExternalDocumentTransferService;
//
//     @Autowired
//     private SchedulerProviderManager schedulerProviderManager;
//
//     @GetMapping("/report/{projectId:\\d+}")
//     public void report(@PathVariable long projectId) throws ServiceException {
//         claimExternalDocumentTransferService.uploadReportsToSymbility(projectId);
//     }
//
//     @GetMapping("/image/{projectId:\\d+}")
//     public void image(@PathVariable long projectId) throws ServiceException {
//         claimExternalDocumentTransferService.uploadPhotosToSymbility(projectId);
//     }
//
//     @GetMapping
//     public void test() throws SchedulerException {
//         Trigger trigger = RetryableJobTriggerFactory
//             .retryForeverTrigger("a" + System.currentTimeMillis(), "b", Duration.ofSeconds(2)).build();
//         JobDetail jobDetail = PrintRetryableJob.createJobDetail("a" + System.currentTimeMillis(), "b",
//             new PrintRetryableJob.ValueObj("obj"));
//         schedulerProviderManager.getProvider(PrintRetryableJob.class).getScheduler().scheduleJob(jobDetail, trigger);
//     }
//
//     @Override
//     public void ignoring(WebSecurity web) {
//         web.ignoring().antMatchers("/tmpjob/**");
//     }
// }
