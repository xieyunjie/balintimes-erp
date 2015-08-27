package com.balintimes.erp.util.mvc;

import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.redis.RedisUserUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;

/**
 * Created by AlexXie on 2015/8/24.
 */
public class MvcHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource
    private RedisUserUtil redisUserUtil;

    public boolean supportsParameter(MethodParameter methodParameter) {

        Annotation[] as = methodParameter.getParameterAnnotations();
        for (Annotation a : as) {
            if (a.annotationType() == MvcExModel.class) {
                Class<?> clazz = methodParameter.getParameterType();
                if (clazz.isAssignableFrom(MvcRUID.class) || clazz.isAssignableFrom(MvcWebUser.class)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        Class<?> clazz = methodParameter.getParameterType();
        if (clazz.isAssignableFrom(MvcRUID.class)) {
            MvcRUID sessionID = new MvcRUID();
            sessionID.setRuid(nativeWebRequest.getHeader("nodejs-sessionid"));
            return sessionID;
        } else if (clazz.isAssignableFrom(MvcWebUser.class)) {
            MvcWebUser mvcWebUser = JsonUtil.ToObject(redisUserUtil.GetRedisWebUser(nativeWebRequest.getHeader("nodejs-sessionid")), MvcWebUser.class);
            return mvcWebUser;
        }
        return null;
    }
}
