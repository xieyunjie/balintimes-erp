package com.balintimes.erp.center.dao.impl;

import com.balintimes.erp.center.dao.MediaDao;
import com.balintimes.erp.center.mappers.MediaMapper;
import com.balintimes.erp.center.model.Media;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/11.
 */
@Repository("mediaDao")
public class MediaDaoImpl implements MediaDao {
    @Resource
    private MediaMapper mediaMapper;

    public List<Media> GetMediaListByCondition(Map<String, Object> params) {
        try {
            return mediaMapper.GetMediaListByCondition(params);
        }catch (Exception err){
            throw  err;
        }
    }

    public Media GetOneMedia(String uid) {
        return mediaMapper.GetOneMedia(uid);
    }



    public boolean UpdateMedia(Media media) {
        return mediaMapper.UpdateMedia(media);
    }


    public boolean DeleteMedia(String uid) {
        return mediaMapper.DeleteMedia(uid);
    }


    public boolean CreateMedia(Media media) {
        return mediaMapper.CreateMedia(media);
    }



}
