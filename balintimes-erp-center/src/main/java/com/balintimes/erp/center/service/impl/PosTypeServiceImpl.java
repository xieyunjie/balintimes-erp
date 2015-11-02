package com.balintimes.erp.center.service.impl;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.PosTypeDao;
import com.balintimes.erp.center.model.PosType;
import com.balintimes.erp.center.service.PosTypeService;
import com.balintimes.erp.center.tuples.TupleResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/29.
 */
@Service("postTypeService")
public class PosTypeServiceImpl implements PosTypeService {
    @Resource
    private PosTypeDao posTypeDao;

    public List<PosType> GetPosTypeListByCondition(Map<String, Object> params) {
        return posTypeDao.GetPosTypeListByCondition(params);
    }

    
    public PosType GetOnePosType(String uid) {
        return posTypeDao.GetOnePosType(uid);
    }

    @CustomerTransactional
    public TupleResult<Boolean, Object> UpdatePosType(PosType posType) {
        try {
            Boolean isUpdated = posTypeDao.UpdatePosType(posType);
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
    public TupleResult<Boolean, Object> DeletePosType(String uid) {
        try {
            Boolean isDeleted = posTypeDao.DeletePosType(uid);
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
    public TupleResult<Boolean, Object> CreatePosType(PosType posType) {
        try {
            Boolean isCreated = posTypeDao.CreatePosType(posType);
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
