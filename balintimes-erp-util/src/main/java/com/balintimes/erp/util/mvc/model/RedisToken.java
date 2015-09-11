package com.balintimes.erp.util.mvc.model;

import java.io.Serializable;

/**
 * Created by AlexXie on 2015/8/24.
 */
public class RedisToken implements Serializable {

    private String token = "";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
