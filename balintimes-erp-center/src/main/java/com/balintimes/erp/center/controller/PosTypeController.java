package com.balintimes.erp.center.controller;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.PosType;
import com.balintimes.erp.center.service.PosTypeService;
import com.balintimes.erp.center.tuples.TupleResult;
import com.balintimes.erp.center.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/29.
 */
@Controller
@RequestMapping("postype")
public class PosTypeController extends BaseController {
    @Resource
    private PosTypeService posTypeService;

    @RequestMapping(value = "listbypage", method = RequestMethod.POST)
    @ResponseBody
    public String GetPosTypeListByCondition(String name) {
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put("name", name);
        List<PosType> posTypes = posTypeService.GetPosTypeListByCondition(parameters);
        return JsonUtil.ResponseSuccessfulMessage(posTypes);
    }

    @RequestMapping(value = "getonepostype", method = RequestMethod.POST)
    @ResponseBody
    public String GetOnePosType(String uid) {
        PosType posType = posTypeService.GetOnePosType(uid);
        return JsonUtil.ResponseSuccessfulMessage(posType);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String UpdatePosType(PosType posType) {
        TupleResult<Boolean,Object> tupleResult=posTypeService.UpdatePosType(posType);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("修改成功");
        }
        else{
            return JsonUtil.ResponseFailureMessage("修改失败！"+tupleResult.objectMessage);
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String DeletePosType(String uid) {
        TupleResult<Boolean,Object> tupleResult=posTypeService.DeletePosType(uid);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("删除成功");
        }
        else{
            return JsonUtil.ResponseFailureMessage("删除失败！"+tupleResult.objectMessage);
        }
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public String CreatePosType(PosType posType) {
        TupleResult<Boolean,Object> tupleResult=posTypeService.CreatePosType(posType);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("新增成功");
        }
        else{
            return JsonUtil.ResponseFailureMessage("新增失败！"+tupleResult.objectMessage);
        }
    }
}



