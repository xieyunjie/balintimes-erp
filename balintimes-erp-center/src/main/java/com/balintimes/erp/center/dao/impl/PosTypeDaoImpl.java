package com.balintimes.erp.center.dao.impl;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.PosTypeDao;
import com.balintimes.erp.center.mappers.PosTypeMapper;
import com.balintimes.erp.center.model.PosType;
import com.balintimes.erp.center.tuples.TupleResult;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/29.
 */
@Repository("posTypeDao")
public class PosTypeDaoImpl implements PosTypeDao {
    @Resource
    private PosTypeMapper posTypeMapper;

    public List<PosType> GetPosTypeListByCondition(Map<String, Object> params) {
        return posTypeMapper.GetPosTypeListByCondition(params);
    }


    public PosType GetOnePosType(String uid) {
        return posTypeMapper.GetOnePosType(uid);
    }

    public boolean UpdatePosType(PosType posType) {
        Boolean isUpdated = posTypeMapper.UpdatePosType(posType);
        return  isUpdated;
    }

    public boolean DeletePosType(String uid) {
        boolean isDeleted = posTypeMapper.DeletePosType(uid);
        return isDeleted;
    }

    public boolean CreatePosType(PosType posType) {
        boolean isCreated = posTypeMapper.CreatePosType(posType);
        return isCreated;
    }
}
