package com.balintimes.erp.center.controller;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.Train;
import com.balintimes.erp.center.service.TrainService;
import com.balintimes.erp.center.tuples.TuplePage;
import com.balintimes.erp.center.tuples.TupleResult;
import com.balintimes.erp.center.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

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
    public String GetTrainListByCondition(String name,String lineuid,String cityuid,String page,String pageSize) {
        Map<String, Object> params=new HashMap<String,Object>(5);
        params.put("name",name);
        params.put("lineuid", lineuid);
        params.put("cityuid", cityuid);

        params.put("page", page);
        params.put("pageSize", pageSize);

//        List<Train> trains = trainService.GetTrainListByCondition(params);
        TuplePage<List<Train>,Integer> tuplePage= trainService.GetTrainListByProcedure(params);
        return JsonUtil.ResponseSuccessfulMessage(tuplePage.objectList,tuplePage.objectTotalCount);
    }

    @RequestMapping(value = "getonetrain",method = RequestMethod.POST)
    @ResponseBody
    public String GetOneTrain(String uid) {
        Train train = trainService.GetOneTrain(uid);
        return JsonUtil.ResponseSuccessfulMessage(train);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public String UpdateTrain(Train train) {
        try{
            train.setEditorid(webUsrUtil.CurrentUser().getUid());
            train.setEditorname(webUsrUtil.CurrentUser().getEmployeeName());
            train.setEdittime(new Date());
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
        train.setCreatorname(webUsrUtil.CurrentUser().getEmployeeName());
        train.setCreatetime(new Date());
        train.setEditorid(webUsrUtil.CurrentUser().getUid());
        train.setEditorname(webUsrUtil.CurrentUser().getEmployeeName());
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
