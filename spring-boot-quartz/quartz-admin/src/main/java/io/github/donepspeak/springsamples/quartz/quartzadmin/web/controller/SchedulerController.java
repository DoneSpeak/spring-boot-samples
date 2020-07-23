package io.github.donepspeak.springsamples.quartz.quartzadmin.web.controller;

import java.util.List;

import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model.SchedulerDO;
import io.github.donepspeak.springsamples.quartz.quartzadmin.service.SchedulerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author DoneSpeak
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/quartz-admin/scheduler")
public class SchedulerController {

    private final SchedulerService schedulerService;

    /**
     * 列表
     */
    @GetMapping("")
    public List<SchedulerDO> list() {
        return schedulerService.listAll();
    }
}
