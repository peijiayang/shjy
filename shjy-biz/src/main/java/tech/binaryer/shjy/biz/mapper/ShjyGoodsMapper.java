package tech.binaryer.shjy.biz.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import tech.binaryer.shjy.biz.dto.GoodsDto;
import tech.binaryer.shjy.biz.entity.ShjyGoodsEntity;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peijiayang
 * @since 2021-01-19
 */
@Repository
public interface ShjyGoodsMapper extends BaseMapper<ShjyGoodsEntity> {

}
