// package io.github.donespeak.springsamples.quartz;
//
// import io.github.donespeak.springsamples.quartz.repository.model.User;
// import io.github.donespeak.springsamples.quartz.service.UserService;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j;
// import lombok.extern.log4j.Log4j2;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import java.util.Optional;
//
// /**
//  * @author DoneSpeak
//  */
// @Slf4j
// @RequiredArgsConstructor
// @RestController
// @RequestMapping("/scheduler")
// public class QuartzSchedulerController {
//
//     private final Optional<UserService> userService;
//
//     private final Optional<FakeUtil> fakeUtil;
//
//     @GetMapping("tryy")
//     public User tryy() {
//         log.info("hjahhah");
//         log.info("{}", userService);
//         log.info("{}", fakeUtil);
//         return userService.isPresent()? userService.get().getById(100): null;
//     }
// }
