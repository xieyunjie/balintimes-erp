package com.balintimes.erp.center.service.impl;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.DirectionDao;
import com.balintimes.erp.center.model.Direction;
import com.balintimes.erp.center.service.DirectionService;
import com.balintimes.erp.center.tuples.TuplePage;
import com.balintimes.erp.center.tuples.TupleResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/3.
 */
@Service("directionService")
public class DirectionServiceImpl implements DirectionService {
    @Resource
    private DirectionDao directionDao;

    public List<Direction> GetDirectionListByProcedure(Map<String, Object> params) {
        List<Direction> directions=directionDao.GetDirectionListByProcedure(params);
        return directions;
    }


    public Direction GetOneDirection(String uid) {
        return directionDao.GetOneDirection(uid);
    }

    @CustomerTransactional
    public TupleResult<Boolean, Object> UpdateDirection(Direction direction) {
        try {
            Boolean isUpdated = directionDao.UpdateDirection(direction);
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
    public TupleResult<Boolean, Object> DeleteDirection(String uid) {
        try {
            Boolean isDeleted = directionDao.DeleteDirection(uid);
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
    public TupleResult<Boolean, Object> CreateDirection(Direction direction) {
        try {
            Boolean isCreated = directionDao.CreateDirection(direction);
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
