package com.balintimes.erp.center.service.impl;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.MediaTypeDao;
import com.balintimes.erp.center.model.MediaType;
import com.balintimes.erp.center.service.MediaTypeService;
import com.balintimes.erp.center.tuples.TupleResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/6.
 */
@Service("mediaTypeService")
public class MediaTypeServiceImpl implements MediaTypeService {
    @Resource
    private MediaTypeDao mediaTypeDao;


    public List<MediaType> GetMediaTypeListByCondition(Map<String, Object> params) {
        return mediaTypeDao.GetMediaTypeListByCondition(params);
    }


    public MediaType GetOneMediaType(String uid) {
        return mediaTypeDao.GetOneMediaType(uid);
    }

    @CustomerTransactional
    public TupleResult<Boolean,Object> UpdateMediaType(MediaType mediaType) {
        try {
            Boolean isUpdated = mediaTypeDao.UpdateMediaType(mediaType);
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
    public TupleResult<Boolean,Object> DeleteMediaType(String uid) {
        try {
            Boolean isDeleted = mediaTypeDao.DeleteMediaType(uid);
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
    public TupleResult<Boolean,Object> CreateMediaType(MediaType mediaType) {
        try {
            Boolean isCreated = mediaTypeDao.CreateMediaType(mediaType);
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
