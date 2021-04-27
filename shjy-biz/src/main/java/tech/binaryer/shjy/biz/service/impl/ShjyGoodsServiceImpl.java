package tech.binaryer.shjy.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.binaryer.shjy.biz.dto.GoodsDto;
import tech.binaryer.shjy.biz.entity.ShjyGoodsEntity;
import tech.binaryer.shjy.biz.mapper.ShjyGoodsMapper;
import tech.binaryer.shjy.biz.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.ShjyGoodsService;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peijiayang
 * @since 2021-01-19
 */
@Service
public class ShjyGoodsServiceImpl extends ServiceImpl<ShjyGoodsMapper, ShjyGoodsEntity> implements ShjyGoodsService {

    @Autowired
    private ShjyGoodsMapper shjyGoodsMapper;

    @Override
    public ResponseMessage getGoodsMessage(GoodsDto goodsDto) {
        LambdaQueryWrapper<ShjyGoodsEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(ShjyGoodsEntity::getGoodsName, ShjyGoodsEntity::getGoodsSeries, ShjyGoodsEntity::getGoodsDegree);
        lambdaQueryWrapper.eq(ObjectUtils.isNotNull(goodsDto.getGoodsSeries()), ShjyGoodsEntity::getGoodsSeries, goodsDto.getGoodsSeries())
                .eq(ObjectUtils.isNotNull(goodsDto.getId()), ShjyGoodsEntity::getId, goodsDto.getId());
        PageHelper.startPage(goodsDto.getPageIndex(), goodsDto.getPageSize());
        PageInfo pageInfo = new PageInfo(shjyGoodsMapper.selectList(lambdaQueryWrapper));
        return ResponseMessage.ok(pageInfo);
    }

    @Override
    public ResponseMessage getGoodsMessageBySeries(Integer goodsSeries) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("goods_series", goodsSeries);
        List<ShjyGoodsEntity> listGoods = shjyGoodsMapper.selectList(queryWrapper);
        return ResponseMessage.ok(listGoods);
    }
}
