package com.balintimes.erp.center.service;

import com.balintimes.erp.center.model.Train;
import com.balintimes.erp.center.tuples.TuplePage;
import com.balintimes.erp.center.tuples.TupleResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/29.
 */
public interface TrainService {
    List<Train> GetTrainListByCondition(Map<String,Object> params);

    Train GetOneTrain(String uid);

    TuplePage<List<Train>,Integer> GetTrainListByProcedure(Map<String,Object> params);


    void UpdateTrain(Train train);

    TupleResult<Boolean,Object> DeleteTrain(String uid);

    TupleResult<Boolean,Object> CreateTrain(Train train);
}
