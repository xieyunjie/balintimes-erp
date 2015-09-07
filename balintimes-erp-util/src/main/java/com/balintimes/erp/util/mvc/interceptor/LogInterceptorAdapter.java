package com.balintimes.erp.util.mvc.interceptor;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class LogInterceptorAdapter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println(request.getProtocol());
        Enumeration<String> headerNames = request.getHeaderNames();
        for (Enumeration e = headerNames; e.hasMoreElements(); ) {

            String thisName = e.nextElement().toString();
            String thisValue = request.getHeader(thisName);
            System.out.println(thisName + " ## " + thisValue);
        }
        return true;
    }
}
