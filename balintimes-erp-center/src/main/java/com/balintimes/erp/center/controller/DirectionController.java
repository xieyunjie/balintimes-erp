package com.balintimes.erp.center.controller;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.Direction;
import com.balintimes.erp.center.service.DirectionService;
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
 * Created by Administrator on 2015/11/3.
 */
@Controller
@RequestMapping("direction")
public class DirectionController extends BaseController{
    @Resource
    private DirectionService directionService;

    @RequestMapping(value = "listbypage",method = RequestMethod.POST)
    @ResponseBody
    public String GetDirectionListByProcedure(String name,String lineuid,String postypeuid,String page,String pageSize){
        Map<String,Object> params=new HashMap<>(5);
        params.put("name",name);
        params.put("lineuid", lineuid);
        params.put("postypeuid", postypeuid);

        params.put("page", page);
        params.put("pageSize", pageSize);

        List<Direction> directions=directionService.GetDirectionListByProcedure(params);
        TuplePage<List<Direction>,Integer> tuplePage=new TuplePage<>(directions,(int)params.get("totalcount"));
        return JsonUtil.ResponseSuccessfulMessage(tuplePage.objectList,tuplePage.objectTotalCount);
    }

    @RequestMapping(value = "getonedirection",method = RequestMethod.POST)
    @ResponseBody
    public String GetOneDirection(String uid) {
        Direction direction = directionService.GetOneDirection(uid);
        return JsonUtil.ResponseSuccessfulMessage(direction);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public String UpdateDirection(Direction direction) {
        try{
            direction.setEditorid(webUsrUtil.CurrentUser().getUid());
            direction.setEditorname(webUsrUtil.CurrentUser().getEmployeeName());
            direction.setEdittime(new Date());
            directionService.UpdateDirection(direction);
            return JsonUtil.ResponseSuccessfulMessage("修改成功");
        }
        catch (Exception err){
            return JsonUtil.ResponseFailureMessage("修改失败");
        }
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public String DeleteDirection(String uid) {
        TupleResult<Boolean, Object> tupleResult= directionService.DeleteDirection(uid);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("删除方向成功");
        }
        else{
            return  JsonUtil.ResponseFailureMessage("删除方向失败！"+tupleResult.objectMessage);
        }

    }

    @RequestMapping(value = "create",method = RequestMethod.POST)
    @ResponseBody
    public String CreateDirection(Direction direction) {
        direction.setUid(UUID.randomUUID().toString());
        direction.setCreatorid(webUsrUtil.CurrentUser().getUid());
        direction.setCreatorname(webUsrUtil.CurrentUser().getEmployeeName());
        direction.setCreatetime(new Date());
        direction.setEditorid(webUsrUtil.CurrentUser().getUid());
        direction.setEditorname(webUsrUtil.CurrentUser().getEmployeeName());
        direction.setEdittime(new Date());
        direction.setDeleted(false);
        TupleResult<Boolean,Object> tupleResult=directionService.CreateDirection(direction);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("添加方向成功");
        }
        else{
            return JsonUtil.ResponseFailureMessage("添加方向失败!"+tupleResult.objectMessage);
        }
    }

}
