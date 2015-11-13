package com.balintimes.erp.center.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/6.
 */
public class MediaType implements Serializable{
    private String uid;
    private int id;
    private String  kid;
    private String name;
    private double width;
    private double length;
    private String spec;
    private Boolean deleted;
    private String comment;
    private Date edittime;
    private String editorname;
    private String editorid;
    private Date createtime;
    private String creatorname;
    private String creatorid;
    private String mediatypecategoryuid;
    private String mediatypecategoryname;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public String getMediatypecategoryuid() {
        return mediatypecategoryuid;
    }

    public void setMediatypecategoryuid(String mediatypecategoryuid) {
        this.mediatypecategoryuid = mediatypecategoryuid;
    }

    public String getMediatypecategoryname() {
        return mediatypecategoryname;
    }

    public void setMediatypecategoryname(String mediatypecategoryname) {
        this.mediatypecategoryname = mediatypecategoryname;
    }
}
