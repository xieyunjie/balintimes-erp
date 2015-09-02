package com.balintimes.erp.util.spring;

import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.log.LogMethodModel;
import com.balintimes.erp.util.log.LogUtil;
import com.balintimes.erp.util.mvc.WebUser;
import com.balintimes.erp.util.mvc.WebUserUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class ServicesAspectAdvice {

    @Autowired(required = false)
    private HttpServletRequest request;

    private WebUserUtil webUserUtil;

    private String AppUID;
    private String AppName;

    public ServicesAspectAdvice() {
        // TODO Auto-generated constructor stub
    }

    public Object doAround(ProceedingJoinPoint jp) throws Throwable {

        Object o = null;

        LogMethodModel model = new LogMethodModel();

        model.setException("Exception");
        model.setAction(jp.toString());
        model.setMethod(jp.toString());
        model.setParams(JsonUtil.ToJson(jp.getArgs()));

        model.setAppuid(this.AppUID);
        model.setAppname(AppName);

        if (webUserUtil != null) {
            WebUser webUser = webUserUtil.getWebUser();
            if (webUser != null) {
                model.setUseruid(webUser.getUid());
                model.setUsername(webUser.getUsername());
                model.setEmployeeuid(webUser.getUid());
                model.setEmployeename(webUser.getEmployeeName());
            }
        }
        if (request != null) {

            model.setClientip(request.getRemoteAddr());
            model.setRequesturl(request.getRequestURL().toString());
        }

        LogUtil.recordServiceLog(model);

        o = jp.proceed();

        return o;
    }

    public WebUserUtil getWebUserUtil() {
        return webUserUtil;
    }

    public void setWebUserUtil(WebUserUtil webUserUtil) {
        this.webUserUtil = webUserUtil;
    }


    public String getAppUID() {
        return AppUID;
    }

    public void setAppUID(String appUID) {
        AppUID = appUID;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }
}
