package com.balintimes.erp.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.balintimes.erp.crm.model.Customer;
import com.balintimes.erp.crm.model.EmpCustomer;
import com.balintimes.erp.crm.service.CustomerProcService;
import com.balintimes.erp.crm.service.CustomerService;
import com.balintimes.erp.util.json.AjaxResponse;
import com.balintimes.erp.util.json.JsonUtil;
import com.balintimes.erp.util.json.ResponseMessage;
import com.balintimes.erp.util.tuples.TuplePage;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

	@Resource
	private CustomerProcService customerProcService;
	@Resource
	private CustomerService customerService;

	@RequestMapping("listbyemp/{json}")
	@ResponseBody
	public AjaxResponse getCustomerByEmp(@PathVariable String json) {
		Map<String, Object> map = JsonUtil.ToMap(json);
		TuplePage<List<EmpCustomer>, Integer> page = this.customerProcService
				.getCustomerByEmp(map.get("name").toString(),
						map.get("userUids").toString(),
						map.get("businessTypeUid").toString(),
						(int) map.get("isReg"), map.get("brand").toString(),
						(int) map.get("pageSize"), (int) map.get("currPage"));
		return ResponseMessage.successful(page.objectList,
				page.objectTotalCount);
	}

	@RequestMapping("customerlist/{json}")
	@ResponseBody
	public AjaxResponse getCustomerByName(@PathVariable String json) {
		Map<String, Object> map = JsonUtil.ToMap(json);
		TuplePage<List<Customer>, Integer> page = this.customerService
				.getCustomerList(map.get("name").toString(),
						(int) map.get("currPage"), (int) map.get("pageSize"));
		return ResponseMessage.successful(page.objectList,
				page.objectTotalCount);
	}

	@RequestMapping("getcustomer/{uid}")
	@ResponseBody
	public AjaxResponse getCustomer(@PathVariable int uid) {
		return ResponseMessage
				.successful(this.customerService.getCustomer(uid));
	}
}
