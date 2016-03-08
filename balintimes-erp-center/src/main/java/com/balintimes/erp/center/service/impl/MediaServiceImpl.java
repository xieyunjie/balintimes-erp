package com.balintimes.erp.center.service.impl;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.MediaDao;
import com.balintimes.erp.center.model.Media;
import com.balintimes.erp.center.service.MediaService;
import com.balintimes.erp.center.tuples.TupleResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/11.
 */

@Service("mediaService")
public class MediaServiceImpl implements MediaService {
    @Resource
    private MediaDao mediaDao;


    public List<Media> GetMediaListByCondition(Map<String, Object> params) {
        return mediaDao.GetMediaListByCondition(params);
    }


    public Media GetOneMedia(String uid) {
        return mediaDao.GetOneMedia(uid);
    }

    @CustomerTransactional
    public TupleResult<Boolean, Object> UpdateMedia(Media media) {
        try {
            Boolean isUpdated = mediaDao.UpdateMedia(media);
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
    public TupleResult<Boolean, Object> DeleteMedia(String uid) {
        try {
            Boolean isDeleted = mediaDao.DeleteMedia(uid);
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
    public TupleResult<Boolean, Object> CreateMedia(Media media) {
        try {
            Boolean isCreated = mediaDao.CreateMedia(media);
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
