/*
package com.lhkk.cabinet.web.timeTask;

import com.lhkk.cabinet.biz.service.ShelfService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

*/
/**
 * 调用Service层 ，在此实现定时任务业务逻辑
 *
 * @author peijiayang
 * @create 2020-04-21 10:01
 *//*

public class QueryNetStatusTimeJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(QueryNetStatusTimeJob.class);
    @Autowired
    private ShelfService shelfService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //String msg = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("msg");
        shelfService.getShelfDto();
    }
}
*/
