package com.balintimes.erp.crm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.balintimes.erp.crm.service.AreaService;

@Controller
@RequestMapping("area")
public class AreaController extends BaseController {
	
	@Resource
	private AreaService areaInfoService;
	
	
}
