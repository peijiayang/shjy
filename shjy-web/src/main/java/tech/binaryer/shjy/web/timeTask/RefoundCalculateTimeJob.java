
package tech.binaryer.shjy.web.timeTask;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import tech.binaryer.shjy.biz.service.TaskQueueService;


/**
 * 调用Service层 ，在此实现定时任务业务逻辑
 *
 * @author peijiayang
 * @create 2020-04-21 10:01
 */

public class RefoundCalculateTimeJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(RefoundCalculateTimeJob.class);
    @Autowired
    private TaskQueueService taskQueueService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //String msg = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("msg");
        logger.info("定时任务开启");
        taskQueueService.getCertificate();
    }
}

