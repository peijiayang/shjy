package tech.binaryer.shjy.biz.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.binaryer.shjy.biz.dto.AddGoodsDto;
import tech.binaryer.shjy.biz.entity.ShjyGoodsEntity;
import tech.binaryer.shjy.biz.entity.ShjyOrderDetailsEntity;
import tech.binaryer.shjy.biz.entity.ShjyOrderEntity;
import tech.binaryer.shjy.biz.mapper.ShjyGoodsMapper;
import tech.binaryer.shjy.biz.mapper.ShjyOrderDetailsMapper;
import tech.binaryer.shjy.biz.mapper.ShjyOrderMapper;
import tech.binaryer.shjy.biz.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.ShjyOrderDetailsService;
import tech.binaryer.shjy.biz.util.SnowflakeIdWorker;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
public class ShjyOrderDetailsServiceImpl extends ServiceImpl<ShjyOrderDetailsMapper, ShjyOrderDetailsEntity> implements ShjyOrderDetailsService {

    @Autowired
    private ShjyOrderMapper shjyOrderMapper;

    @Autowired
    private ShjyOrderDetailsMapper shjyOrderDetailsMapper;

    @Autowired
    private ShjyGoodsMapper shjyGoodsMapper;

    @Override
    @Transactional
    public ResponseMessage insertGoodsOrder(AddGoodsDto addGoodsDto) {
        try {
            ShjyOrderEntity shjyOrderEntity = new ShjyOrderEntity();
            ShjyOrderDetailsEntity shjyOrderDetailsEntity = new ShjyOrderDetailsEntity();
            Date date = new Date();
            Long orderId = SnowflakeIdWorker.generateId();
            List<Integer> ids = addGoodsDto.getIds();
            List<Integer> listGoodsIds = new ArrayList<>();
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.select("id");
            List<ShjyGoodsEntity> shjyGoodsEntityList = shjyGoodsMapper.selectList(queryWrapper);
            shjyGoodsEntityList.forEach(item -> listGoodsIds.add(item.getId()));
            if (ids.isEmpty()) {
                return ResponseMessage.error("请选择商品。");
            }
            for (Integer id : ids
            ) {
                if (!listGoodsIds.contains(id)) {
                    return ResponseMessage.error("商品" + id + "不存在，请刷新后再试。");
                }
            }
            for (Integer id : ids
            ) {
                shjyOrderDetailsEntity.setOrderGoodsId(id).setOrderId(orderId);
                //加入多个商品
                shjyOrderDetailsMapper.insert(shjyOrderDetailsEntity);
                System.out.println(shjyOrderDetailsEntity);
            }
            shjyOrderEntity.setGoodsOrder(orderId).setGoodsOrderCreatetime(date);
            //生成一个订单
            shjyOrderMapper.insert(shjyOrderEntity);
            return ResponseMessage.ok("商品添加成功。");
        } catch (Exception e) {
            //e.printStackTrace();
            return ResponseMessage.error("商品添加失败，请稍后再试。");
        }
    }
}
