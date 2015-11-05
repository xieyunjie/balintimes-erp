package com.balintimes.erp.center.dao;

import com.balintimes.erp.center.model.Direction;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/3.
 */
public interface DirectionDao {
    List<Direction> GetDirectionListByProcedure(Map<String,Object> params);

    Direction GetOneDirection(String uid);

    boolean UpdateDirection(Direction train);

    boolean DeleteDirection(String uid);

    boolean CreateDirection(Direction train);
}
