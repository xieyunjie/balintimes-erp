package com.balintimes.erp.util.mvc.argumentresolver;

import com.balintimes.erp.util.exception.CurrentUserExpireException;
import com.balintimes.erp.util.mvc.model.Ruid;
import com.balintimes.erp.util.mvc.model.WebUser;
import com.balintimes.erp.util.mvc.annon.MvcModel;
import com.balintimes.erp.util.mvc.util.WebUserUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;

/**
 * Created by AlexXie on 2015/8/24.
 */
public class CtrlMethodArgumentResolver implements HandlerMethodArgumentResolver {


    private WebUserUtil webUserUtil;

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
//            Ruid sessionID = new Ruid();
//            sessionID.setRuid(nativeWebRequest.getHeader("redissessionid"));
//            return sessionID;
            return webUserUtil.getUniqueID();
        } else if (clazz.isAssignableFrom(WebUser.class)) {

            WebUser webUser = webUserUtil.getWebUser();
            if (webUser == null) throw new CurrentUserExpireException(nativeWebRequest.getHeader("redissessionid"));

            return webUser;
        }
        return WebArgumentResolver.UNRESOLVED;
    }


    public WebUserUtil getWebUserUtil() {
        return webUserUtil;
    }

    public void setWebUserUtil(WebUserUtil webUserUtil) {
        this.webUserUtil = webUserUtil;
    }
}
