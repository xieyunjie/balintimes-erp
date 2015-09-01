package com.balintimes.erp.util.mvc;

import com.balintimes.erp.util.exception.CurrentUserExpireException;
import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.redis.RedisUserUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;

/**
 * Created by AlexXie on 2015/8/24.
 */
public class CtrlMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource
    private RedisUserUtil redisUserUtil;

    public boolean supportsParameter(MethodParameter methodParameter) {

        Annotation[] as = methodParameter.getParameterAnnotations();
        for (Annotation a : as) {
            if (a.annotationType() == MvcModel.class) {
                Class<?> clazz = methodParameter.getParameterType();
                if (clazz.isAssignableFrom(Ruid.class) || clazz.isAssignableFrom(WebUser.class)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        Class<?> clazz = methodParameter.getParameterType();
        if (clazz.isAssignableFrom(Ruid.class)) {
            Ruid sessionID = new Ruid();
            sessionID.setRuid(nativeWebRequest.getHeader("redissessionid"));
            return sessionID;
        } else if (clazz.isAssignableFrom(WebUser.class)) {
            String userStr = redisUserUtil.GetRedisWebUser(nativeWebRequest.getHeader("redissessionid"));

            if(userStr == null){

                throw  new CurrentUserExpireException(nativeWebRequest.getHeader("redissessionid"));
            }

            WebUser webUser = JsonUtil.ToObject(userStr, WebUser.class);
            return webUser;
        }
        return WebArgumentResolver.UNRESOLVED;
    }
}
