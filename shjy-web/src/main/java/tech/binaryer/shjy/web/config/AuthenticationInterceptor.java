package tech.binaryer.shjy.web.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tech.binaryer.shjy.biz.common.annotation.PassToken;
import tech.binaryer.shjy.biz.common.annotation.UserLoginToken;
import tech.binaryer.shjy.biz.entity.UserEntity;
import tech.binaryer.shjy.biz.mapper.UserMapper;
import tech.binaryer.shjy.biz.common.util.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 拦截器
 *
 * @author qiaoyn
 * @date 2019/06/14
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        if (method.getDeclaringClass().isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getDeclaringClass().getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                if (token == null||token.trim()=="") {
                    httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN,"未携带token信息！");

                    return false;
                }

                String userName;
                try {
                    userName = Objects.toString(JwtUtils.parseJWT("ZHIJIAPINGTAI", token).get("username"));
                    System.out.println("==============" + userName);
                } catch (Exception j) {
                    httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN,"鉴权失败，请重新登录U+平台！");

                    return false;
                }
                LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper();
                lambdaQueryWrapper.eq(UserEntity::getUserName, userName);
                UserEntity user = userMapper.selectOne(lambdaQueryWrapper);
                if (user == null) {
                    httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN,"鉴权失败，请重新登录U+平台！");

                    return false;
                }

            }
        }
        return true;


        //}
        //  }
        //   return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

/*
package tech.binaryer.shjy.biz.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tech.binaryer.shjy.biz.annotation.PassToken;
import tech.binaryer.shjy.biz.annotation.UserLoginToken;
import tech.binaryer.shjy.biz.dto.UserDto;
import tech.binaryer.shjy.biz.entity.UserEntity;
import tech.binaryer.shjy.biz.exception.BusinessException;
import tech.binaryer.shjy.biz.mapper.UserMapper;
import tech.binaryer.shjy.biz.util.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 拦截器
 *
 * @author qiaoyn
 * @date 2019/06/14
 */
/*public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解HandlerMethod.getMethod().getDeclaringClass().getAnnotation(注解名称.class)
        if (method.getDeclaringClass().isAnnotationPresent(UserLoginToken.class)) {
            //UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            UserLoginToken userLoginToken = method.getDeclaringClass().getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String userName;
                try {
                    userName = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper();
                lambdaQueryWrapper.eq(UserEntity::getUserName, userName);
                UserEntity user = userMapper.selectOne(lambdaQueryWrapper);
                System.out.println("------------------------------------------------");
                System.out.println(user);
                System.out.println("------------------------------------------------");
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassWord())).build();
                try {
                    System.out.println("===================================================");
                    System.out.println(token);
                    System.out.println("===================================================");
                    jwtVerifier.verify(token);
                    return true;
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}*/


