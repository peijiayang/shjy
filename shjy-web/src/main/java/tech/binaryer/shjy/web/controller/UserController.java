package tech.binaryer.shjy.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.binaryer.shjy.biz.common.annotation.PassToken;
import tech.binaryer.shjy.biz.dto.UserDto;
import tech.binaryer.shjy.biz.entity.UserEntity;
import tech.binaryer.shjy.biz.common.message.ResponseMessage;
import tech.binaryer.shjy.biz.service.UserService;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author peijiayang
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/token")
    @PassToken
    Map login(@RequestBody UserDto userDto) throws Exception {

        return userService.login(userDto);
    }
    @PostMapping("/reg")
    @PassToken
    ResponseMessage regUser(@RequestBody UserEntity userEntityDto) throws Exception {

        return userService.regUser(userEntityDto);
    }


}

