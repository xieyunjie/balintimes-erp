package com.balintimes.erp.util.mvc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexXie on 2015/9/6.
 */
public interface PermittedUtil {

    boolean isPermitted(HttpServletRequest request, HttpServletResponse response, String permissionValue);
}
