package com.balintimes.erp.util.mvc;


import com.balintimes.erp.util.exception.AuthPermissionException;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

public class MvcPermissionInterceptorAdapter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long time = new java.util.Date().getTime();

        if (handler instanceof DefaultServletHttpRequestHandler) {
            HttpRequestHandler h = (DefaultServletHttpRequestHandler) handler;
            return true;
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod handler2 = (HandlerMethod) handler;

            Annotation an = handler2.getMethodAnnotation(MvcHasPermissions.class);
            if (an != null) {
                String[] permissions = handler2.getMethodAnnotation(MvcHasPermissions.class).value();
            }

            boolean isPermissioin = true;


            if (isPermissioin) {
                return true;
            } else {
                throw new AuthPermissionException("UnAuth");
            }
        }

        return false;
    }
}
