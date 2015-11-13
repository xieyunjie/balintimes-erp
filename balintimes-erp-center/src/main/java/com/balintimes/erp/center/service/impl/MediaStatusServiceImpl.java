package com.balintimes.erp.center.service.impl;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.MediaStatusDao;
import com.balintimes.erp.center.model.MediaStatus;
import com.balintimes.erp.center.service.MediaStatusService;
import com.balintimes.erp.center.tuples.TupleResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/4.
 */
@Service("mediaStatusService")
public class MediaStatusServiceImpl implements MediaStatusService{
    @Resource
    private MediaStatusDao mediaStatusDao;

    public List<MediaStatus> GetMediaStatusListByCondition(Map<String, Object> params) {
        return mediaStatusDao.GetMediaStatusListByCondition(params);
    }


    public MediaStatus GetOneMediaStatus(String uid) {
        return mediaStatusDao.GetOneMediaStatus(uid);
    }

    @CustomerTransactional
    public TupleResult<Boolean, Object> UpdateMediaStatus(MediaStatus mediaStatus) {
        try {
            Boolean isUpdated = mediaStatusDao.UpdateMediaStatus(mediaStatus);
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
    public TupleResult<Boolean, Object> DeleteMediaStatus(String uid) {
        try {
            Boolean isDeleted = mediaStatusDao.DeleteMediaStatus(uid);
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
    public TupleResult<Boolean, Object> CreateMediaStatus(MediaStatus mediaStatus) {
        try {
            Boolean isCreated = mediaStatusDao.CreateMediaStatus(mediaStatus);
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
