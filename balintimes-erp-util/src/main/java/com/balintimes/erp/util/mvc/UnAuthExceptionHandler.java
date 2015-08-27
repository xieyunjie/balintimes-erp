package com.balintimes.erp.util.mvc;

import com.balintimes.erp.util.exception.AuthPermissionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created by AlexXie on 2015/8/25.
 */
public class UnAuthExceptionHandler {

    @ExceptionHandler({AuthPermissionException.class})
    @ResponseBody
    public String processUnAuthPermissionException(NativeWebRequest request, AuthPermissionException e) {

        return  "";
//
//        ShiroHttpServletResponse response = (ShiroHttpServletResponse) request.getNativeResponse();
//        ShiroHttpServletRequest req = (ShiroHttpServletRequest) request.getNativeRequest();
//
//        System.out.println(req.getRequestURL());
//
//        try {
//            boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
//            String ajaxFlag = null == request.getParameter("ajax") ? "false" : request.getParameter("ajax");
//            boolean isAjax = ajax || ajaxFlag.equalsIgnoreCase("true");
//
//            // WebUtils.issueRedirect(request, response, this.getLoginUrl());
//            //
//            if (isAjax) {
//                System.out.println("AuthPermissionException AuthPermissionException AuthPermissionException AuthPermissionException");
//
//                AjaxResponse jsonResponseMsg = new AjaxResponse();
//                jsonResponseMsg.setResponseMsg("��ǰ������Ҫ��Ȩ(40001)������ϵ����Ա��");
//
//                return JsonUtil.ToJson(jsonResponseMsg);
//
//            } else {
//                WebUtils.issueRedirect(req, response, "/login/unpermission");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return "";
    }
}
