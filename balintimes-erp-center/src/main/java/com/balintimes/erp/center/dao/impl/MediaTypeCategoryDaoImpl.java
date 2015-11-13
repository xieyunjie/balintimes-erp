package com.balintimes.erp.center.dao.impl;

import com.balintimes.erp.center.dao.MediaTypeCategoryDao;
import com.balintimes.erp.center.mappers.MediaTypeCategoryMapper;
import com.balintimes.erp.center.model.MediaTypeCategory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/5.
 */
@Repository("mediaTypeCategoryDao")
public class MediaTypeCategoryDaoImpl implements MediaTypeCategoryDao {
    @Resource
    private MediaTypeCategoryMapper mediaTypeCategoryMapper;


    public List<MediaTypeCategory> GetMediaTypeCategoryListByCondition(Map<String, Object> params) {
        return mediaTypeCategoryMapper.GetMediaTypeCategoryListByCondition(params);
    }


    public MediaTypeCategory GetOneMediaTypeCategory(String uid) {
        return mediaTypeCategoryMapper.GetOneMediaTypeCategory(uid);
    }


    public boolean UpdateMediaTypeCategory(MediaTypeCategory mediaTypeCategory) {
        return mediaTypeCategoryMapper.UpdateMediaTypeCategory(mediaTypeCategory);
    }


    public boolean DeleteMediaTypeCategory(String uid) {
        return mediaTypeCategoryMapper.DeleteMediaTypeCategory(uid);
    }


    public boolean CreateMediaTypeCategory(MediaTypeCategory mediaTypeCategory) {
        return mediaTypeCategoryMapper.CreateMediaTypeCategory(mediaTypeCategory);
    }
}
