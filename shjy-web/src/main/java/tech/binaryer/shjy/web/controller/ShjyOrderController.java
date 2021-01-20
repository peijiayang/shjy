package tech.binaryer.shjy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.binaryer.shjy.biz.dto.AddGoodsDto;
import tech.binaryer.shjy.biz.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.ShjyOrderDetailsService;
import tech.binaryer.shjy.biz.service.ShjyOrderService;

import java.util.List;

/**
 * <p>
 *  控制类
 * </p>
 *
 * @author peijiayang
 * @since 2021-01-00
 */

@RestController
@RequestMapping("/order")
public class ShjyOrderController {

    @Autowired
    private ShjyOrderService shjyOrderService;

    @Autowired
    private ShjyOrderDetailsService shjyOrderDetailsService;

    @PostMapping("/insert")
    ResponseMessage insertOrder(){
        return shjyOrderService.addToOrder();
    }

    @PostMapping("/addgoods")
    ResponseMessage insertGoodsOder(@RequestBody AddGoodsDto addGoodsDto){
        System.out.println(addGoodsDto);
        return shjyOrderDetailsService.insertGoodsOrder(addGoodsDto);
    }

}
