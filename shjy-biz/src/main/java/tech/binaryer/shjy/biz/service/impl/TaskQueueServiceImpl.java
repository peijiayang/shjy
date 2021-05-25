package tech.binaryer.shjy.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import tech.binaryer.shjy.biz.common.util.SignParams;
import tech.binaryer.shjy.biz.common.util.UserUtils;
import tech.binaryer.shjy.biz.entity.JobInfoEntity;
import tech.binaryer.shjy.biz.entity.TaskQueueEntity;
import tech.binaryer.shjy.biz.mapper.TaskQueueMapper;
import tech.binaryer.shjy.biz.common.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.TaskQueueService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peijiayang
 * @since 2021-05-02
 */
@Service
public class TaskQueueServiceImpl extends ServiceImpl<TaskQueueMapper, TaskQueueEntity> implements TaskQueueService {
    private static final Logger logger = LoggerFactory.getLogger(TaskQueueServiceImpl.class);


    @Resource(name = "taskExcutor")
    @Autowired
    private ThreadPoolTaskExecutor executor;
    @Autowired
    private TaskQueueMapper taskQueueMapper;

    @Override
    public ResponseMessage insertTask(TaskQueueEntity taskQueueEntity) {
        try {
            taskQueueEntity.setCreateId(UserUtils.getUserId())
                    .setCreateTime(new Date())
                    .setTaskStatus(SignParams.TASK_STATUS_UNDO);
            taskQueueMapper.insert(taskQueueEntity);
            System.out.println(new Date());
            return ResponseMessage.ok("任务添加成功，点击【记录查询】可查看任务执行结果。");
        } catch (Exception e) {
            return ResponseMessage.error("任务添加发生异常!");
        }
    }

    @Override
    public ResponseMessage getTaskInfo(int pageIndex, int pageSize, String taskData, String taskStatus) {
        try {
            PageHelper.startPage(pageIndex, pageSize);
            PageInfo pageInfo = new PageInfo(taskQueueMapper.getTaskInfo(taskData, taskStatus));
            return ResponseMessage.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("任务查询异常！");
        }
    }

    @Override
    @Async
    public void getCertificate() {

        Long timeS = System.currentTimeMillis();
        try {

            FutureTask futureTask1 = new FutureTask<Integer>(() -> {
                logger.info(Thread.currentThread().getName() + "正在处理....1");
                //筛选出状态='队列中'的任务
                LambdaQueryWrapper<TaskQueueEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(TaskQueueEntity::getTaskCategory, SignParams.TASK_CATE_REFOUND).eq(TaskQueueEntity::getTaskStatus, SignParams.TASK_STATUS_UNDO);
                taskQueueMapper.selectList(lambdaQueryWrapper).forEach(item -> {
                    //更新任务状态
                    UpdateWrapper<TaskQueueEntity> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("id", item.getId());
                    TaskQueueEntity taskQueueEntity = new TaskQueueEntity();
                    taskQueueEntity.setTaskStatus(SignParams.TASK_STATUS_DOSUCCESS);
                    taskQueueMapper.update(taskQueueEntity, updateWrapper);
                    logger.info(item.getTaskName() + "----" + item.getTaskData() + "----处理完成");
                });
                return 1;
            });
            executor.execute(futureTask1);
            futureTask1.get();
        } catch (Exception e) {
            logger.info("发生异常");
            Thread.interrupted();
        }
        Long timeE = System.currentTimeMillis();

        logger.info("执行时间=" + (timeE - timeS));

    }
}
