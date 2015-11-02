package com.balintimes.erp.center.controller;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.Train;
import com.balintimes.erp.center.service.TrainService;
import com.balintimes.erp.center.tuples.TupleResult;
import com.balintimes.erp.center.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2015/10/29.
 */
@Controller
@RequestMapping("train")
public class TrainController extends BaseController {
    @Resource
    private TrainService trainService;

    @RequestMapping(value = "listbypage",method = RequestMethod.POST)
    @ResponseBody
    public String GetTrainListByCondition(Map<String, Object> params) {
        List<Train> trains= trainService.GetTrainListByCondition(params);
        return JsonUtil.ResponseSuccessfulMessage(trains);
    }

    @RequestMapping(value = "getonetrain",method = RequestMethod.POST)
    @ResponseBody
    public Train GetOneTrain(String uid) {
        return trainService.GetOneTrain(uid);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public String UpdateTrain(Train train) {
        try{
            trainService.UpdateTrain(train);
            return JsonUtil.ResponseSuccessfulMessage("修改成功");
        }
        catch (Exception err){
            return JsonUtil.ResponseFailureMessage("修改失败");
        }
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public String DeleteTrain(String uid) {
        TupleResult<Boolean, Object> tupleResult= trainService.DeleteTrain(uid);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("删除列车成功");
        }
        else{
            return  JsonUtil.ResponseFailureMessage("删除列车失败！"+tupleResult.objectMessage);
        }

    }

    @RequestMapping(value = "create",method = RequestMethod.POST)
    @ResponseBody
    public String CreateTrain(Train train) {
        train.setUid(UUID.randomUUID().toString());
        train.setCreatorid(webUsrUtil.CurrentUser().getUid());
        train.setCreatorname(webUsrUtil.CurrentUser().getUsername());
        train.setCreatetime(new Date());
        train.setEdittime(new Date());
        train.setDeleted(false);
        TupleResult<Boolean,Object> tupleResult=trainService.CreateTrain(train);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("添加列车成功");
        }
        else{
            return JsonUtil.ResponseFailureMessage("添加列车失败!"+tupleResult.objectMessage);
        }
    }
}
