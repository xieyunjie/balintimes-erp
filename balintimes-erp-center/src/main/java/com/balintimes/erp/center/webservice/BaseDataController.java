package com.balintimes.erp.center.webservice;

import java.util.List;

import javax.annotation.Resource;

import com.balintimes.erp.center.model.base.BusinessType;
import com.balintimes.erp.center.model.base.CustomerCategory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.center.service.base.BusinessTypeService;
import com.balintimes.erp.center.service.base.CustomerCategoryService;
import com.balintimes.erp.center.util.JsonUtil;

@Controller
@RequestMapping("basedata")
public class BaseDataController {

	@Resource
	private BusinessTypeService businessTypeService;
	@Resource
	private CustomerCategoryService customerCategoryService;

	@RequestMapping("businesstypelist")
	@ResponseBody
	public String getBusinessTypeList() {
		List<BusinessType> list = this.businessTypeService
				.GetBusinessTypeList(null);
		return JsonUtil.ResponseSuccessfulMessage(list);
	}

	@RequestMapping("customercategorylist")
	@ResponseBody
	public String getCustomerCategoryList() {
		List<CustomerCategory> list = this.customerCategoryService
				.GetCustomerCategoryList(null);
		return JsonUtil.ResponseSuccessfulMessage(list);
	}
}
