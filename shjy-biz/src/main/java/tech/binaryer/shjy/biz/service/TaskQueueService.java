package tech.binaryer.shjy.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import tech.binaryer.shjy.biz.entity.TaskQueueEntity;
import tech.binaryer.shjy.biz.common.message.ResponseMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peijiayang
 * @since 2021-05-02
 */
public interface TaskQueueService extends IService<TaskQueueEntity> {
    /**
     * 订单退款情分任务创建
     * @param taskQueueEntity
     * @return
     */
    ResponseMessage insertTask(TaskQueueEntity taskQueueEntity);

    /**
     *
     * @param pageIndex
     * @param pageSize
     * @param taskData
     * @param taskStatus
     * @return
     */
    ResponseMessage getTaskInfo(int pageIndex,int pageSize,String taskData,String taskStatus);

    /**
     * 定时任务
     */

    void getCertificate();


}
