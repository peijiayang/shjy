package tech.binaryer.shjy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tech.binaryer.shjy.biz.common.annotation.UserLoginToken;
import tech.binaryer.shjy.biz.entity.JobInfoEntity;
import tech.binaryer.shjy.biz.common.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.JobInfoService;


/**
 * <p>
 *  控制类
 * </p>
 *
 * @author peijiayang
 * @since 2021-04-08
 */

@RestController
@RequestMapping("/job")
@UserLoginToken(required = true)
public class JobInfoController {
    @Autowired
    private JobInfoService jobInfoService;

    @GetMapping("/jobinfo")
    ResponseMessage getJobinfo(@RequestParam int pageIndex ,@RequestParam int pageSize){
        return jobInfoService.getJobInfo(pageIndex,pageSize);
    }

    @PostMapping("/insertjob")
    ResponseMessage insertJob(@RequestBody JobInfoEntity jobInfoEntity){
        System.out.println(jobInfoEntity.getJobContent());
        return jobInfoService.insertJobInfo(jobInfoEntity);
    }

    @PostMapping("/deletejobinfo")
    ResponseMessage deleteJobInfo(@RequestParam int jobId){
        return jobInfoService.deleteJobInfo(jobId);
    }

    @PostMapping("/updatejobinfo")
    ResponseMessage updateJobInfo(@RequestParam int jobId,@RequestParam String jobParam) {
        return jobInfoService.updateJobinfo(jobId, jobParam);
    }
}
