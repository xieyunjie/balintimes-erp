package com.balintimes.erp.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.crm.model.Area;
import com.balintimes.erp.crm.service.AreaService;
import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.ResponseMessage;

@Controller
@RequestMapping("area")
public class AreaController extends BaseController {

	@Resource
	private AreaService areaInfoService;

	@RequestMapping("gettoppareas")
	@ResponseBody
	public AjaxResponse getAreaInfoByTop() {
		return this.getAreaInfoByParent("00000000-0000-0000-0000-000000000000");
	}

	@RequestMapping("getareabyparent/{parentuid}")
	@ResponseBody
	public AjaxResponse getAreaInfoByParent(@PathVariable String parentuid) {
		List<Area> list = this.areaInfoService
				.getAreaInfoListByParnetUid(parentuid);
		return ResponseMessage.successful(list);
	}

	@RequestMapping("getarea/{uid}")
	@ResponseBody
	public AjaxResponse getArea(@PathVariable String uid) {
		Area model = this.areaInfoService.getAreaInfo(uid);
		return ResponseMessage.successful(model);
	}
}
