package com.balintimes.erp.center.dao;

import com.balintimes.erp.center.model.PosType;
import com.balintimes.erp.center.tuples.TupleResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/29.
 */
public interface PosTypeDao {
    List<PosType> GetPosTypeListByCondition(Map<String,Object> params);

    PosType GetOnePosType(String uid);

    boolean UpdatePosType(PosType posType);

    boolean DeletePosType(String uid);

    boolean CreatePosType(PosType posType);
}
