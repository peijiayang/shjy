package tech.binaryer.shjy.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tech.binaryer.shjy.biz.common.annotation.UserLoginToken;
import tech.binaryer.shjy.biz.entity.TaskQueueEntity;
import tech.binaryer.shjy.biz.common.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.TaskQueueService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author peijiayang
 * @since 2021-05-02
 */
@RestController
@RequestMapping("/task")
@UserLoginToken
public class TaskQueueController {

    @Autowired
    private TaskQueueService taskQueueService;
    @PostMapping("/inserttask")
    ResponseMessage insertTask(@RequestBody TaskQueueEntity taskQueueEntity){
        return taskQueueService.insertTask(taskQueueEntity);
    }
    @GetMapping("/gettaskinfo")
    ResponseMessage getTaskInfo(@RequestParam int pageIndex, @RequestParam int pageSize,
                                @RequestParam String taskData, @RequestParam String taskStatus){
        return taskQueueService.getTaskInfo(pageIndex,pageSize,taskData,taskStatus);
    }

}

