package com.balintimes.erp.model.crm;

/**
 * Created by AlexXie on 2015/9/15.
 */
public class BusinessType {

    private String uid;
    private String name;
    private String code;
    private int priority;
    private String comment;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BusinessType{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", priority=" + priority +
                ", comment='" + comment + '\'' +
                '}';
    }
}
