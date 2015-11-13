package com.balintimes.erp.center.dao.impl;

import com.balintimes.erp.center.dao.MediaStatusDao;
import com.balintimes.erp.center.mappers.MediaStatusMapper;
import com.balintimes.erp.center.model.MediaStatus;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/4.
 */
@Repository("mediaStatusDao")
public class MediaStatusDaoImpl implements MediaStatusDao {
    @Resource
    private MediaStatusMapper mediaStatusMapper;
    
    
    public List<MediaStatus> GetMediaStatusListByCondition(Map<String, Object> params) {
        return mediaStatusMapper.GetMediaStatusListByCondition(params);
    }

   
    public MediaStatus GetOneMediaStatus(String uid) {
        return mediaStatusMapper.GetOneMediaStatus(uid);
    }

   
    public boolean UpdateMediaStatus(MediaStatus mediaStatus) {
        return mediaStatusMapper.UpdateMediaStatus(mediaStatus);
    }

   
    public boolean DeleteMediaStatus(String uid) {
        return mediaStatusMapper.DeleteMediaStatus(uid);
    }

   
    public boolean CreateMediaStatus(MediaStatus mediaStatus) {
        return mediaStatusMapper.CreateMediaStatus(mediaStatus);
    }
}
