package com.balintimes.erp.center.mappers;

import com.balintimes.erp.center.model.PosType;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/29.
 */
public interface PosTypeMapper {
    List<PosType> GetPosTypeListByCondition(Map<String,Object> params);

    PosType GetOnePosType(String uid);

    boolean UpdatePosType(PosType posType);

    boolean DeletePosType(String uid);

    boolean CreatePosType(PosType posType);
}
