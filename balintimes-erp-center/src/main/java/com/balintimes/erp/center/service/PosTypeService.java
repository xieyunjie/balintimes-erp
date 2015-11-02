package com.balintimes.erp.center.service;

import com.balintimes.erp.center.model.PosType;
import com.balintimes.erp.center.tuples.TupleResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/29.
 */
public interface PosTypeService {
    List<PosType> GetPosTypeListByCondition(Map<String,Object> params);

    PosType GetOnePosType(String uid);

    TupleResult<Boolean,Object> UpdatePosType(PosType posType);

    TupleResult<Boolean,Object> DeletePosType(String uid);

    TupleResult<Boolean,Object> CreatePosType(PosType posType);
}
