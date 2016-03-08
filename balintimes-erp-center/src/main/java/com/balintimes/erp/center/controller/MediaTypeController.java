package com.balintimes.erp.center.controller;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.MediaType;
import com.balintimes.erp.center.service.MediaTypeService;
import com.balintimes.erp.center.tuples.TupleResult;
import com.balintimes.erp.center.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2015/11/6.
 */
@Controller
@RequestMapping("mediatype")
public class MediaTypeController extends BaseController {
    @Resource
    private MediaTypeService mediaTypeService;

    @RequestMapping(value = "listbypage",method = RequestMethod.POST)
    @ResponseBody
    public String GetMediaTypeListByCondition(String name,String page,String pageSize) {
        Map<String, Object> params=new HashMap<>(3);
        params.put("name",name);

        params.put("page", page);
        params.put("pageSize", pageSize);
        List<MediaType> mediaTypes =  mediaTypeService.GetMediaTypeListByCondition(params);
        return JsonUtil.ResponseSuccessfulMessage(mediaTypes,(Integer)params.get("totalcount"));
    }

    @RequestMapping(value = "getone",method = RequestMethod.POST)
    @ResponseBody
    public String GetOneMediaType(String uid) {
        MediaType mediaType = mediaTypeService.GetOneMediaType(uid);
        return JsonUtil.ResponseSuccessfulMessage(mediaType);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public String UpdateMediaType(MediaType mediaType) {
        mediaType.setEditorid(webUsrUtil.CurrentUser().getUid());
        mediaType.setEditorname(webUsrUtil.CurrentUser().getEmployeeName());
        mediaType.setEdittime(new Date());
        TupleResult<Boolean, Object> tupleResult= mediaTypeService.UpdateMediaType(mediaType);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("修改类型成功");
        }
        else{
            return  JsonUtil.ResponseFailureMessage("修改类型失败！"+tupleResult.objectMessage);
        }
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public String DeleteMediaType(String uid) {
        TupleResult<Boolean, Object> tupleResult= mediaTypeService.DeleteMediaType(uid);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("删除类型成功");
        }
        else{
            return  JsonUtil.ResponseFailureMessage("删除类型失败！"+tupleResult.objectMessage);
        }
    }

    @RequestMapping(value = "create",method = RequestMethod.POST)
    @ResponseBody
    public String CreateMediaType(MediaType mediaType) {
        mediaType.setUid(UUID.randomUUID().toString());
        mediaType.setCreatorid(webUsrUtil.CurrentUser().getUid());
        mediaType.setCreatorname(webUsrUtil.CurrentUser().getEmployeeName());
        mediaType.setCreatetime(new Date());
        mediaType.setEditorid(webUsrUtil.CurrentUser().getUid());
        mediaType.setEditorname(webUsrUtil.CurrentUser().getEmployeeName());
        mediaType.setEdittime(new Date());
        mediaType.setDeleted(false);
        TupleResult<Boolean,Object> tupleResult=mediaTypeService.CreateMediaType(mediaType);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("添加类型成功");
        }
        else{
            return JsonUtil.ResponseFailureMessage("添加类型失败!"+tupleResult.objectMessage);
        }
    }
}
