package tech.binaryer.shjy.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.binaryer.shjy.biz.entity.JobInfoEntity;
import tech.binaryer.shjy.biz.mapper.JobInfoMapper;
import tech.binaryer.shjy.biz.common.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.JobInfoService;
import tech.binaryer.shjy.biz.common.util.UserUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peijiayang
 * @since 2021-04-18
 */
@Service
public class JobInfoServiceImpl extends ServiceImpl<JobInfoMapper, JobInfoEntity> implements JobInfoService {
    @Autowired
    private JobInfoMapper jobInfoMapper;


    @Override
    public ResponseMessage getJobInfo(int pageIndex,int pageSize) {
        try {
            PageHelper.startPage(pageIndex,pageSize);
            PageInfo pageInfo = new PageInfo(jobInfoMapper.getJobInfo());
            return ResponseMessage.ok(pageInfo);
        } catch (Exception e) {
            return ResponseMessage.error("Job查询异常！");
        }
    }

    @Override
    public ResponseMessage insertJobInfo(JobInfoEntity jobInfoEntity) {
        try {
            System.out.println("-=-=-=-=-=-=-=-="+UserUtils.getUserId());
            jobInfoEntity.setUpdateId(UserUtils.getUserId());
            jobInfoMapper.insert(jobInfoEntity);
            return ResponseMessage.ok("Job添加成功！");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseMessage.error("Job添加异常！");
        }
    }

    @Override
    public ResponseMessage deleteJobInfo(int jobId) {
        try{
            if (ObjectUtils.isNull(jobInfoMapper.selectById(jobId))){
                return ResponseMessage.error("该job不存在，请刷新！");
            }
            jobInfoMapper.deleteById(jobId);
            return ResponseMessage.ok("删除成功！");
        }catch (Exception e){
            return ResponseMessage.error("job删除异常");
        }
    }

    @Override
    public ResponseMessage updateJobinfo(int jobId,String jobParam) {
        try{
            if (ObjectUtils.isNull(jobInfoMapper.selectById(jobId))){
                return ResponseMessage.error("该job不存在，请刷新！");
            }
            UpdateWrapper<JobInfoEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("job_id",jobId);
            JobInfoEntity jobInfoEntity = new JobInfoEntity();
           if (jobParam.equals("null")|jobParam==null|jobParam=="null"|"null".equals(jobParam)|jobParam.equals("")){
                jobInfoEntity.setJobParam("");
            }
            jobInfoEntity.setUpdateId(UserUtils.getUserId()).setJobParam(jobParam.trim());
            jobInfoMapper.update(jobInfoEntity,updateWrapper);
            return ResponseMessage.ok("参数更新成功!");

        }catch (Exception e){
            return ResponseMessage.error("参数更新异常！");
        }
    }
}
