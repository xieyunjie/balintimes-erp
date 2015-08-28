package com.balintimes.erp.crm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by AlexXie on 2015/8/26.
 */
public class Line implements Serializable {

    private String uid;
    private String name;
    private Date createtime;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return uid + " ## " + name + " ## " + createtime;
    }
}
