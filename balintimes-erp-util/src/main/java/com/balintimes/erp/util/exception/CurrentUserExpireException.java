package com.balintimes.erp.util.exception;

/**
 * Created by AlexXie on 2015/8/31.
 */
public class CurrentUserExpireException extends RuntimeException {

    public CurrentUserExpireException(String message) {
        super(message);
    }
}
