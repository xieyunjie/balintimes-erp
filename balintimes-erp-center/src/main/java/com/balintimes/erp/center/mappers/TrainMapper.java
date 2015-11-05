package com.balintimes.erp.center.mappers;

import com.balintimes.erp.center.model.Train;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/28.
 */
public interface TrainMapper {
    List<Train> GetTrainListByCondition(Map<String,Object> params);

    Train GetOneTrain(String uid);

    List<Train> GetTrainListByProcedure(Map<String,Object> params);

    void UpdateTrain(Train train);

    boolean DeleteTrain(String uid);

    boolean CreateTrain(Train train);

}
