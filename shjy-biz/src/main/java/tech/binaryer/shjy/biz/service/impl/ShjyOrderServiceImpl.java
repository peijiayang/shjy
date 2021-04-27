package tech.binaryer.shjy.biz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.binaryer.shjy.biz.entity.ShjyOrderEntity;
import tech.binaryer.shjy.biz.mapper.ShjyOrderMapper;
import tech.binaryer.shjy.biz.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.ShjyOrderService;
import tech.binaryer.shjy.biz.common.util.SnowflakeIdWorker;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peijiayang
 * @since 2021-01-19
 */
@Service
public class ShjyOrderServiceImpl extends ServiceImpl<ShjyOrderMapper, ShjyOrderEntity> implements ShjyOrderService {

    @Autowired
    private ShjyOrderMapper shjyOrderMapper;

    @Override
    public ResponseMessage addToOrder() {
        try {
            ShjyOrderEntity shjyOrderEntity = new ShjyOrderEntity();
            Date date = new Date();
            shjyOrderEntity.setGoodsOrder(SnowflakeIdWorker.generateId()).setGoodsOrderCreatetime(date);
            shjyOrderMapper.insert(shjyOrderEntity);
            return ResponseMessage.ok("新增一条订单成功");
        } catch (Exception e){
            return ResponseMessage.error("订单生成异常");
        }
    }
}
