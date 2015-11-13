package com.balintimes.erp.center.controller;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.MediaTypeCategory;
import com.balintimes.erp.center.service.MediaTypeCategoryService;
import com.balintimes.erp.center.tuples.TupleResult;
import com.balintimes.erp.center.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2015/11/5.
 */
@Controller
@RequestMapping("mediatypecategory")
public class MediaTypeCategoryController extends BaseController{
    @Resource
    private MediaTypeCategoryService mediaTypeCategoryService;

    @RequestMapping(value="listbypage",method= RequestMethod.POST)
    @ResponseBody
    public String GetMediaTypeCategoryListByCondition(String name) {
        Map<String,Object> params=new HashMap<String,Object>(1);
        params.put("name",name);
        List<MediaTypeCategory> mediaTypeCategories= mediaTypeCategoryService.GetMediaTypeCategoryListByCondition(params);
        return JsonUtil.ResponseSuccessfulMessage(mediaTypeCategories);
    }

    @RequestMapping(value="getonemediatypecategory",method= RequestMethod.POST)
    @ResponseBody
    public String GetOneMediaTypeCategory(String uid) {
        MediaTypeCategory mediatypecategory=mediaTypeCategoryService.GetOneMediaTypeCategory(uid);
        return JsonUtil.ResponseSuccessfulMessage(mediatypecategory);
    }

    @RequestMapping(value="update",method= RequestMethod.POST)
    @ResponseBody
    public String UpdateMediaTypeCategory(MediaTypeCategory mediatypecategory) {
        TupleResult<Boolean, Object> tupleResult= mediaTypeCategoryService.UpdateMediaTypeCategory(mediatypecategory);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("修改媒体类型分类成功");
        }
        else{
            return  JsonUtil.ResponseFailureMessage("修改分类失败！"+tupleResult.objectMessage);
        }
    }

    @RequestMapping(value="delete",method= RequestMethod.POST)
    @ResponseBody
    public String DeleteMediaTypeCategory(String uid) {
        TupleResult<Boolean, Object> tupleResult= mediaTypeCategoryService.DeleteMediaTypeCategory(uid);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("删除分类成功");
        }
        else{
            return  JsonUtil.ResponseFailureMessage("删除分类失败！"+tupleResult.objectMessage);
        }
    }

    @RequestMapping(value="create",method= RequestMethod.POST)
    @ResponseBody
    public String CreateMediaTypeCategory(MediaTypeCategory mediatypecategory) {
        mediatypecategory.setUid(UUID.randomUUID().toString());
        mediatypecategory.setCreatorid(webUsrUtil.CurrentUser().getUid());
        mediatypecategory.setCreatorname(webUsrUtil.CurrentUser().getUsername());
        mediatypecategory.setCreatetime(new Date());
        mediatypecategory.setEdittime(new Date());
        mediatypecategory.setDeleted(false);
        TupleResult<Boolean, Object> tupleResult=mediaTypeCategoryService.CreateMediaTypeCategory(mediatypecategory);
        if(tupleResult.isSuccess==true){
            return JsonUtil.ResponseSuccessfulMessage("添加分类成功");
        }
        else{
            return  JsonUtil.ResponseFailureMessage("添加分类失败！"+tupleResult.objectMessage);
        }
    }
}
