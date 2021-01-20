package tech.binaryer.shjy.biz.service;

import tech.binaryer.shjy.biz.dto.AddGoodsDto;
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
    ResponseMessage getGoodsMessage(AddGoodsDto goodsDto);

    /**
     * 根据所属系类查询-小程序
     * @param goodsSeries
     * @return
     */
    ResponseMessage getGoodsMessageBySeries(Integer goodsSeries);
}
