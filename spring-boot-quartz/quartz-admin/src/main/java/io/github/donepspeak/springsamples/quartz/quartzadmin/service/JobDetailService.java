package io.github.donepspeak.springsamples.quartz.quartzadmin.service;

import com.github.pagehelper.PageInfo;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model.JobDetailDO;
import io.github.donepspeak.springsamples.quartz.quartzadmin.entity.query.JobDetailSearchQuery;

/**
 * @author DoneSpeak
 */
public interface JobDetailService {

    PageInfo<JobDetailDO> listJobs(JobDetailSearchQuery query, int pageSize, int pageIndex);
}
