package com.balintimes.erp.workflow.model;

import java.io.Serializable;

/**
 * Created by AlexXie on 2015/10/12.
 */
public class TaskUserLink implements Serializable {
    private static final long serialVersionUID = 5061436597851183850L;

    // linkId 为空，api没有相应取id的方法
    private String linkId;
    private String linkType;

    private String userUID;

    public TaskUserLink(String linkId, String linkType, String userUID) {
        this.linkId = linkId;
        this.linkType = linkType;
        this.userUID = userUID;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }
}
