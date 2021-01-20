package tech.binaryer.shjy.biz.service;

import tech.binaryer.shjy.biz.message.ResponseMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peijiayang
 * @since 2021-01-19
 */
public interface ShjyOrderService {
    /**
     * 生产一个订单编号
     * @return
     */
    ResponseMessage addToOrder();

}
