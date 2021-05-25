package tech.binaryer.shjy.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.binaryer.shjy.biz.dto.UserDto;
import tech.binaryer.shjy.biz.common.message.ResponseMessage;
import tech.binaryer.shjy.biz.entity.UserEntity;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author peijiayang
 * @since 2021-03-28
 */
public interface UserService extends IService<UserEntity> {

    Map login(UserDto userDto);

    ResponseMessage regUser(UserEntity userEntity);

}
