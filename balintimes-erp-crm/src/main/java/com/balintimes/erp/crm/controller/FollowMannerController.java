package com.balintimes.erp.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.crm.model.FollowManner;
import com.balintimes.erp.crm.service.FollowMannerService;
import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.json.ResponseMessage;

@Controller
@RequestMapping("manner")
public class FollowMannerController extends BaseController {

	@Resource
	private FollowMannerService followMannerService;

	@RequestMapping("getlist")
	@ResponseBody
	public AjaxResponse getMannerList() {
		List<FollowManner> list = this.followMannerService.getMannerList();
		return ResponseMessage.successful(list);
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse createManner(FollowManner manner) {
		this.followMannerService.createManner(manner);
		return ResponseMessage.successful("success");
	}
}
