package com.balintimes.erp.center.webservice;

import java.util.List;

import javax.annotation.Resource;

import com.balintimes.erp.center.model.authority.Employee;
import com.balintimes.erp.center.model.base.BusinessType;
import com.balintimes.erp.center.model.base.CustomerCategory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("businesstype/{uid}")
	@ResponseBody
	public AjaxResponse getBusinessType(@PathVariable String uid) {
		BusinessType bt = this.businessTypeService.GetBusinessType(uid);
		return ResponseMessage.successful(bt);
	}

	@RequestMapping("customercategorylist")
	@ResponseBody
	public AjaxResponse getCustomerCategoryList() {
		List<CustomerCategory> list = this.customerCategoryService
				.GetCustomerCategoryList(null);
		return ResponseMessage.successful(list);
	}

	@RequestMapping("customercategory/{uid}")
	@ResponseBody
	public AjaxResponse getCustomerCategory(@PathVariable String uid) {
		CustomerCategory cc = this.customerCategoryService
				.GetCustomerCategory(uid);
		return ResponseMessage.successful(cc);
	}

	@RequestMapping("getsubordinates/{username}")
	@ResponseBody
	public AjaxResponse getSubordinates(@PathVariable String username) {
		List<Employee> list = this.authorityService.GetSubordinates(username);
		return ResponseMessage.successful(list);
	}
}
