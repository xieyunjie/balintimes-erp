package com.balintimes.erp.center.controller;

import java.util.List;

import javax.annotation.Resource;

import com.balintimes.erp.center.model.UserType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.center.service.UserTypeService;
import com.balintimes.erp.center.util.JsonUtil;
import com.balintimes.erp.center.base.BaseController;

@Controller
@RequestMapping("usertype")
public class UserTypeController extends BaseController
{
	@Resource
	private UserTypeService userTypeService;

	@RequestMapping("list")
	@ResponseBody
	public String GetUserType()
	{
		List<UserType> list = userTypeService.GetUserType();
		
		return JsonUtil.ResponseSuccessfulMessage(list);
	}

}
