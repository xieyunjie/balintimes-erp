package com.balintimes.erp.util.mvc.handler;

import com.balintimes.erp.util.exception.AuthPermissionException;
import com.balintimes.erp.util.exception.CurrentUserExpireException;
import com.balintimes.erp.util.json.AjaxResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AlexXie on 2015/8/25.
 */
@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler({AuthPermissionException.class})
    @ResponseBody
    public AjaxResponse processUnAuthPermissionException(NativeWebRequest request, AuthPermissionException e) {

        HttpServletResponse response = (HttpServletResponse) request.getNativeResponse();
        HttpServletRequest req = (HttpServletRequest) request.getNativeRequest();

        System.out.println(req.getRequestURL());

        try {
            boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
            String ajaxFlag = null == request.getParameter("ajax") ? "false" : request.getParameter("ajax");
            boolean isAjax = ajax || ajaxFlag.equalsIgnoreCase("true");

            //
            if (isAjax) {
                System.out.println("AuthPermissionException AuthPermissionException AuthPermissionException AuthPermissionException");

                AjaxResponse ajaxResponse = new AjaxResponse();
                ajaxResponse.setSuccess("false");
                ajaxResponse.setAuthenicated(false);
                ajaxResponse.setResponseMsg("你没有当前操作权限(40001)，请联系管理员。");
                ajaxResponse.setHttpStatus(40001);
                return ajaxResponse;


            } else {
//                WebUtils.response(req, response, "/login");
                response.sendRedirect("/login");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new AjaxResponse();
    }

    @ExceptionHandler({CurrentUserExpireException.class})
    @ResponseBody
    public AjaxResponse processUnAuthPermissionException(NativeWebRequest request, CurrentUserExpireException e) {

        HttpServletResponse response = (HttpServletResponse) request.getNativeResponse();
        HttpServletRequest req = (HttpServletRequest) request.getNativeRequest();

        System.out.println(req.getRequestURL());

        try {
            boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
            String ajaxFlag = null == request.getParameter("ajax") ? "false" : request.getParameter("ajax");
            boolean isAjax = ajax || ajaxFlag.equalsIgnoreCase("true");

            //
            if (isAjax) {
                System.out.println("CurrentUserExpireException CurrentUserExpireException CurrentUserExpireException CurrentUserExpireException");

                AjaxResponse ajaxResponse = new AjaxResponse();
                ajaxResponse.setSuccess("false");
                ajaxResponse.setAuthenicated(false);
                ajaxResponse.setResponseMsg("当前访问过期(70001)，请重新登录。");
                ajaxResponse.setHttpStatus(70001);
                return ajaxResponse;

            } else {
//                WebUtils.response(req, response, "/login");
                response.sendRedirect("/login");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new AjaxResponse();
    }

}
