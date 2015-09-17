package com.balintimes.erp.util.mvc.interceptor;


import com.balintimes.erp.util.exception.AuthPermissionException;
import com.balintimes.erp.util.mvc.annon.HasPermissions;
import com.balintimes.erp.util.mvc.util.PermittedUtil;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

public class PermissionInterceptorAdapter extends HandlerInterceptorAdapter {

    private PermittedUtil permittedUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (handler instanceof DefaultServletHttpRequestHandler) {
            HttpRequestHandler h = (DefaultServletHttpRequestHandler) handler;
            return true;
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod handler2 = (HandlerMethod) handler;

            Annotation an = handler2.getMethodAnnotation(HasPermissions.class);
            boolean isPermissioin = true;
            if (an != null) {
                String permissions = handler2.getMethodAnnotation(HasPermissions.class).value();
                if (permissions != null && "".equals(permissions) == false) {

                    if (!permittedUtil.isPermitted(request, response, permissions)) {
                        isPermissioin = false;
                    }
                }
            }

            if (isPermissioin) {
                return true;
            } else {
                throw new AuthPermissionException("UnAuth");
            }
        }

        return false;
    }

    public PermittedUtil getPermittedUtil() {
        return permittedUtil;
    }

    public void setPermittedUtil(PermittedUtil permittedUtil) {
        this.permittedUtil = permittedUtil;
    }
}
