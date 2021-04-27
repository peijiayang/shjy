package tech.binaryer.shjy.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.binaryer.shjy.biz.common.util.SignParams;
import tech.binaryer.shjy.biz.dto.UserDto;
import tech.binaryer.shjy.biz.message.ResponseMessage;
import tech.binaryer.shjy.biz.entity.UserEntity;
import tech.binaryer.shjy.biz.mapper.UserMapper;
import tech.binaryer.shjy.biz.service.UserService;
import tech.binaryer.shjy.biz.common.util.JwtUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peijiayang
 * @since 2021-03-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public Map login(UserDto userDto) {
        Map authorizationMap = new HashMap<>();
        LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(UserEntity::getId,UserEntity::getPassWord, UserEntity::getRealName,UserEntity::getUserName);
        lambdaQueryWrapper.eq(UserEntity::getUserName, userDto.getUserName());
        if (ObjectUtils.isNull(userMapper.selectOne(lambdaQueryWrapper))) {
            authorizationMap.put("success", false);
            authorizationMap.put("msg", "用户不存在");
            return authorizationMap;
        }
        UserEntity userEntity=userMapper.selectOne(lambdaQueryWrapper);
        String pwd = userEntity.getPassWord();
        String realName = userEntity.getRealName();
        Integer useId = userEntity.getId();
        String username = userEntity.getUserName();

        if (pwd.equals(userDto.getPassWord())) {
            authorizationMap.put("success", true);
            authorizationMap.put("username",username );
            authorizationMap.put("realName", realName);
            authorizationMap.put("token", JwtUtils.createJWT(SignParams.SIGN_JWC, 1000 * 600, username,useId));
            return authorizationMap;
        } else {
            authorizationMap.put("success", false);
            authorizationMap.put("msg", "账号或密码错误！");
            return authorizationMap;
        }
    }

    @Override
    public ResponseMessage regUser(UserEntity userEntityDto) {
        try {
            LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.select(UserEntity::getUserName);
            lambdaQueryWrapper.eq(UserEntity::getUserName, userEntityDto.getUserName());
            //如果用户账号不存在则插入
            if (ObjectUtils.isNotNull(userMapper.selectOne(lambdaQueryWrapper))) {
                return ResponseMessage.ok("用户名已存在！");
            }
            return ResponseMessage.ok(userMapper.insert(userEntityDto));
        } catch (Exception e) {
            return ResponseMessage.error("用户插入异常");
        }

    }
}
