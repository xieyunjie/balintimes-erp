package com.balintimes.erp.center.webservice;

import java.util.List;

import javax.annotation.Resource;

import com.balintimes.erp.center.model.authority.Employee;
import com.balintimes.erp.center.model.base.BusinessType;
import com.balintimes.erp.center.model.base.CustomerCategory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.center.service.AuthorityService;
import com.balintimes.erp.center.service.base.BusinessTypeService;
import com.balintimes.erp.center.service.base.CustomerCategoryService;
import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.ResponseMessage;

@Controller
@RequestMapping("ws/basedata")
public class BaseDataController {

	@Resource
	private BusinessTypeService businessTypeService;
	@Resource
	private CustomerCategoryService customerCategoryService;
	@Resource
	private AuthorityService authorityService;

	@RequestMapping("businesstypelist")
	@ResponseBody
	public AjaxResponse getBusinessTypeList() {
		List<BusinessType> list = this.businessTypeService
				.GetBusinessTypeList(null);
		return ResponseMessage.successful(list);
	}

	@RequestMapping("customercategorylist")
	@ResponseBody
	public AjaxResponse getCustomerCategoryList() {
		List<CustomerCategory> list = this.customerCategoryService
				.GetCustomerCategoryList(null);
		return ResponseMessage.successful(list);
	}

	@RequestMapping("getsubordinates")
	@ResponseBody
	public AjaxResponse getSubordinates(String userName) {
		List<Employee> list = this.authorityService.GetSubordinates(userName);
		return ResponseMessage.successful(list);
	}
}
