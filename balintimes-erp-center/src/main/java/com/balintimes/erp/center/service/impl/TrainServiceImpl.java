package com.balintimes.erp.center.service.impl;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.TrainDao;
import com.balintimes.erp.center.model.Train;
import com.balintimes.erp.center.service.TrainService;
import com.balintimes.erp.center.tuples.TupleResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/29.
 */
@Service("trainService")
public class TrainServiceImpl implements TrainService {
    @Resource
    private TrainDao trainDao;

    public List<Train> GetTrainListByCondition(Map<String, Object> params) {
        return trainDao.GetTrainListByCondition(params);
    }


    public Train GetOneTrain(String uid) {
        return trainDao.GetOneTrain(uid);
    }

    @CustomerTransactional
    public void UpdateTrain(Train train) {
        trainDao.UpdateTrain(train);
    }

    @CustomerTransactional
    public TupleResult<Boolean, Object> DeleteTrain(String uid) {
        return trainDao.DeleteTrain(uid);
    }

    @CustomerTransactional
    public TupleResult<Boolean, Object> CreateTrain(Train train) {
        return trainDao.CreateTrain(train);
    }
}
