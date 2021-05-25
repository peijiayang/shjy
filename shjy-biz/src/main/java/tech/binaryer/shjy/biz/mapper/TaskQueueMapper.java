package tech.binaryer.shjy.biz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tech.binaryer.shjy.biz.dto.TaskInfoDto;
import tech.binaryer.shjy.biz.entity.TaskQueueEntity;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peijiayang
 * @since 2021-05-02
 */
@Repository
public interface TaskQueueMapper extends BaseMapper<TaskQueueEntity> {
    List<TaskInfoDto> getTaskInfo(@Param("taskData") String taskData, @Param("taskStatus") String taskStatus);

}
