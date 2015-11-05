package com.balintimes.erp.center.dao.impl;

import com.balintimes.erp.center.dao.DirectionDao;
import com.balintimes.erp.center.mappers.DirectionMapper;
import com.balintimes.erp.center.model.Direction;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/3.
 */
@Repository("directionDao")
public class DirectionDaoImpl implements DirectionDao {
    @Resource
    private DirectionMapper directionMapper;

    public List<Direction> GetDirectionListByProcedure(Map<String, Object> params) {
        return directionMapper.GetDirectionListByProcedure(params);
    }

    public Direction GetOneDirection(String uid) {
        return directionMapper.GetOneDirection(uid);
    }

    public boolean UpdateDirection(Direction train) {
        return directionMapper.UpdateDirection(train);
    }


    public boolean DeleteDirection(String uid) {
        return directionMapper.DeleteDirection(uid);
    }


    public boolean CreateDirection(Direction train) {
        return directionMapper.CreateDirection(train);
    }
}
