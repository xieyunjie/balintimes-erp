package com.balintimes.erp.center.controller;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.Level;
import com.balintimes.erp.center.service.LevelService;
import com.balintimes.erp.center.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2015/10/27.
 */
@Controller
@RequestMapping("level")
public class LevelController extends BaseController {
    @Resource
    private LevelService levelService;

    @RequestMapping(value="listbypage",method= RequestMethod.POST)
    @ResponseBody
    public String GetLevelListByCondition(String name) {
        Map<String,Object> params=new HashMap<String,Object>(1);
        params.put("name",name);
        List<Level> levels= levelService.GetLevelListByCondition(params);
        return JsonUtil.ResponseSuccessfulMessage(levels);
    }

    @RequestMapping(value="getonelevel",method= RequestMethod.POST)
    @ResponseBody
   public String GetOneLevel(String uid) {
       Level level=levelService.GetOneLevel(uid);
       return JsonUtil.ResponseSuccessfulMessage(level);
    }

    @RequestMapping(value="update",method= RequestMethod.POST)
    @ResponseBody
    public String UpdateLevel(Level level) {
        try {
            levelService.UpdateLevel(level);
            return  JsonUtil.ResponseSuccessfulMessage("更新级别成功");
        }
        catch (Exception err){
            return  JsonUtil.ResponseFailureMessage("更新级别失败");
        }
    }

    @RequestMapping(value="delete",method= RequestMethod.POST)
    @ResponseBody
    public String DeleteLevel(String uid) {
        try{
            boolean isSuccess = levelService.DeleteLevel(uid);
            if(isSuccess){
                return JsonUtil.ResponseSuccessfulMessage("删除级别成功");
            }
            else{
                return JsonUtil.ResponseSuccessfulMessage("该级别已有站点使用，不能删除");
            }

        }
        catch (Exception err){
            return JsonUtil.ResponseFailureMessage("删除失败");
        }

    }

    @RequestMapping(value="create",method= RequestMethod.POST)
    @ResponseBody
     public String CreateLevel(Level level) {
        try {
            level.setUid(UUID.randomUUID().toString());
            level.setCreatorid(webUsrUtil.CurrentUser().getUid());
            level.setCreatorname(webUsrUtil.CurrentUser().getUsername());
            level.setCreatetime(new Date());
            level.setEdittime(new Date());
            level.setDeleted(false);
            levelService.CreateLevel(level);
            return JsonUtil.ResponseSuccessfulMessage("增加级别成功");
        }
        catch (Exception err){
            return JsonUtil.ResponseFailureMessage("增加失败");
        }
    }
}
