
package tech.binaryer.shjy.web.timeTask.config;


import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import tech.binaryer.shjy.web.timeTask.RefoundCalculateTimeJob;


/**
 * 定时任务配置项
 * @author peijiayang
 * @create 2020-04-21 10:01
 */

@Component
public class QuartzConfig {
    @Bean
    public JobDetail printTimeJobDetail(){
        return JobBuilder.newJob(RefoundCalculateTimeJob.class)//PrintTimeJob我们的业务类
                .withIdentity("job_1")//可以给该JobDetail起一个id
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("msg", "Quartz")//关联键值对
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }


/**
     * 触发器 ->触发定时任务
     * @return
     */

    @Bean
    public Trigger printTimeJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/20 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(printTimeJobDetail())//关联上述的JobDetail
                .withIdentity("trigger_1")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }


}
