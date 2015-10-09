package com.balintimes.erp.workflow.exception;

/**
 * Created by AlexXie on 2015/10/9.
 */
public class ExecutException  extends Exception {


    private Object exceptionDetail;

    public ExecutException(String message,Object detail) {
        super(message);

        this.exceptionDetail = detail;
    }

    public Object getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(Object exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }
}
