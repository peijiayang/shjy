package tech.binaryer.shjy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.binaryer.shjy.biz.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.ShjySeriesService;

/**
 * <p>
 * 控制类
 * </p>
 *
 * @author peijiayang
 * @since 2021-01-00
 */

@RestController
@RequestMapping("/series")
public class ShjySeriesController {

    @Autowired
    private ShjySeriesService shjySeriesService;

    @GetMapping("/all")
    ResponseMessage getAllSeries() {
        return shjySeriesService.getSeries();
    }
}
