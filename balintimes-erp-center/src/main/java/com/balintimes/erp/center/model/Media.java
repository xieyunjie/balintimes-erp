package com.balintimes.erp.center.model;

import org.apache.ibatis.jdbc.Null;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/10.
 */
public class Media implements Serializable {
    private String uid;
    private int id;
    private String  kid;
    private String name;
    private String posdesc;
    private Boolean cansell;
    private String region;
    private String mediatypeuid;
    private String mediatypename;
    private String stationuid;
    private String stationname;
    private String trainuid;
    private String trainname;
    private String postypeuid;
    private String postypename;
    private String lineuid ;
    private String linename;
    private String mediastatusuid;
    private String mediastatusname;
    private Boolean errorflag;
    private double price;
    private String  image;
    private String  attachment;
    private String directionuid;
    private String directionname;
    private String leveluid;
    private String levelname;
    private Boolean deleted;
    private String  comment;
    private Date edittime;
    private String editorname;
    private String editorid;
    private Date createtime;
    private String creatorname;
    private String creatorid;
    private Date activedate;
    private float mediawidth;
    private float medialength;
    private int amount;
    private double contactrate;
    private int contactlevel;
    private double extendno;
    private Date expiredate;
    private int warnningflag;
    private String shieldingdoor;
    private Date begintime;
    private Date endtime;
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

    public String getLeveluid() {
        return leveluid;
    }

    public void setLeveluid(String leveluid) {
        this.leveluid = leveluid;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public String getMediatypename() {
        return mediatypename;
    }

    public void setMediatypename(String mediatypename) {
        this.mediatypename = mediatypename;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public String getTrainname() {
        return trainname;
    }

    public void setTrainname(String trainname) {
        this.trainname = trainname;
    }

    public String getPostypename() {
        return postypename;
    }

    public void setPostypename(String postypename) {
        this.postypename = postypename;
    }

    public String getLinename() {
        return linename;
    }

    public void setLinename(String linename) {
        this.linename = linename;
    }

    public String getMediastatusname() {
        return mediastatusname;
    }

    public void setMediastatusname(String mediastatusname) {
        this.mediastatusname = mediastatusname;
    }

    public String getDirectionname() {
        return directionname;
    }

    public void setDirectionname(String directionname) {
        this.directionname = directionname;
    }


    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

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

    public String getPosdesc() {
        return posdesc;
    }

    public void setPosdesc(String posdesc) {
        this.posdesc = posdesc;
    }

    public Boolean getCansell() {
        return cansell;
    }

    public void setCansell(Boolean cansell) {
        this.cansell = cansell;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMediatypeuid() {
        return mediatypeuid;
    }

    public void setMediatypeuid(String mediatypeuid) {
        this.mediatypeuid = mediatypeuid;
    }

    public String getStationuid() {
        return stationuid;
    }

    public void setStationuid(String stationuid) {
        this.stationuid = stationuid;
    }

    public String getTrainuid() {
        return trainuid;
    }

    public void setTrainuid(String trainuid) {
        this.trainuid = trainuid;
    }

    public String getPostypeuid() {
        return postypeuid;
    }

    public void setPostypeuid(String postypeuid) {
        this.postypeuid = postypeuid;
    }

    public String getLineuid() {
        return lineuid;
    }

    public void setLineuid(String lineuid) {
        this.lineuid = lineuid;
    }

    public String getMediastatusuid() {
        return mediastatusuid;
    }

    public void setMediastatusuid(String mediastatusuid) {
        this.mediastatusuid = mediastatusuid;
    }

    public Boolean getErrorflag() {
        return errorflag;
    }

    public void setErrorflag(Boolean errorflag) {
        this.errorflag = errorflag;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDirectionuid() {
        return directionuid;
    }

    public void setDirectionuid(String directionuid) {
        this.directionuid = directionuid;
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

    public Date getActivedate() {
        return activedate;
    }

    public void setActivedate(Date activedate) {
        this.activedate = activedate;
    }

    public float getMediawidth() {
        return mediawidth;
    }

    public void setMediawidth(float mediawidth) {
        this.mediawidth = mediawidth;
    }

    public float getMedialength() {
        return medialength;
    }

    public void setMedialength(float medialength) {
        this.medialength = medialength;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getContactrate() {
        return contactrate;
    }

    public void setContactrate(double contactrate) {
        this.contactrate = contactrate;
    }

    public int getContactlevel() {
        return contactlevel;
    }

    public void setContactlevel(int contactlevel) {
        this.contactlevel = contactlevel;
    }

    public double getExtendno() {
        return extendno;
    }

    public void setExtendno(double extendno) {
        this.extendno = extendno;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    public int getWarnningflag() {
        return warnningflag;
    }

    public void setWarnningflag(int warnningflag) {
        this.warnningflag = warnningflag;
    }

    public String getShieldingdoor() {
        return shieldingdoor;
    }

    public void setShieldingdoor(String shieldingdoor) {
        this.shieldingdoor = shieldingdoor;
    }
}
