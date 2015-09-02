package com.balintimes.erp.util.log;

/**
 * Created by AlexXie on 2015/9/2.
 */
public class LogMethodModel {

    private String action = "";
    private String method = "";
    private String appuid = "";
    private String appname = "";
    private String useruid = "";
    private String username = "";
    private String employeeuid = "";
    private String employeename = "";
    private String params = "";

    private String message = "";

    private String exception = "";

    private String clientip = "";
    private String requesturl = "";


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAppuid() {
        return appuid;
    }

    public void setAppuid(String appuid) {
        this.appuid = appuid;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getUseruid() {
        return useruid;
    }

    public void setUseruid(String useruid) {
        this.useruid = useruid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmployeeuid() {
        return employeeuid;
    }

    public void setEmployeeuid(String employeeuid) {
        this.employeeuid = employeeuid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public String getRequesturl() {
        return requesturl;
    }

    public void setRequesturl(String requesturl) {
        this.requesturl = requesturl;
    }

    @Override
    public String toString() {
        return "LogMethodModel{" +
                "action='" + action + '\'' +
                ", method='" + method + '\'' +
                ", appuid='" + appuid + '\'' +
                ", appname='" + appname + '\'' +
                ", useruid='" + useruid + '\'' +
                ", username='" + username + '\'' +
                ", employeeuid='" + employeeuid + '\'' +
                ", employeename='" + employeename + '\'' +
                ", params='" + params + '\'' +
                ", message='" + message + '\'' +
                ", exception='" + exception + '\'' +
                '}';
    }
}
