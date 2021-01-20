package tech.binaryer.shjy.biz.service;

import tech.binaryer.shjy.biz.dto.GoodsDto;
import tech.binaryer.shjy.biz.message.ResponseMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peijiayang
 * @since 2021-01-19
 */
public interface ShjyGoodsService {
    /**
     * 分页查询商品信息/暂不用
     * @param goodsDto
     * @return
     */
    ResponseMessage getGoodsMessage(GoodsDto goodsDto);

    /**
     * 根据所属系类查询
     * @param goodsSeries
     * @return
     */
    ResponseMessage getGoodsMessageBySeries(Integer goodsSeries);
}
