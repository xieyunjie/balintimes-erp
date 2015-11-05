package com.balintimes.erp.center.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/3.
 */
public class Direction implements Serializable{
    private String uid;
    private String  kid;
    private String name;
    private Boolean deleted;
    private String comment;
    private Date edittime;
    private String editorname;
    private String editorid;
    private Date createtime;
    private String creatorname;
    private String creatorid;
    private String lineuid;
    private String linename;
    private String cityuid;
    private String cityname;

    public String getCityuid() {
        return cityuid;
    }

    public void setCityuid(String cityuid) {
        this.cityuid = cityuid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getLinename() {
        return linename;
    }

    public void setLinename(String linename) {
        this.linename = linename;
    }

    private String postypeuid;
    private String postypename;

    public String getPostypename() {
        return postypename;
    }

    public void setPostypename(String postypename) {
        this.postypename = postypename;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public String getEditorname() {
        return editorname;
    }

    public void setEditorname(String editorname) {
        this.editorname = editorname;
    }

    public String getEditorid() {
        return editorid;
    }

    public void setEditorid(String editorid) {
        this.editorid = editorid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreatorname() {
        return creatorname;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public String getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid;
    }

    public String getLineuid() {
        return lineuid;
    }

    public void setLineuid(String lineuid) {
        this.lineuid = lineuid;
    }

    public String getPostypeuid() {
        return postypeuid;
    }

    public void setPostypeuid(String postypeuid) {
        this.postypeuid = postypeuid;
    }
}
