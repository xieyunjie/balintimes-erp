package com.balintimes.erp.util.mvc.model;

import java.io.Serializable;

/**
 * Created by AlexXie on 2015/9/17.
 */
public class Role implements Serializable {

    private static final long serialVersionUID = -5155883977085619447L;

    private  String uid;
    private  String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
