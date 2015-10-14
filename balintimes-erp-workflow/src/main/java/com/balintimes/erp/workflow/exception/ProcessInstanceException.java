package com.balintimes.erp.workflow.exception;

/**
 * Created by AlexXie on 2015/10/12.
 */
public class ProcessInstanceException extends Exception {

    private Object detail;
    public ProcessInstanceException(String message,Object detail) {
        super(message);

        this.detail = detail;
    }

    public Object getDetail() {
        return detail;
    }
}
