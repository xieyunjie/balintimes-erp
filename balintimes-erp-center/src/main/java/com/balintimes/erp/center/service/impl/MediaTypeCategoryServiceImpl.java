package com.balintimes.erp.center.service.impl;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.MediaTypeCategoryDao;
import com.balintimes.erp.center.model.MediaTypeCategory;
import com.balintimes.erp.center.service.MediaTypeCategoryService;
import com.balintimes.erp.center.tuples.TupleResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/5.
 */
@Service("mediaTypeCategoryService")
public class MediaTypeCategoryServiceImpl implements MediaTypeCategoryService {
    @Resource
    private MediaTypeCategoryDao mediaTypeCategoryDao;


    public List<MediaTypeCategory> GetMediaTypeCategoryListByCondition(Map<String, Object> params) {
        return mediaTypeCategoryDao.GetMediaTypeCategoryListByCondition(params);
    }


    public MediaTypeCategory GetOneMediaTypeCategory(String uid) {
        return mediaTypeCategoryDao.GetOneMediaTypeCategory(uid);
    }

    @CustomerTransactional
    public TupleResult<Boolean, Object> UpdateMediaTypeCategory(MediaTypeCategory mediaTypeCategory) {
        try {
            Boolean isUpdated = mediaTypeCategoryDao.UpdateMediaTypeCategory(mediaTypeCategory);
            TupleResult<Boolean,Object> tupleResult;
            if(isUpdated==true)
                tupleResult=new TupleResult<Boolean, Object>(isUpdated,"success");
            else
                tupleResult=new TupleResult<Boolean, Object>(isUpdated,"failure");

            return tupleResult;
        }
        catch (Exception err){
            TupleResult<Boolean,Object> tupleResult=new TupleResult<Boolean, Object>(false,err.getMessage());
            return tupleResult;
        }
    }

    @CustomerTransactional
    public TupleResult<Boolean, Object> DeleteMediaTypeCategory(String uid) {
        try {
            Boolean isDeleted = mediaTypeCategoryDao.DeleteMediaTypeCategory(uid);
            TupleResult<Boolean,Object> tupleResult;
            if(isDeleted==true)
                tupleResult=new TupleResult<Boolean, Object>(isDeleted,"success");
            else
                tupleResult=new TupleResult<Boolean, Object>(isDeleted,"failure");

            return tupleResult;
        }
        catch (Exception err){
            TupleResult<Boolean,Object> tupleResult=new TupleResult<Boolean, Object>(false,err.getMessage());
            return tupleResult;
        }
    }

    @CustomerTransactional
    public TupleResult<Boolean, Object> CreateMediaTypeCategory(MediaTypeCategory mediaTypeCategory) {
        try {
            Boolean isCreated = mediaTypeCategoryDao.CreateMediaTypeCategory(mediaTypeCategory);
            TupleResult<Boolean,Object> tupleResult;
            if(isCreated==true)
                tupleResult=new TupleResult<Boolean, Object>(isCreated,"success");
            else
                tupleResult=new TupleResult<Boolean, Object>(isCreated,"failure");

            return tupleResult;
        }
        catch (Exception err){
            TupleResult<Boolean,Object> tupleResult=new TupleResult<Boolean, Object>(false,err.getMessage());
            return tupleResult;
        }
    }
}
