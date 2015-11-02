package com.balintimes.erp.center.dao.impl;

import com.balintimes.erp.center.dao.TrainDao;
import com.balintimes.erp.center.mappers.TrainMapper;
import com.balintimes.erp.center.model.Train;
import com.balintimes.erp.center.tuples.TupleResult;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/29.
 */
@Repository("trainDao")
public class TrainDaoImpl implements TrainDao{
    @Resource
    private TrainMapper trainMapper;

    public List<Train> GetTrainListByCondition(Map<String, Object> params) {
        return trainMapper.GetTrainListByCondition(params);
    }


    public Train GetOneTrain(String uid) {
        return trainMapper.GetOneTrain(uid);
    }


    public void UpdateTrain(Train train) {
        trainMapper.UpdateTrain(train);
    }

    public TupleResult<Boolean,Object> DeleteTrain(String uid) {
        try{
            boolean isDeleted = trainMapper.DeleteTrain(uid);
            TupleResult<Boolean,Object> resultMap=new TupleResult<Boolean,Object>(isDeleted,"success");
            return resultMap;
        }
        catch (Exception err){
            TupleResult<Boolean,Object> resultMap=new TupleResult<Boolean,Object>(false,err.getMessage());
            return resultMap;
        }
    }

    public TupleResult<Boolean,Object> CreateTrain(Train train) {
        try{
            boolean isCreated = trainMapper.CreateTrain(train);
            TupleResult<Boolean,Object> resultMap=new TupleResult<Boolean,Object>(isCreated,"success");
            return resultMap;
        }
        catch (Exception err){
            TupleResult<Boolean,Object> resultMap=new TupleResult<Boolean,Object>(false,err.getMessage());
            return resultMap;
        }
    }
}
