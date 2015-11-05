package com.balintimes.erp.center.service;

import com.balintimes.erp.center.model.Direction;
import com.balintimes.erp.center.tuples.TuplePage;
import com.balintimes.erp.center.tuples.TupleResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/3.
 */
public interface DirectionService {
    List<Direction> GetDirectionListByProcedure(Map<String,Object> params);

    Direction GetOneDirection(String uid);

    TupleResult<Boolean, Object> UpdateDirection(Direction train);

    TupleResult<Boolean, Object> DeleteDirection(String uid);

    TupleResult<Boolean, Object> CreateDirection(Direction train);
}
