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
public interface ShjyOrderDetailsService  {


    /**
     * 商品加入购物车
     * @param addGoods
     * @return
     */
    ResponseMessage insertGoodsOrder(AddGoodsDto addGoods);

}
