package tech.binaryer.shjy.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import tech.binaryer.shjy.biz.entity.JobInfoEntity;
import tech.binaryer.shjy.biz.common.message.ResponseMessage;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peijiayang
 * @since 2021-04-18
 */
public interface JobInfoService extends IService<JobInfoEntity> {

    ResponseMessage getJobInfo(int pageIndex,int pageSize);

    ResponseMessage insertJobInfo(JobInfoEntity jobInfoEntity);

    ResponseMessage deleteJobInfo(int jobId);

    ResponseMessage updateJobinfo(int jobId,String jobParam);

}
