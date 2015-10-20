package com.balintimes.erp.center.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.center.base.BaseController;
import com.balintimes.erp.center.model.Line;
import com.balintimes.erp.center.service.LineService;
import com.balintimes.erp.center.util.JsonUtil;

@Controller
@RequestMapping("line")
public class LineController extends BaseController{
	@Resource
	private LineService lineService;
	
	@RequestMapping(value="getlinelist",method=RequestMethod.POST)
	@ResponseBody
	public String GetLineList(){
		List<Line> lines=this.lineService.GetLineList();
		return JsonUtil.ResponseSuccessfulMessage(lines);
	}
	
	@RequestMapping(value="listbypage",method=RequestMethod.POST)
	@ResponseBody
	public String GetLineListByCondition(String name,String cityuid){		
		Map<String, Object> parameters = new HashMap<String, Object>(2);
		if(name==null)
			name="";
		if(cityuid==null)
			cityuid="";
		parameters.put("name", name);
		parameters.put("cityuid", cityuid);
		List<Line> lines =this.lineService.GetLineListByCondition(parameters);
		return JsonUtil.ResponseSuccessfulMessage(lines);
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@ResponseBody
	public String CreateLine(Line line){
		line.setUid(UUID.randomUUID().toString());
		line.setCreatorid(webUsrUtil.CurrentUser().getUid());
		line.setCreatorname(webUsrUtil.CurrentUser().getUsername());
		line.setCreatetime(new Date());
		line.setEdittime(new Date());
		line.setIsusebymediapool(true);
		line.setDeleted(false);
		boolean bool=this.lineService.CreateLine(line);
		if(bool)
			return JsonUtil.ResponseSuccessfulMessage("添加线路成功");
		else {
			return JsonUtil.ResponseFailureMessage("添加失败");
		}
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public String UpdateLine(Line line){
		try {
			line.setEditorid(webUsrUtil.CurrentUser().getUid());
			line.setEdittime(new Date());
			line.setIsusebymediapool(true);
			this.lineService.UpdateLine(line);
			return JsonUtil.ResponseSuccessfulMessage("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			return JsonUtil.ResponseFailureMessage("修改失败");
		}
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public String DeleteLine(String uid){
		try {
			this.lineService.DeleteLine(uid);
			return JsonUtil.ResponseSuccessfulMessage("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			return JsonUtil.ResponseFailureMessage("删除失败");
		}
	}
	
	@RequestMapping(value="getoneline",method=RequestMethod.POST)
	@ResponseBody
	public String GetOneLine(String uid){		
		Line line=lineService.GetOneLine(uid);
		return JsonUtil.ResponseSuccessfulMessage(line);
	}
}
