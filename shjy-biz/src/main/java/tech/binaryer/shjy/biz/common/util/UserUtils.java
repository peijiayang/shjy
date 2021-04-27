package tech.binaryer.shjy.biz.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {

    /**
     * 根据token查询用户id
     * @return
     */

    public static int getUserId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        System.out.println(SignParams.SIGN_JWC);

        Integer userId = Integer.parseInt(JwtUtils.parseJWT(SignParams.SIGN_JWC, request.getHeader(SignParams.TOKEN_NAME)).get("userId").toString());
        return userId;
    }
}
