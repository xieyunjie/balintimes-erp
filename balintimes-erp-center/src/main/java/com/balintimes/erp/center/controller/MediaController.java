package com.balintimes.erp.center.controller;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.Media;
import com.balintimes.erp.center.service.MediaService;
import com.balintimes.erp.center.tuples.TupleResult;
import com.balintimes.erp.center.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2015/11/11.
 */
@Controller
@RequestMapping("media")
public class MediaController extends BaseController {
    @Resource
    private MediaService mediaService;

    @RequestMapping(value = "listbypage",method = RequestMethod.POST)
    @ResponseBody
    public String GetMediaListByCondition(String kid,String mediatypeuid,String lineuid,String stationuid, String directionuid, String trainuid,String postyeuid,
                                          String begintime,String endtime,String page,String pageSize) {
        Map<String, Object> params=new HashMap<String,Object>(11);
        params.put("kid",kid);
        params.put("mediatypeuid", mediatypeuid);
        params.put("lineuid", lineuid);
        params.put("stationuid",stationuid);
        params.put("directionuid", directionuid);
        params.put("trainuid", trainuid);
        params.put("postyeuid",postyeuid);
        params.put("begintime", begintime);
        params.put("endtime", endtime);

        params.put("page", page);
        params.put("pageSize", pageSize);
        List<Media> medias = mediaService.GetMediaListByCondition(params);
        return JsonUtil.ResponseSuccessfulMessage(medias,(Integer)params.get("totalcount"));
    }

    @RequestMapping(value = "getone",method = RequestMethod.POST)
    @ResponseBody
    public String GetOneMedia(String uid) {
        Media media = mediaService.GetOneMedia(uid);
        return JsonUtil.ResponseSuccessfulMessage(media);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public String UpdateMedia(Media media) {
        TupleResult<Boolean,Object> tupleResult=mediaService.UpdateMedia(media);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("修改媒体成功");
        }
        else{
            return JsonUtil.ResponseFailureMessage("修改媒体失败!"+tupleResult.objectMessage);
        }
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    public String DeleteMedia(String uid) {
        TupleResult<Boolean, Object> tupleResult= mediaService.DeleteMedia(uid);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("删除媒体成功");
        }
        else{
            return  JsonUtil.ResponseFailureMessage("删除媒体失败！"+tupleResult.objectMessage);
        }
    }

    @RequestMapping(value = "create",method = RequestMethod.POST)
    @ResponseBody
    public String CreateMedia(Media media) {
        media.setUid(UUID.randomUUID().toString());
        media.setCreatorid(webUsrUtil.CurrentUser().getUid());
        media.setCreatorname(webUsrUtil.CurrentUser().getEmployeeName());
        media.setCreatetime(new Date());
        media.setEditorid(webUsrUtil.CurrentUser().getUid());
        media.setEditorname(webUsrUtil.CurrentUser().getEmployeeName());
        media.setEdittime(new Date());
        media.setDeleted(false);
        TupleResult<Boolean,Object> tupleResult=mediaService.CreateMedia(media);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("添加媒体成功");
        }
        else{
            return JsonUtil.ResponseFailureMessage("添加媒体失败!"+tupleResult.objectMessage);
        }
    }
}
