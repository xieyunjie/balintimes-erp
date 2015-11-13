package com.balintimes.erp.center.dao.impl;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.MediaTypeDao;
import com.balintimes.erp.center.mappers.MediaTypeMapper;
import com.balintimes.erp.center.model.MediaType;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/6.
 */
@Repository("mediaTypeDao")
public class MediaTypeDaoImpl implements MediaTypeDao {
    @Resource
    private MediaTypeMapper mediaTypeMapper;
    
    public List<MediaType> GetMediaTypeListByCondition(Map<String, Object> params) {
        try {
//            return mediaTypeMapper.GetMediaTypeListByCondition(params);
            return mediaTypeMapper.GetMediaTypeListByProcedure(params);
        }
        catch (Exception err)
        {
            throw err;
        }
    }

    
    public MediaType GetOneMediaType(String uid) {
        return mediaTypeMapper.GetOneMediaType(uid);
    }


    public boolean UpdateMediaType(MediaType mediaType) {
        return mediaTypeMapper.UpdateMediaType(mediaType);
    }


    public boolean DeleteMediaType(String uid) {
        return mediaTypeMapper.DeleteMediaType(uid);
    }


    public boolean CreateMediaType(MediaType mediaType) {
        return mediaTypeMapper.CreateMediaType(mediaType);
    }
}
