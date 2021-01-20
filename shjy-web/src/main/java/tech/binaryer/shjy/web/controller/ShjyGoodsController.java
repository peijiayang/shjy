package tech.binaryer.shjy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.binaryer.shjy.biz.dto.AddGoodsDto;
import tech.binaryer.shjy.biz.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.ShjyGoodsService;

/**
 * <p>
 *  控制类
 * </p>
 *
 * @author peijiayang
 * @since 2021-01-00
 */

@RestController
@RequestMapping("/goods")
public class ShjyGoodsController {
    @Autowired
    private ShjyGoodsService shjyGoodsService;
    @GetMapping("/all")
    ResponseMessage getAllGoods(@RequestBody AddGoodsDto goodsDto){
        return shjyGoodsService.getGoodsMessage(goodsDto);
    }

    @GetMapping("/byseries/{series}")
    ResponseMessage getGoodsBySeries(@PathVariable Integer series){
        return shjyGoodsService.getGoodsMessageBySeries(series);
    }
}
