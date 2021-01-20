package tech.binaryer.shjy.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.binaryer.shjy.biz.entity.ShjySeriesEntity;
import tech.binaryer.shjy.biz.mapper.ShjySeriesMapper;
import tech.binaryer.shjy.biz.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.ShjySeriesService;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peijiayang
 * @since 2021-01-19
 */
@Service
public class ShjySeriesServiceImpl extends ServiceImpl<ShjySeriesMapper, ShjySeriesEntity> implements ShjySeriesService {

    @Autowired
    private ShjySeriesMapper shjySeriesMapper;

    @Override
    public ResponseMessage getSeries() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List listSeries = shjySeriesMapper.selectList(queryWrapper);
        return ResponseMessage.ok(listSeries);
    }
}
