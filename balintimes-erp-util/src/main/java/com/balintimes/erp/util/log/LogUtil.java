package com.balintimes.erp.util.log;

import com.balintimes.erp.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Created by AlexXie on 2015/9/2.
 */
public class LogUtil {

    private static Logger adviceLog = LoggerFactory.getLogger("ServicesAspectAdvice");
    private static Logger dbLog = LoggerFactory.getLogger("LogUtil.DBInfo");
    private static Logger consoleLog = LoggerFactory.getLogger("LogUtil.ConsoleInfo");

    public static void recordServiceLog(LogMethodModel model) {

        MDC.put("Exception", model.getException());
        MDC.put("Action", model.getAction());
        MDC.put("Method", model.getMethod());
        MDC.put("AppUid", model.getAppuid());
        MDC.put("AppName", model.getAppname());
        MDC.put("UserUid", model.getUseruid());
        MDC.put("UserName", model.getUsername());
        MDC.put("EmpUid", model.getEmployeeuid());
        MDC.put("EmpName", model.getEmployeename());
        MDC.put("Params", model.getParams());
        MDC.put("ClientIp", model.getClientip());
        MDC.put("RequestUrl", model.getRequesturl());

        adviceLog.info(model.getMessage());
        MDC.clear();
    }

    public static void ConsoleInfo(String msg) {

        consoleLog.info(msg);
    }


    public static void DBInfo(String msg, String method, String action, Object... params) {

        MDC.put("Exception", "Exception");
        MDC.put("Action", action);

        MDC.put("Method", method);
        MDC.put("ClientIp", "ClientIp");
        MDC.put("RequestUrl", "RequestUrl");
        MDC.put("AppUid", "AppUid");
        MDC.put("AppName", "AppName");
        MDC.put("UserUid", "UserUid");
        MDC.put("UserName", "UserName");
        MDC.put("EmpUid", "EmpUid");
        MDC.put("EmpName", "EmpName");
        MDC.put("Params", JsonUtil.ToJson(params));
        dbLog.info(msg);
        MDC.clear();

    }

}
