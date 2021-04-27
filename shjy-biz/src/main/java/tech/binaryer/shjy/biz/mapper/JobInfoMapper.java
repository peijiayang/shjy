package tech.binaryer.shjy.biz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import tech.binaryer.shjy.biz.dto.JobInfoDto;
import tech.binaryer.shjy.biz.entity.JobInfoEntity;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peijiayang
 * @since 2021-04-18
 */
@Repository
public interface JobInfoMapper extends BaseMapper<JobInfoEntity> {

     List<JobInfoDto> getJobInfo();
}
