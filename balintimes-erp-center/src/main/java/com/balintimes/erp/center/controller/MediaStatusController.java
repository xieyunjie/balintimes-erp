package com.balintimes.erp.center.controller;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.MediaStatus;
import com.balintimes.erp.center.service.MediaStatusService;
import com.balintimes.erp.center.tuples.TupleResult;
import com.balintimes.erp.center.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2015/11/4.
 */
@Controller
@RequestMapping("mediastatus")
public class MediaStatusController extends BaseController{
    @Resource
    private MediaStatusService mediaStatusService;

    @RequestMapping(value = "listbypage",method = RequestMethod.POST)
    @ResponseBody
    public String GetMediaStatusListByCondition(String name) {
        Map<String, Object> params=new HashMap<>(1);
        params.put("name",name);
        List<MediaStatus> mediaStatuses =  mediaStatusService.GetMediaStatusListByCondition(params);
        return JsonUtil.ResponseSuccessfulMessage(mediaStatuses);
    }

    @RequestMapping(value = "getonemediastatus",method = RequestMethod.POST)
    @ResponseBody
    public String GetOneMediaStatus(String uid) {
        MediaStatus mediaStatus = mediaStatusService.GetOneMediaStatus(uid);
        return JsonUtil.ResponseSuccessfulMessage(mediaStatus);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public String UpdateMediaStatus(MediaStatus mediaStatus) {
        try{
            mediaStatus.setEditorid(webUsrUtil.CurrentUser().getUid());
            mediaStatus.setEditorname(webUsrUtil.CurrentUser().getEmployeeName());
            mediaStatus.setEdittime(new Date());
            mediaStatusService.UpdateMediaStatus(mediaStatus);
            return JsonUtil.ResponseSuccessfulMessage("修改成功");
        }
        catch (Exception err){
            return JsonUtil.ResponseFailureMessage("修改失败!"+err.getMessage());
        }
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public String DeleteMediaStatus(String uid) {
        TupleResult<Boolean, Object> tupleResult= mediaStatusService.DeleteMediaStatus(uid);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("删除状态成功");
        }
        else{
            return  JsonUtil.ResponseFailureMessage("删除状态失败！"+tupleResult.objectMessage);
        }
    }

    @RequestMapping(value = "create",method = RequestMethod.POST)
    @ResponseBody
    public String CreateMediaStatus(MediaStatus mediaStatus) {
        mediaStatus.setUid(UUID.randomUUID().toString());
        mediaStatus.setCreatorid(webUsrUtil.CurrentUser().getUid());
        mediaStatus.setCreatorname(webUsrUtil.CurrentUser().getEmployeeName());
        mediaStatus.setCreatetime(new Date());
        mediaStatus.setEditorid(webUsrUtil.CurrentUser().getUid());
        mediaStatus.setEditorname(webUsrUtil.CurrentUser().getEmployeeName());
        mediaStatus.setEdittime(new Date());
        mediaStatus.setDeleted(false);
        mediaStatus.setCansell(mediaStatus.getCansell());
        TupleResult<Boolean,Object> tupleResult=mediaStatusService.CreateMediaStatus(mediaStatus);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("添加状态成功");
        }
        else{
            return JsonUtil.ResponseFailureMessage("添加状态失败!"+tupleResult.objectMessage);
        }
    }
}
