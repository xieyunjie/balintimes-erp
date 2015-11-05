package com.balintimes.erp.center.mappers;

import com.balintimes.erp.center.model.Direction;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/3.
 */
public interface DirectionMapper {

    Direction GetOneDirection(String uid);

    List<Direction> GetDirectionListByProcedure(Map<String,Object> params);

    boolean UpdateDirection(Direction train);

    boolean DeleteDirection(String uid);

    boolean CreateDirection(Direction train);
    
}
